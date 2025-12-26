package ui.ft.ccit.faculty.transaksi.barang.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarangRepository extends JpaRepository<Barang, String> {
    List<Barang> findByNamaContainingIgnoreCase(String nama);
}
