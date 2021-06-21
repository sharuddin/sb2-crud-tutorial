package my.cwm.soil;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TanahService {

    String[] columns = { "nama" };
    private final TanahRepository tanahRepository;

    public Optional<Tanah> getTanahById(long id) {
        return this.tanahRepository.findById(id);
    }

    public Optional<Tanah> simpan(Tanah tanah) {
        return Optional.of(this.tanahRepository.save(tanah));
    }

    public List<Tanah> getAllTanah() {
        return this.tanahRepository.findAll();
    }

    public void deleteTanah(Long id) {
        this.tanahRepository.deleteById(id);
    }
}
