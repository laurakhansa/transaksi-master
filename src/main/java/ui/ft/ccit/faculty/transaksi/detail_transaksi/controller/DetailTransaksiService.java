package ui.ft.ccit.faculty.transaksi.detail_transaksi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.detail_transaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detail_transaksi.model.DetailTransaksiId;
import ui.ft.ccit.faculty.transaksi.detail_transaksi.model.DetailTransaksiRepository;

import java.util.List;

@Service
@Transactional
public class DetailTransaksiService {

    private final DetailTransaksiRepository repository;
    private final ui.ft.ccit.faculty.transaksi.barang.controller.BarangService barangService;

    public DetailTransaksiService(DetailTransaksiRepository repository,
            ui.ft.ccit.faculty.transaksi.barang.controller.BarangService barangService) {
        this.repository = repository;
        this.barangService = barangService;
    }

    public List<DetailTransaksi> getAll() {
        return repository.findAll();
    }

    public Page<DetailTransaksi> getAllPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public DetailTransaksi getById(String kodeTransaksi, String idBarang) {
        DetailTransaksiId id = new DetailTransaksiId(kodeTransaksi, idBarang);
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("DetailTransaksi", id.toString()));
    }

    public List<DetailTransaksi> getByKodeTransaksi(String kodeTransaksi) {
        return repository.findByIdKodeTransaksi(kodeTransaksi);
    }

    public DetailTransaksi save(DetailTransaksi detail) {
        // Kurangi stok barang saat transaksi dibuat
        barangService.kurangiStok(detail.getId().getIdBarang(), detail.getJumlah());
        return repository.save(detail);
    }

    public List<DetailTransaksi> saveAll(List<DetailTransaksi> details) {
        details.forEach(detail -> barangService.kurangiStok(detail.getId().getIdBarang(), detail.getJumlah()));
        return repository.saveAll(details);
    }

    public DetailTransaksi update(String kodeTransaksi, String idBarang, DetailTransaksi updated) {
        DetailTransaksi existing = getById(kodeTransaksi, idBarang);

        // Sesuaikan stok jika jumlah berubah
        int selisih = updated.getJumlah() - existing.getJumlah();
        if (selisih > 0) {
            barangService.kurangiStok(idBarang, selisih);
        } else if (selisih < 0) {
            // Jika jumlah berkurang, kembalikan stok
            ui.ft.ccit.faculty.transaksi.barang.model.Barang barang = barangService.getById(idBarang);
            barang.setStok(barang.getStok() + Math.abs(selisih));
        }

        existing.setJumlah(updated.getJumlah());
        return repository.save(existing);
    }

    public void delete(String kodeTransaksi, String idBarang) {
        DetailTransaksiId id = new DetailTransaksiId(kodeTransaksi, idBarang);
        DetailTransaksi existing = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("DetailTransaksi", id.toString()));

        // Kembalikan stok saat detail dihapus
        ui.ft.ccit.faculty.transaksi.barang.model.Barang barang = barangService.getById(idBarang);
        barang.setStok(barang.getStok() + existing.getJumlah());

        repository.deleteById(id);
    }
}
