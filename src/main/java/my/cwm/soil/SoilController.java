package my.cwm.soil;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * SoilController
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/soil")
public class SoilController {

    private SoilService soilService;

    public SoilController(SoilService soilService) {
        this.soilService = soilService;
    }

    @GetMapping("/all")
    public List<Soil> getAll() {
        return this.soilService.getAll();
    }

    @GetMapping("/dt")
    public HashMap<String, Object> getDT(@Valid DataTablesInput dataTablesInput) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", this.soilService.getRecordCount(dataTablesInput));
        map.put("results", this.soilService.dtSoil(dataTablesInput));
        log.info("{}", map);
        return map;
    }

    @GetMapping("/{id}")
    public Soil getSoil(@PathVariable long id) {
        return soilService.getSoilById(id);
    }

    @PostMapping("")
    @Transactional
    public Soil save(@Valid @RequestBody Soil soil) {
        return soilService.save(soil);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable long id) {
        soilService.delete(id);
    }
}