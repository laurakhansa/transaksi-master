package ui.ft.ccit.faculty.transaksi.detail_transaksi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.detail_transaksi.model.DetailTransaksi;

import java.util.List;

@RestController
@RequestMapping("/api/detail-transaksi")
@Tag(name = "Detail Transaksi", description = "API untuk manajemen detail transaksi")
public class DetailTransaksiController {

    private final DetailTransaksiService service;

    public DetailTransaksiController(DetailTransaksiService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List semua detail transaksi", description = "Mengambil semua data detail transaksi")
    public List<DetailTransaksi> list() {
        return service.getAll();
    }

    @GetMapping("/pages")
    @Operation(summary = "List detail transaksi dengan pagination", description = "Mengambil data detail transaksi dengan pagination")
    public Page<DetailTransaksi> listPaged(Pageable pageable) {
        return service.getAllPaged(pageable);
    }

    @GetMapping("/{kodeTransaksi}/{idBarang}")
    @Operation(summary = "Get detail transaksi by ID", description = "Mengambil satu data detail transaksi berdasarkan kode transaksi dan ID barang")
    public DetailTransaksi get(@PathVariable String kodeTransaksi, @PathVariable String idBarang) {
        return service.getById(kodeTransaksi, idBarang);
    }

    @GetMapping("/transaksi/{kodeTransaksi}")
    @Operation(summary = "Get detail transaksi by kode transaksi", description = "Mengambil semua detail untuk satu kode transaksi")
    public List<DetailTransaksi> getByKode(@PathVariable String kodeTransaksi) {
        return service.getByKodeTransaksi(kodeTransaksi);
    }

    @PostMapping
    @Operation(summary = "Create detail transaksi baru", description = "Menambahkan satu data detail transaksi baru")
    public DetailTransaksi create(@RequestBody DetailTransaksi detail) {
        return service.save(detail);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Bulk create detail transaksi", description = "Menambahkan banyak detail transaksi sekaligus")
    public List<DetailTransaksi> createBulk(@RequestBody List<DetailTransaksi> details) {
        return service.saveAll(details);
    }

    @PutMapping("/{kodeTransaksi}/{idBarang}")
    @Operation(summary = "Update detail transaksi", description = "Mengubah data detail transaksi")
    public DetailTransaksi update(@PathVariable String kodeTransaksi, @PathVariable String idBarang,
            @RequestBody DetailTransaksi detail) {
        return service.update(kodeTransaksi, idBarang, detail);
    }

    @DeleteMapping("/{kodeTransaksi}/{idBarang}")
    @Operation(summary = "Delete detail transaksi", description = "Menghapus data detail transaksi")
    public void delete(@PathVariable String kodeTransaksi, @PathVariable String idBarang) {
        service.delete(kodeTransaksi, idBarang);
    }
}
