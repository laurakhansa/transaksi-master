# Transaksi API Master

Sistem Informasi Transaksi Penjualan Barang dengan Spring Boot 4.0.0. Proyek ini mencakup manajemen data Barang, Jenis Barang, Pelanggan, Karyawan, Pemasok, dan Transaksi.

## 🚀 Fitur Utama

- **Lengkap CRUD**: Implementasi POST, PUT, DELETE, dan GET untuk semua entitas.
- **Relasi Tabel**: Penanganan data yang saling berelasi (misal: Transaksi dan Detail Transaksi).
- **Security**: Integrasi **OAuth 2.0 / JWT** menggunakan Google sebagai Identity Provider.
- **Dokumentasi API**: Swagger UI yang interaktif dengan dukungan Bearer Token.
- **CI/CD**: GitHub Actions untuk otomatisasi build dan test.

## 🛠️ Teknologi yang Digunakan

- **Backend**: Spring Boot 4 (Java 25)
- **Database**: MySQL
- **Dokumentasi**: SpringDoc OpenAPI (Swagger)
- **Security**: Spring Security OAuth2 Resource Server
- **Build Tool**: Maven

## 📖 Dokumentasi API

Setelah menjalankan aplikasi, Anda dapat mengakses dokumentasi API di:
- **Swagger UI**: `http://localhost:8085/swagger-ui.html`
- **JSON Docs**: `http://localhost:8085/api-docs`

### Cara Menggunakan Token di Swagger:
1. Klik tombol **Authorize**.
2. Masukkan token Anda: `Bearer YOUR_JWT_TOKEN`.
3. Klik **Authorize** dan tutup.

## 🧪 Cara Menjalankan Secara Lokal

1. Clone repositori:
   ```bash
   git clone https://github.com/USER/transaksi-master.git
   ```
2. Pastikan MySQL sudah berjalan dan buat database bernama `penjualan`.
3. Konfigurasi database di `src/main/resources/application-local.yaml`.
4. Jalankan aplikasi:
   ```bash
   ./mvnw spring-boot:run
   ```

## 👷 GitHub Actions Status

![Java CI with Maven](https://github.com/USER/transaksi-master/actions/workflows/maven.yml/badge.svg)

---

**Kontributor**: Laura Al Khansa
**Tugas Besar**: CCIT Faculty - Pengembangan Sistem Penjualan
