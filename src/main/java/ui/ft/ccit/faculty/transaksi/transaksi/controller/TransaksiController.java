package ui.ft.ccit.faculty.transaksi.transaksi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;

import java.util.List;

@RestController
@RequestMapping("/api/transaksi")
@Tag(name = "Transaksi", description = "API untuk manajemen data transaksi")
public class TransaksiController {

    private final TransaksiService service;

    public TransaksiController(TransaksiService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List semua transaksi", description = "Mengambil semua data transaksi tanpa pagination")
    public List<Transaksi> list() {
        return service.getAll();
    }

    @GetMapping("/pages")
    @Operation(summary = "List transaksi dengan pagination", description = "Mengambil data transaksi dengan pagination")
    public Page<Transaksi> listPaged(Pageable pageable) {
        return service.getAllPaged(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get transaksi by ID", description = "Mengambil satu data transaksi berdasarkan ID")
    public Transaksi get(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create transaksi baru", description = "Menambahkan satu data transaksi baru")
    public Transaksi create(@RequestBody Transaksi transaksi) {
        return service.save(transaksi);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Bulk create transaksi", description = "Menambahkan banyak data transaksi sekaligus")
    public List<Transaksi> createBulk(@RequestBody List<Transaksi> transaksiList) {
        return service.saveAll(transaksiList);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update transaksi", description = "Mengubah data transaksi yang sudah ada")
    public Transaksi update(@PathVariable String id, @RequestBody Transaksi transaksi) {
        return service.update(id, transaksi);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete transaksi", description = "Menghapus data transaksi berdasarkan ID")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
