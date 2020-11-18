public class Mahasiswa {
    String Nama;
    String JenisKelamin;
    int Umur;

    public Mahasiswa(String initNama, String initJenisKelamin, int initUmur){
        Nama = initNama;
        JenisKelamin = initJenisKelamin;
        Umur = initUmur;
    }
    void SetNama (String newValue){
        Nama = newValue;
    }
    String GetNama (){
        return Nama;
    }
    void SetJK (String newValue){
        JenisKelamin = newValue;
    }
    String GetJK (){
        return JenisKelamin;
    }
    void SetUmur (int newValue){
        Umur = newValue;
    }
    int GetUmur (){
        return Umur;
    }

    void printMahasiswa() {
        System.out.println("Nama:" + Nama);
        System.out.println("Jenis Kelamin:" + JenisKelamin);
        System.out.println("Umur:" + Umur);
   }
}