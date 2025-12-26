package ui.ft.ccit.faculty.transaksi.pelanggan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;

import java.util.List;

@RestController
@RequestMapping("/api/pelanggan")
@Tag(name = "Pelanggan", description = "API untuk manajemen data pelanggan")
public class PelangganController {

    private final PelangganService service;

    public PelangganController(PelangganService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List semua pelanggan", description = "Mengambil semua data pelanggan tanpa pagination")
    public List<Pelanggan> list() {
        return service.getAll();
    }

    @GetMapping("/pages")
    @Operation(summary = "List pelanggan dengan pagination", description = "Mengambil data pelanggan dengan pagination")
    public Page<Pelanggan> listPaged(Pageable pageable) {
        return service.getAllPaged(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get pelanggan by ID", description = "Mengambil satu data pelanggan berdasarkan ID")
    public Pelanggan get(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create pelanggan baru", description = "Menambahkan satu data pelanggan baru")
    public Pelanggan create(@RequestBody Pelanggan pelanggan) {
        return service.save(pelanggan);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Bulk create pelanggan", description = "Menambahkan banyak data pelanggan sekaligus")
    public List<Pelanggan> createBulk(@RequestBody List<Pelanggan> pelangganList) {
        return service.saveAll(pelangganList);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update pelanggan", description = "Mengubah data pelanggan yang sudah ada")
    public Pelanggan update(@PathVariable String id, @RequestBody Pelanggan pelanggan) {
        return service.update(id, pelanggan);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete pelanggan", description = "Menghapus data pelanggan berdasarkan ID")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
