package ui.ft.ccit.faculty.transaksi.karyawan.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;

import java.util.List;

@Service
@Transactional
public class KaryawanService {

    private final KaryawanRepository repository;

    public KaryawanService(KaryawanRepository repository) {
        this.repository = repository;
    }

    public List<Karyawan> getAll() {
        return repository.findAll();
    }

    public Page<Karyawan> getAllPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Karyawan getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Karyawan", id));
    }

    public Karyawan save(Karyawan karyawan) {
        return repository.save(karyawan);
    }

    public List<Karyawan> saveAll(List<Karyawan> karyawanList) {
        return repository.saveAll(karyawanList);
    }

    public Karyawan update(String id, Karyawan updated) {
        Karyawan existing = getById(id);
        existing.setNama(updated.getNama());
        existing.setJenisKelamin(updated.getJenisKelamin());
        existing.setAlamat(updated.getAlamat());
        existing.setTelepon(updated.getTelepon());
        existing.setTglLahir(updated.getTglLahir());
        existing.setGaji(updated.getGaji());
        return repository.save(existing);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Karyawan", id);
        }
        repository.deleteById(id);
    }
}
