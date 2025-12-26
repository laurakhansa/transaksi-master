package ui.ft.ccit.faculty.transaksi.detail_transaksi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detail_transaksi")
public class DetailTransaksi {

    @EmbeddedId
    private DetailTransaksiId id;

    @Column(name = "jumlah")
    private Integer jumlah;

    protected DetailTransaksi() {
        // for JPA
    }

    public DetailTransaksi(DetailTransaksiId id, Integer jumlah) {
        this.id = id;
        this.jumlah = jumlah;
    }

    public DetailTransaksiId getId() {
        return id;
    }

    public void setId(DetailTransaksiId id) {
        this.id = id;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}
