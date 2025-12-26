package ui.ft.ccit.faculty.transaksi.transaksi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;

import java.util.List;

@Service
@Transactional
public class TransaksiService {

    private final TransaksiRepository repository;

    public TransaksiService(TransaksiRepository repository) {
        this.repository = repository;
    }

    public List<Transaksi> getAll() {
        return repository.findAll();
    }

    public Page<Transaksi> getAllPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Transaksi getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Transaksi", id));
    }

    public Transaksi save(Transaksi transaksi) {
        return repository.save(transaksi);
    }

    public List<Transaksi> saveAll(List<Transaksi> transaksiList) {
        return repository.saveAll(transaksiList);
    }

    public Transaksi update(String id, Transaksi updated) {
        Transaksi existing = getById(id);
        existing.setTglTransaksi(updated.getTglTransaksi());
        existing.setIdPelanggan(updated.getIdPelanggan());
        existing.setIdKaryawan(updated.getIdKaryawan());
        return repository.save(existing);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Transaksi", id);
        }
        repository.deleteById(id);
    }
}
