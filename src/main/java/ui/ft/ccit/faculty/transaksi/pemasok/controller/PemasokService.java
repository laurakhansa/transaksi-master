package ui.ft.ccit.faculty.transaksi.pemasok.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;
import ui.ft.ccit.faculty.transaksi.pemasok.model.PemasokRepository;

import java.util.List;

@Service
@Transactional
public class PemasokService {

    private final PemasokRepository repository;

    public PemasokService(PemasokRepository repository) {
        this.repository = repository;
    }

    public List<Pemasok> getAll() {
        return repository.findAll();
    }

    public Page<Pemasok> getAllPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Pemasok getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Pemasok", id));
    }

    public Pemasok save(Pemasok pemasok) {
        return repository.save(pemasok);
    }

    public List<Pemasok> saveAll(List<Pemasok> pemasokList) {
        return repository.saveAll(pemasokList);
    }

    public Pemasok update(String id, Pemasok updated) {
        Pemasok existing = getById(id);
        existing.setNama(updated.getNama());
        existing.setAlamat(updated.getAlamat());
        existing.setTelepon(updated.getTelepon());
        existing.setEmail(updated.getEmail());
        return repository.save(existing);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Pemasok", id);
        }
        repository.deleteById(id);
    }
}
