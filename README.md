## Janji
Saya Raditya Novandrian dengan NIM 2508283 mengerjakan TP-4 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan.

### Desain Class 
*   **Atribut Private:** Seluruh atribut pada class `Person` dibuat `private` untuk menjaga keamanan data.
*   **Getter & Setter:** Akses dan modifikasi data dilakukan melalui metode `public` berupa *getter* dan *setter*.

### Pengelolaan Koleksi Data
*   **Java (Swing):** Menggunakan struktur data `ArrayList<Person>` yang bersifat dinamis untuk menyimpan daftar objek orang secara sementara (di dalam memori saat program berjalan).

### UI
*   **Versi Desktop GUI:** Program berjalan sebagai aplikasi desktop (Window) menggunakan library **Java Swing**.
*   Antarmuka dibangun menggunakan elemen-elemen GUI seperti `JFrame` untuk *window* utama, `JPanel` untuk tata letak, `JTextField` dan `JComboBox` untuk form input, serta `JTable` untuk menampilkan data secara tabular yang interaktif.

---

## Penjelasan Atribut dan Methods
### Class Person 
*   **Deskripsi:** Merepresentasikan entitas individu/orang beserta informasi detailnya.
*   **Atribut (`private`):**
    *   `id` (String): Identifier unik seseorang 
    *   `nama` (String): Nama lengkap.
    *   `tahunLahir` (int): Tahun kelahiran.
    *   `kategori` (String): Kategori status orang tersebut.
    *   `email` (String): Alamat email (Atribut tambahan).
*   **Methods:** Memiliki Constructor untuk inisialisasi awal, serta kumpulan metode getter dan setter untuk mengakses atribut.

### Class PeopleMenu (GUI)
*   **Deskripsi:** Mewarisi dari JFrame. Mengatur seluruh tampilan visual, interaksi pengguna, dan logika pengolahan data.
*   **Atribut (Komponen GUI & State):**
    *   `mainPanel`, `personTable`, `addUpdateButton`, `deleteButton`, `cancelButton`: Komponen visual form.
    *   Komponen Input: `idField`, `namaField`, `tahunLahirField`, `kategoriComboBox`, `emailField`.
    *   `listPerson` (ArrayList): Tempat menyimpan objek `Person`.
    *   `selectedIndex` (int): Melacak indeks baris tabel yang sedang diklik.
*   **Methods Utama:** 
    *   `populateList()`: Mengisi data awal ke dalam list.
    *   `setTable()`: Menyusun ulang `DefaultTableModel` berdasarkan isi terbaru dari `ArrayList` untuk dirender ke `JTable`.
    *   `insertData()`: Mengambil teks dari form, memvalidasi tipe angka, membuat objek `Person` baru, dan memasukkannya ke list.
    *   `updateData()`: Memodifikasi data objek `Person` yang dipilih berdasarkan input form terbaru.
    *   `deleteData()`: Menghapus objek dari list berdasarkan baris yang diklik.
    *   `clearForm()`: Mengosongkan isian form dan mereset status tombol .

---

## Penjelasan Alur Program

1. **Inisialisasi Data Awal (Read):** Saat program pertama kali dijalankan, constructor `PeopleMenu` mengeksekusi `populateList()` untuk membuat beberapa objek `Person` dan menaruhnya ke dalam `ArrayList`. Method `setTable()` kemudian merender data tersebut ke dalam `JTable`.
2. **Interaksi Tabel (Pilih Data):** Jika pengguna mengklik salah satu baris pada tabel, `MouseListener` akan menangkap indeks baris tersebut (`selectedIndex`). Teks dari tabel akan diisikan secara otomatis ke kotak form di atasnya, tombol "Add" berubah menjadi "Update", dan tombol "Delete" akan dimunculkan.
3. **Alur Tambah Data (Create):** Jika tidak ada baris yang dipilih (`selectedIndex == -1`), menekan tombol "Add" akan memicu method `insertData()`. Data dari form diambil, dijadikan objek `Person` baru, lalu tabel direfresh.
4. **Alur Ubah Data (Update):** Jika sebuah baris sedang dipilih, menekan tombol "Update" akan memicu method `updateData()`. Data pada objek yang bersangkutan di `ArrayList` akan diperbarui sesuai teks di form, lalu tabel direfresh.
5. **Alur Hapus Data (Delete):** Menekan tombol "Delete" akan memunculkan pop-up **konfirmasi** (JOptionPane). Jika pengguna memilih "Yes", method `deleteData()` akan dieksekusi untuk menghapus elemen dari `ArrayList`, kemudian tabel direfresh dan form dikosongkan.

---

## Dokumentasi (Video)

<video src="Dokumentasi/dokumentasi.mp4" controls width="100%"></video>
