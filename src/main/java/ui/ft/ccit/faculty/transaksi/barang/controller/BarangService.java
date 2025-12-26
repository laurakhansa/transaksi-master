package ui.ft.ccit.faculty.transaksi.barang.controller;

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.barang.model.BarangRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BarangService {

    private final BarangRepository barangRepository;

    public BarangService(BarangRepository barangRepository) {
        this.barangRepository = barangRepository;
    }

    public List<Barang> getAll() {
        return barangRepository.findAll();
    }

    public Page<Barang> getAllPaged(Pageable pageable) {
        return barangRepository.findAll(pageable);
    }

    public Barang getById(String id) {
        return barangRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Barang", id));
    }

    public List<Barang> searchByNama(String keyword) {
        return barangRepository.findByNamaContainingIgnoreCase(keyword);
    }

    // CREATE
    public Barang save(Barang barang) {
        if (barang.getIdBarang() == null || barang.getIdBarang().isBlank()) {
            throw new IllegalArgumentException("idBarang wajib diisi");
        }

        if (barangRepository.existsById(barang.getIdBarang())) {
            throw new DataAlreadyExistsException("Barang", barang.getIdBarang());
        }

        return barangRepository.save(barang);
    }

    // BULK CREATE
    public List<Barang> saveAll(List<Barang> barangList) {
        return barangRepository.saveAll(barangList);
    }

    // UPDATE
    public Barang update(String id, Barang updated) {
        Barang existing = getById(id);

        existing.setNama(updated.getNama());
        existing.setStok(updated.getStok());
        existing.setHarga(updated.getHarga());
        existing.setPersenLaba(updated.getPersenLaba());
        existing.setDiskon(updated.getDiskon());
        existing.setIdJenisBarang(updated.getIdJenisBarang());
        existing.setIdPemasok(updated.getIdPemasok());

        return barangRepository.save(existing);
    }

    // DELETE
    public void delete(String id) {
        if (!barangRepository.existsById(id)) {
            throw new DataNotFoundException("Barang", id);
        }
        barangRepository.deleteById(id);
    }

    // Kurangi Stok Barang
    public void kurangiStok(String idBarang, Integer jumlahKeluar) {
        Barang barang = getById(idBarang);

        Integer stokSaatIni = barang.getStok();

        if (stokSaatIni < jumlahKeluar) {
            throw new IllegalArgumentException(
                    "Stok barang " + barang.getNama() + " (" + idBarang +
                            ") tidak mencukupi. Stok saat ini: " + stokSaatIni +
                            ", Permintaan: " + jumlahKeluar);
        }

        Integer stokBaru = stokSaatIni - jumlahKeluar;
        barang.setStok(stokBaru);
        barangRepository.save(barang);
    }
}
