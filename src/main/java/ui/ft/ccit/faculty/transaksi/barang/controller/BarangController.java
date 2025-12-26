package ui.ft.ccit.faculty.transaksi.barang.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;

import java.util.List;

@RestController
@RequestMapping("/api/barang")
@Tag(name = "Barang", description = "API untuk manajemen data barang")
public class BarangController {

    private final BarangService service;

    public BarangController(BarangService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List semua barang", description = "Mengambil semua data barang tanpa pagination")
    public List<Barang> list() {
        return service.getAll();
    }

    @GetMapping("/pages")
    @Operation(summary = "List barang dengan pagination", description = "Mengambil data barang dengan dukungan pagination")
    public Page<Barang> listPaged(Pageable pageable) {
        return service.getAllPaged(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get barang by ID", description = "Mengambil satu data barang berdasarkan ID")
    public Barang get(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search barang by nama", description = "Mencari data barang berdasarkan nama (case-insensitive)")
    public List<Barang> search(@RequestParam String q) {
        return service.searchByNama(q);
    }

    @PostMapping
    @Operation(summary = "Create barang baru", description = "Menambahkan satu data barang baru")
    public Barang create(@RequestBody Barang barang) {
        return service.save(barang);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Bulk create barang", description = "Menambahkan banyak data barang sekaligus")
    public List<Barang> createBulk(@RequestBody List<Barang> barangList) {
        return service.saveAll(barangList);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update barang", description = "Mengubah data barang yang sudah ada")
    public Barang update(@PathVariable String id, @RequestBody Barang barang) {
        return service.update(id, barang);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete barang", description = "Menghapus data barang berdasarkan ID")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
