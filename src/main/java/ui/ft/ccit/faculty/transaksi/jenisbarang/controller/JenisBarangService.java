package ui.ft.ccit.faculty.transaksi.jenisbarang.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarangNotFoundException;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarangRepository;

import java.util.List;

@Service
@Transactional
public class JenisBarangService {

    private final JenisBarangRepository repository;

    public JenisBarangService(JenisBarangRepository repository) {
        this.repository = repository;
    }

    public List<JenisBarang> getAll() {
        return repository.findAll();
    }

    public Page<JenisBarang> getAllPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public JenisBarang getById(Byte id) {
        return repository.findById(id)
                .orElseThrow(() -> new JenisBarangNotFoundException(id));
    }

    public JenisBarang save(JenisBarang jenis) {
        return repository.save(jenis);
    }

    public List<JenisBarang> saveAll(List<JenisBarang> jenisList) {
        return repository.saveAll(jenisList);
    }

    public JenisBarang update(Byte id, JenisBarang updated) {
        JenisBarang existing = getById(id);
        existing.setNamaJenis(updated.getNamaJenis());
        return repository.save(existing);
    }

    public void delete(Byte id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("JenisBarang", id.toString());
        }
        repository.deleteById(id);
    }
}
