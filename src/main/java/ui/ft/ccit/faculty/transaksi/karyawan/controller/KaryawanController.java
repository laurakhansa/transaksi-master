package ui.ft.ccit.faculty.transaksi.karyawan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;

import java.util.List;

@RestController
@RequestMapping("/api/karyawan")
@Tag(name = "Karyawan", description = "API untuk manajemen data karyawan")
public class KaryawanController {

    private final KaryawanService service;

    public KaryawanController(KaryawanService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List semua karyawan", description = "Mengambil semua data karyawan tanpa pagination")
    public List<Karyawan> list() {
        return service.getAll();
    }

    @GetMapping("/pages")
    @Operation(summary = "List karyawan dengan pagination", description = "Mengambil data karyawan dengan pagination")
    public Page<Karyawan> listPaged(Pageable pageable) {
        return service.getAllPaged(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get karyawan by ID", description = "Mengambil satu data karyawan berdasarkan ID")
    public Karyawan get(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create karyawan baru", description = "Menambahkan satu data karyawan baru")
    public Karyawan create(@RequestBody Karyawan karyawan) {
        return service.save(karyawan);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Bulk create karyawan", description = "Menambahkan banyak data karyawan sekaligus")
    public List<Karyawan> createBulk(@RequestBody List<Karyawan> karyawanList) {
        return service.saveAll(karyawanList);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update karyawan", description = "Mengubah data karyawan yang sudah ada")
    public Karyawan update(@PathVariable String id, @RequestBody Karyawan karyawan) {
        return service.update(id, karyawan);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete karyawan", description = "Menghapus data karyawan berdasarkan ID")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
