package ui.ft.ccit.faculty.transaksi.jenisbarang.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisBarangRepository extends JpaRepository<JenisBarang, Byte> {
}
