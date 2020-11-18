public class MobilDemo {
    public static void main(String[] args) {

        Mobil mobil1 = new Mobil();
        Mobil mobil2 = new Mobil();

        mobil1.setMobil("Avanza", "Hitam");
        mobil2.setMobil("Xenia", "Putih");
        mobil1.tambahGigi(2);
        mobil2.tambahGigi(1);
        mobil1.tambahKecepatan(50);
        mobil2.tambahKecepatan(30);
        mobil1.printStates();
        mobil2.printStates();
    }
}
