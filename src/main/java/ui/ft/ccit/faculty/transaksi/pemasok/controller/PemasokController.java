package ui.ft.ccit.faculty.transaksi.pemasok.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;

import java.util.List;

@RestController
@RequestMapping("/api/pemasok")
@Tag(name = "Pemasok", description = "API untuk manajemen data pemasok")
public class PemasokController {

    private final PemasokService service;

    public PemasokController(PemasokService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List semua pemasok", description = "Mengambil semua data pemasok tanpa pagination")
    public List<Pemasok> list() {
        return service.getAll();
    }

    @GetMapping("/pages")
    @Operation(summary = "List pemasok dengan pagination", description = "Mengambil data pemasok dengan pagination")
    public Page<Pemasok> listPaged(Pageable pageable) {
        return service.getAllPaged(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get pemasok by ID", description = "Mengambil satu data pemasok berdasarkan ID")
    public Pemasok get(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create pemasok baru", description = "Menambahkan satu data pemasok baru")
    public Pemasok create(@RequestBody Pemasok pemasok) {
        return service.save(pemasok);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Bulk create pemasok", description = "Menambahkan banyak data pemasok sekaligus")
    public List<Pemasok> createBulk(@RequestBody List<Pemasok> pemasokList) {
        return service.saveAll(pemasokList);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update pemasok", description = "Mengubah data pemasok yang sudah ada")
    public Pemasok update(@PathVariable String id, @RequestBody Pemasok pemasok) {
        return service.update(id, pemasok);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete pemasok", description = "Menghapus data pemasok berdasarkan ID")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
