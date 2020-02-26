package my.cwm.soil;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

/**
 * SoilDataJpaTest
 */
@Slf4j
@DataJpaTest
public class SoilDataJpaTest {
    SoilService soilService;

    @Test
    public void should_return_result_with_input_search(@Autowired SoilRepository soilRepository) {
        soilService = new SoilService(soilRepository);
        DataTablesInput dti = new DataTablesInput();
        dti.setPageNumber(1);
        dti.setPageSize(5);
        dti.setSortColumn("description");
        dti.setSortDirection("desc");
        dti.setSearchInput("00");
        List<Soil> li = soilService.dtSoil(dti);
        log.info("{}", li);
    }

    @Test
    public void should_return_result_without_input_search(@Autowired SoilRepository soilRepository) {
        soilService = new SoilService(soilRepository);
        DataTablesInput dti = new DataTablesInput();
        dti.setPageNumber(1);
        dti.setPageSize(5);
        dti.setSortColumn("description");
        dti.setSortDirection("desc");
        dti.setSearchInput("");
        List<Soil> li = soilService.dtSoil(dti);
        log.info("{}", li);
    }
}