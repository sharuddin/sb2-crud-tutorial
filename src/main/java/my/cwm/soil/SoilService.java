package my.cwm.soil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * SoilService
 */
@Service
public class SoilService {

    String[] columns = { "description", "symbol", "category" };
    private final SoilRepository soilRepository;

    public SoilService(final SoilRepository soilRepository) {
        this.soilRepository = soilRepository;
    }

    public Soil getSoilById(long id) {
        return this.soilRepository.getOne(id);
    }

    public long getRecordCount() {
        return this.soilRepository.count();
    }

    public List<Soil> getAll() {
        return this.soilRepository.findAll();
    }

    public Soil save(final Soil soil) {
        return this.soilRepository.saveAndFlush(soil);
    }

    public void delete(final Long id) {
        this.soilRepository.deleteById(id);
    }

    private Specification getFilterSpecification(DataTablesInput dti) {
        return new Specification() {
            private static final long serialVersionUID = -1381829247221773302L;

            @Override
            public Predicate toPredicate(final Root root, final CriteriaQuery query,
                    final CriteriaBuilder criteriaBuilder) {
                final List<Predicate> pr = new ArrayList<>();
                // if (!dti.getSearchInput().equals(""))
                for (final String column : columns) {
                    pr.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(column)),
                            contains(dti.getSearchInput().toLowerCase())));
                }
                return criteriaBuilder.or(pr.toArray(new Predicate[0]));
            }
        };
    }

    public long getRecordCount(DataTablesInput dti) {
        return dti.getSearchInput().equals("") ? this.soilRepository.count()
                : this.soilRepository.count(getFilterSpecification(dti));
    }

    public List<Soil> dtSoil(DataTablesInput dti) {
        final Pageable paging = PageRequest.of(dti.getPageNumber(), dti.getPageSize(),
                dti.getSortDirection().equals("asc") ? Sort.by(dti.getSortColumn()).ascending()
                        : Sort.by(dti.getSortColumn()).descending());
        final Page<Soil> pagedResult = dti.getSearchInput().equals("") ? soilRepository.findAll(paging)
                : soilRepository.findAll(getFilterSpecification(dti), paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Soil>();
        }
    }

    private static String contains(final String expression) {
        return MessageFormat.format("%{0}%", expression);
    }
}