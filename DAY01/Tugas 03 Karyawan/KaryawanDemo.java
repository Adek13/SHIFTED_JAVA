public class KaryawanDemo { //inisialisasi class KaryawanDemo
    
    public static void main(String[] args) { //main method
        Karyawan manager = new Karyawan("breach", "Manager", 10000000); //inisialisasi object baru dari class Karyawan
        Karyawan supervisor = new Karyawan("viper", "Supervisor", 7000000); //inisialisasi object baru dari class Karyawan
        Karyawan staff = new Karyawan("sage", "Staff", 5000000); //inisialisasi object baru dari class Karyawan

        manager.PrintKaryawan(); //menjalankan method PrintKaryawan dari object
        supervisor.PrintKaryawan();//menjalankan method PrintKaryawan dari object
        staff.PrintKaryawan();//menjalankan method PrintKaryawan dari object
    }

}
