# 🪢 Sistem Gamifikasi Tarik Tambang — Evaluasi Advanced English Grammar \& Vocabulary

Aplikasi berbasis **Java CLI** yang mensimulasikan permainan **tarik tambang** sebagai media evaluasi kuis Bahasa Inggris Fase F (Kelas XI–XII).

\---

## 📋 Deskripsi

Setiap jawaban yang benar akan menggeser tali ke sisi Siswa, sedangkan jawaban salah menggeser tali ke sisi CPU. Siswa menang jika berhasil menarik tali ke sisi mereka setelah menyelesaikan 10 soal pilihan ganda bertipe **Grammar** atau **Vocabulary**.

\---

## ✨ Fitur

### 👤 Admin

* Login dengan username \& password
* Tambah soal baru (tipe Grammar / Vocabulary) beserta opsi A–D dan kunci jawaban
* Simpan soal ke file eksternal (bank soal)
* Lihat Leaderboard berdasarkan tipe soal

### 🎓 Siswa

* Masuk menggunakan username (tanpa password)
* Pilih mode kuis: Grammar atau Vocabulary
* Kerjakan 10 soal acak dengan feedback visual tarik tambang secara real-time
* Lihat hasil akhir: skor, jumlah benar/salah, dan status Menang / Kalah / Seri
* Lihat Leaderboard

\---

## ⚙️ Cara Menjalankan

### Prasyarat

* **Java JDK 17** atau lebih baru sudah terinstal
* Pastikan file bank soal tersedia di direktori yang sesuai

### Langkah

1. **Clone repository ini**

```bash
   git clone https://github.com/username/nama-repo.git
   cd nama-repo
   ```

2. **Compile semua file Java**

```bash
   javac -d out src/\\\*\\\*/\\\*.java
   ```

3. **Jalankan aplikasi**

```bash
   java -cp out Main
   ```

---
*Proyek ini disusun untuk Kebutuhan Tugas Kelompok Mata Kuliah **Pemrograman Berorientasi Objek** - 
Pendidikan Teknik Informatika dan Komputer, Universitas Negeri Jakarta*
