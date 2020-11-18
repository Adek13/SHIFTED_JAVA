public class Mobil {
    String merek;
    String warna;
    Integer gigi = 0;
    Integer kecepatan = 0;

    void setMobil(String newMerek, String newWarna){
        merek = newMerek;
        warna = newWarna;
    }
    void tambahGigi (Integer increment){
        gigi = gigi + increment;
    }

    void kurangGigi (Integer decrement){
        gigi = gigi - decrement;
    }

    void tambahKecepatan (Integer newValue){
        kecepatan = kecepatan + newValue;
    }
    void kurangKecepatan (Integer newValue){
        kecepatan = kecepatan - newValue;
    }
    void printStates() {
        System.out.println("merek:" +
            merek + " warna:" + 
            warna + " gigi:" + gigi + " kecepatan:" + kecepatan);
   }
}
