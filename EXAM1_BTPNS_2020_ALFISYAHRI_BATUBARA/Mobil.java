class MobilObject {
    int iTahun = 2000, iKecepatan = 0;
    String sMerek;

    MobilObject(int tahun, String merek, int kecepatan){
        this.iTahun = tahun;
        this.sMerek = merek;
        this.iKecepatan = kecepatan;
    }
    void TambahKecepatan (int increment){
        this.iKecepatan += increment;
        System.out.println("Kecepatan Saat Ini : " + this.iKecepatan);
    }
    void KurangKecepatan (int decrement){
        this.iKecepatan -= decrement;
        System.out.println("Kecepatan Saat Ini : " + this.iKecepatan);
    }
}
public class Mobil {
    public static void main(String[] args) {
        MobilObject mobil = new MobilObject(2015, "Avanza", 100);
        mobil.TambahKecepatan(20);
        mobil.KurangKecepatan(40);
    }
    
}
