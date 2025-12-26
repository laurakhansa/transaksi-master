# Laporan Project DSP/Distributed System - Video Streaming Service

## 1. System Analysis

**Latar Belakang:**
Dalam era digital saat ini, konsumsi konten multimedia, khususnya video, meningkat pesat. Pengguna membutuhkan akses yang cepat, stabil, dan terpusat untuk menikmati konten video favorit mereka. Sistem distribusi video tradisional seringkali terkendala oleh latensi dan skalabilitas yang kurang memadai.

**Tujuan Pengembangan:**
Pengembangan aplikasi "Video Streaming Service" ini bertujuan untuk menyediakan platform streaming video yang handal dan dapat diakses (scalable). API ini dirancang untuk:
1.  Mengelola penyimpanan dan pengambilan data video secara efisien.
2.  Menyediakan akses video streaming dengan buffering minimal.
3.  Memfasilitasi interaksi pengguna melalui sistem otentikasi dan manajemen playlist.

**Alasan Pembuatan API:**
API (Application Programming Interface) diperlukan untuk memisahkan logika bisnis backend dengan presentasi frontend (Decoupled Architecture). Hal ini memungkinkan:
-   **Fleksibilitas Frontend:** Backend yang sama dapat digunakan oleh aplikasi Web, Mobile (Android/iOS), maupun Smart TV.
-   **Skalabilitas:** Layanan dapat dikembangkan (scaled) secara independen tanpa mengganggu antarmuka pengguna.
-   **Integrasi Pihak Ketiga:** Memudahkan integrasi dengan layanan lain di masa depan.

## 2. Schematic Diagram

Berikut adalah skema database yang digunakan dalam sistem:

**Tabel-tabel Utama:**

1.  **`users`**
    *   *Peran:* Menyimpan informasi pengguna untuk otentikasi dan profil.
    *   *Kolom Utama:* `id` (PK), `username`, `email`, `password_hash`, `role` (ADMIN/USER).

2.  **`videos`**
    *   *Peran:* Menyimpan metadata video yang diunggah.
    *   *Kolom Utama:* `id` (PK), `title`, `description`, `url_path`, `thumbnail_url`, `uploader_id` (FK), `views_count`, `upload_date`.

3.  **`categories`**
    *   *Peran:* Mengelompokkan video ke dalam kategori/genre.
    *   *Kolom Utama:* `id` (PK), `name`, `description`.

4.  **`video_categories`** (Pivot Table)
    *   *Peran:* Menangani relasi Many-to-Many antara Video dan Kategori.
    *   *Kolom Utama:* `video_id` (FK), `category_id` (FK).

5.  **`comments`**
    *   *Peran:* Menyimpan interaksi/komentar pengguna pada video.
    *   *Kolom Utama:* `id` (PK), `video_id` (FK), `user_id` (FK), `content`, `created_at`.

*(Disarankan membuat diagram visual menggunakan Designer phpMyAdmin atau tool ERD lain sesuai deskripsi tabel di atas)*

## 3. Technology Used

**Core Technology:**
-   **Versi JDK:** 21 (LTS)
-   **Jenis Project:** Maven
-   **Framework:** Spring Boot 3.3.x
-   **Database:** MySQL 8.0
-   **Packaging:** JAR
-   **Configuration:** YAML (`application.yaml`)

**Fitur Tambahan:**
-   **Docker:** `openjdk:21-jdk-slim` (untuk containerisasi aplikasi).
-   **Otentikasi:** JWT (JSON Web Token) dengan Spring Security untuk keamanan stateless yang skalabel.

## 4. Endpoints

Berikut adalah daftar endpoint utama pada Controller:

**Video Controller (`/api/videos`):**
-   `GET /api/videos` - Mendapatkan daftar semua video (support pagination).
-   `GET /api/videos/{id}` - Mendapatkan detail satu video berdasarkan ID.
-   `GET /api/videos/stream/{id}` - Melakukan streaming konten video.
-   `POST /api/videos` - Mengunggah video baru (Multipart file).
-   `PUT /api/videos/{id}` - Memperbarui metadata video.
-   `DELETE /api/videos/{id}` - Menghapus video.

**Auth Controller (`/api/auth`):**
-   `POST /api/auth/register` - Mendaftarkan pengguna baru.
-   `POST /api/auth/login` - Masuk dan mendapatkan Token JWT.

**Comment Controller (`/api/videos/{videoId}/comments`):**
-   `GET /api/videos/{videoId}/comments` - Melihat komentar pada video tertentu.
-   `POST /api/videos/{videoId}/comments` - Menambahkan komentar baru.

## 5. User Interface Design

Rancangan UI menggunakan tema "Dark Mode" yang modern untuk kenyamanan menonton, mirip dengan platform streaming populer.

**Elemen Kunci:**
-   **Hero Banner:** Menampilkan video featured/trending di bagian atas.
-   **Grid Layout:** Thumbnail video tersusun rapi dengan Judul dan Durasi.
-   **Sidebar Navigation:** Akses cepat ke Home, Trending, Subscriptions, dan Library.
-   **Responsive Player:** Pemutar video yang responsif dengan kontrol playback.

*(Sertakan screenshot mock-up UI yang telah dibuat di sini)*
