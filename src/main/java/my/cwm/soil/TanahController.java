package my.cwm.soil;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/tanah")
public class TanahController {
    private final TanahService tanahService;

    public void log() {
        log.debug("saya log");
    }

    @GetMapping("/list")
    public List<Tanah> getAllTanah() {
        List<Tanah> tanahl = this.tanahService.getAllTanah();
        log.debug("list tanah {}", tanahl);
        return tanahl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Tanah> tanah = this.tanahService.getTanahById(id);
        if (tanah.isPresent()) {
            return ResponseEntity.ok(tanah.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Tanah tanah) {
        Optional<Tanah> tanahsave = tanahService.simpan(tanah);
        if (tanahsave.isPresent()) {
            return ResponseEntity.ok(tanahsave.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.tanahService.deleteTanah(id);
    }
}
