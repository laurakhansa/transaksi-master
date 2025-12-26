package ui.ft.ccit.faculty.transaksi.pelanggan.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;

import java.util.List;

@Service
@Transactional
public class PelangganService {

    private final PelangganRepository repository;

    public PelangganService(PelangganRepository repository) {
        this.repository = repository;
    }

    public List<Pelanggan> getAll() {
        return repository.findAll();
    }

    public Page<Pelanggan> getAllPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Pelanggan getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Pelanggan", id));
    }

    public Pelanggan save(Pelanggan pelanggan) {
        return repository.save(pelanggan);
    }

    public List<Pelanggan> saveAll(List<Pelanggan> pelangganList) {
        return repository.saveAll(pelangganList);
    }

    public Pelanggan update(String id, Pelanggan updated) {
        Pelanggan existing = getById(id);
        existing.setNama(updated.getNama());
        existing.setJenisKelamin(updated.getJenisKelamin());
        existing.setAlamat(updated.getAlamat());
        existing.setTelepon(updated.getTelepon());
        existing.setTglLahir(updated.getTglLahir());
        existing.setJenisPelanggan(updated.getJenisPelanggan());
        return repository.save(existing);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Pelanggan", id);
        }
        repository.deleteById(id);
    }
}
