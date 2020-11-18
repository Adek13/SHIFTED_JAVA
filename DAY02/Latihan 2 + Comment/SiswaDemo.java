public class SiswaDemo {
    public static void main(String[] args) {
        Siswa breach = new Siswa("Breach", 75, "Laki-laki", 13, 7, 2000); // Membuat object baru dengan nama object breach dengan variabel constructor

        breach.PrintData(); // Print data object yaitu Nama, Nilai Angka, Jenis Kelamin, Umur, Tanggal Lahir, Nilai Huruf, dan Kelas
        breach.PrintHistory(breach.iUmur, breach.iYear); // Print history dari object breach berdasarkan umur dan tanggal lahir
    }
}
