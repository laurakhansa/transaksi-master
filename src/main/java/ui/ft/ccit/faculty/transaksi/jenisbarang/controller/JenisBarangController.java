package ui.ft.ccit.faculty.transaksi.jenisbarang.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;

import java.util.List;

@RestController
@RequestMapping("/api/jenis-barang")
@Tag(name = "Jenis Barang", description = "API untuk manajemen jenis barang")
public class JenisBarangController {

    private final JenisBarangService service;

    public JenisBarangController(JenisBarangService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List semua jenis barang", description = "Mengambil semua data jenis barang")
    public List<JenisBarang> list() {
        return service.getAll();
    }

    @GetMapping("/pages")
    @Operation(summary = "List jenis barang dengan pagination", description = "Mengambil data jenis barang dengan pagination")
    public Page<JenisBarang> listPaged(Pageable pageable) {
        return service.getAllPaged(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get jenis barang by ID", description = "Mengambil satu data jenis barang berdasarkan ID")
    public JenisBarang get(@PathVariable Byte id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create jenis barang baru", description = "Menambahkan satu data jenis barang baru")
    public JenisBarang create(@RequestBody JenisBarang jenis) {
        return service.save(jenis);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Bulk create jenis barang", description = "Menambahkan banyak jenis barang sekaligus")
    public List<JenisBarang> createBulk(@RequestBody List<JenisBarang> jenisList) {
        return service.saveAll(jenisList);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update jenis barang", description = "Mengubah data jenis barang")
    public JenisBarang update(@PathVariable Byte id, @RequestBody JenisBarang jenis) {
        return service.update(id, jenis);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete jenis barang", description = "Menghapus data jenis barang")
    public void delete(@PathVariable Byte id) {
        service.delete(id);
    }
}
