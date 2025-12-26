package ui.ft.ccit.faculty.transaksi.pemasok.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PemasokRepository extends JpaRepository<Pemasok, String> {
}
