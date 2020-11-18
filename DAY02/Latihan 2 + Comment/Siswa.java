public class Siswa {

    // Deklarasi dan Inisialisasi nilai variabel
    String sNama, sGender, sTglLahir, sKelas, sTanggalLahir;
    char cNilai;
    int iNilai = 0, iUmur = 0, iYear = 2000, iDate = 0, iMonth = 0;

    // Constructor Method, set Nama, Nilai, Gender, Tanggal Lahir, Bulan Lahir, Tahun Lahir
    public Siswa(String nama, int nilai, String gender, int date, int month, int year){
        // set nilai instance variable
        sNama = nama;
        iNilai = nilai;
        sGender = gender;
        iDate = date;
        iMonth = month;
        iYear = year;
        // proses melalui method
        iUmur = Umur(2020, year);
        cNilai = NilaiHuruf(nilai);
        sKelas = Kelas(iUmur);
        sTanggalLahir = TanggalLahir(date, month, year);
    }

    //Method yang mengembalikan nilai umur berdasarkan tahun lahir dan tahun saat ini
    public int Umur (int CurrentYear, int BirthYear){
        return CurrentYear - BirthYear;
    }

    //Method yang mengembalikan nilai huruf dari nilai angka yang ada di parameter
    public char NilaiHuruf(int Nilai){
        // Jika Nilai > 80 maka nilai huruf A, 70-79 nilai huruf B, 60-69 nilai huruf C, 50-59 nilai huruf D, dan 0-49 nilai huruf E   
        if(Nilai >=80){
            return 'A';
        }else if(Nilai >=70){
            return 'B';
        }else if(Nilai >=60){
            return 'c';
        }else if(Nilai >=50){
            return 'D';
        }else{
            return 'E';
        }
    }

    //Method yang mengembalikan keterangan kelas berdasarkan umur yang ada di parameter
    public String Kelas(int Umur){
        // switch mengembalikan nilai alam bentuk string berdasarkan umur
        switch (Umur) {
            case 15:
                return "SMA, Kelas X";
            case 16:
                return "SMA, Kelas XI";
            case 17:
                return "SMA, Kelas XII";
            case 18:
                return "Kuliah, Tingkat 1";
            case 19:
                return "Kuliah, Tingkat 2";
            case 20:
                return "Kuliah, Tingkat 3";
            case 21:
                return "Kuliah, Tingkat 4";
            case 22:case 23:case 24:
                return "Lulus, Sedang Bekerja";
            default:
                return "Umur Salah!";
        }
    }

    //Method yang mengembalikan string tanggal dengan format ex: 1 Januari 2000
    public String TanggalLahir(int Date, int Month, int Year){
        String sMonth;
        // switch untuk menyeleksi bulan dan set string bulan ke dalam variabel sMonth
        switch (Month) {
            case 1:
                sMonth = "Januari";
                break;
            case 2:
                sMonth = "Februari";
                break;
            case 3:
                sMonth = "Maret";
                break;
            case 4:
                sMonth = "April";
                break;
            case 5:
                sMonth = "Mei";
                break;
            case 6:
                sMonth = "Juni";
                break;
            case 7:
                sMonth = "Juli";
                break;
            case 8:
                sMonth = "Agustus";
                break;
            case 9:
                sMonth = "September";
                break;
            case 10:
                sMonth = "Oktober";
                break;
            case 11:
                sMonth = "November";
                break;
            case 12:
                sMonth = "Desember";
                break;
            default:
                sMonth = "Bulan Salah!";
                break;
        }
        return Date + " " + sMonth + " " + Year; // mengembalikan tanggal dalam format ex: 1 januari 2020
    }
    
    // Method untuk print history berdasarkan umur dan tahun lahir
    public void PrintHistory(int Umur, int Year){
        // perulangan untuk print tahun berdasarkan umur dan tahun lahir, setiap pertambahan tahun, umur juga bertambah 
        for (int i = 1; i <= Umur; i++) {
            System.out.println((Year + i) +" -> " + i + " Tahun");
        }
    }

    //method untuk print data Nama, Gender, Nilai Angka, Tanggal Lahir, Umur, Nilai Huruf, dan Kelas
    public void PrintData (){
        System.out.println("");
        System.out.println("Nama : " + sNama);
        System.out.println("Gender : " + sGender);
        System.out.println("Nilai : " + iNilai);
        System.out.println("");
        System.out.println("Tanggal Lahir : " + sTanggalLahir);
        System.out.println("Umur : " + iUmur);
        System.out.println("Nilai Huruf : " + cNilai);
        System.out.println("Kelas : " + sKelas);
        System.out.println("");
    }
}