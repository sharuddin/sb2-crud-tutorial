package my.cwm.soil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * SoilRepository
 */
public interface SoilRepository extends JpaRepository<Soil, Long>, JpaSpecificationExecutor<Soil> {

}