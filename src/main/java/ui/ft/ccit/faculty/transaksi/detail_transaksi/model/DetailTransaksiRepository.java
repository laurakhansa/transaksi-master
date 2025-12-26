package ui.ft.ccit.faculty.transaksi.detail_transaksi.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTransaksiRepository extends JpaRepository<DetailTransaksi, DetailTransaksiId> {
    List<DetailTransaksi> findByIdKodeTransaksi(String kodeTransaksi);
}
