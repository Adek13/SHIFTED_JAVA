public class Karyawan { // inisialisasi class
    // <-- deklarasi variabel/state -->
    String  nama; // variabel tipe string
    String  jabatan;
    int     gaji = 0;// variabel tipe integer
    // <-- ! -->
    public Karyawan (String initNama, String initJabatan, int initGaji){ // inisialisasi method constructor dengan 3 parameter initNama, initJabatan, initGaji
        // set variabel sebagai initial state saat object dibuat
        nama = initNama;
        jabatan = initJabatan;
        gaji = initGaji;
    }

    void SetNama (String newValue){ //inisialisasi method untuk mengganti nilai variabel nama
        nama = newValue;
    }
    String GetNama (){ //inisialisasi method untuk mengembalikan nilai dari variabel nama dengan tipe string
        return nama;
    }
    void SetJabatan (String newValue){ //inisialisasi method untuk mengganti nilai variabel jabatan
        jabatan = newValue;
    }
    String GetJabatan (){ //inisialisasi method untuk mengembalikan nilai dari variabel jabatan dengan tipe string
        return jabatan;
    }
    void SetGaji (int newValue){ //inisialisasi method untuk mengganti nilai variabel gaji
        gaji = newValue;
    }
    int GetGaji (){ //inisialisasi method untuk mengembalikan nilai dari variabel gaji dengan tipe integer
        return gaji;
    }

    void PrintKaryawan (){ //method untuk menghasilkan output nama, jabatan, dan gaji
        System.out.println("Nama: " + nama);
        System.out.println("Jabatan: " + jabatan);
        System.out.println("Gaji: " + gaji);
        System.out.println("");
    }
}
