package ui.ft.ccit.faculty.transaksi.jenisbarang.model;

public class JenisBarangNotFoundException extends RuntimeException {

    public JenisBarangNotFoundException(Byte id) {
        super("Jenis Barang dengan id " + id + " tidak ditemukan");
    }
}
