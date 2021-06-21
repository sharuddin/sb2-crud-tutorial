package my.cwm.soil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TanahRepository extends JpaRepository<Tanah, Long>, JpaSpecificationExecutor<Tanah> {

}
