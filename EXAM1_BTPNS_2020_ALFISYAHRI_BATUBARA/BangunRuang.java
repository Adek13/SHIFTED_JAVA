import java.io.*;
public class BangunRuang {
    static int iVolumeBalok = 0, iVolumeKubus = 0;
    static float fVolumeBola = 0;
    public static void main(String[] args) {
        InputStreamReader r=new InputStreamReader(System.in);    
        BufferedReader br=new BufferedReader(r);
        String menu = "0";       
        while (Integer.parseInt(menu) != 5) {
            System.out.println("Menu");
            System.out.println("=====================");
            System.out.println("");
            System.out.println("1. Volume Balok");
            System.out.println("2. Volume Bola");
            System.out.println("3. Volume Kubus");
            System.out.println("4. Average Summary");
            System.out.println("5. Exit");
            System.out.println("");
            System.out.print("Input Menu : ");
            try {
                menu = br.readLine();
                switch (Integer.parseInt(menu)) {
                    case 1:
                        System.out.print("Panjang : ");
                        int panjang = Integer.parseInt(br.readLine());
                        System.out.print("Lebar : ");
                        int lebar = Integer.parseInt(br.readLine());
                        System.out.print("Tinggi : ");
                        int tinggi = Integer.parseInt(br.readLine());
                        System.out.println(Volume(panjang, lebar, tinggi));
                        break;
                    case 2:
                        System.out.print("Jari-jari : ");
                        float jari = Float.parseFloat(br.readLine());
                        System.out.println(Volume(jari));
                        break;
                    case 3:
                        System.out.print("Rusuk : ");
                        int rusuk = Integer.parseInt(br.readLine());
                        System.out.println(Volume(rusuk));
                        break;
                    case 4:
                        Average_Summary();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }    
    public static int Volume(int panjang, int lebar, int tinggi){
        int Volume = panjang * lebar * tinggi;
        iVolumeBalok = Volume;
        return Volume;
    }
    public static float Volume(float jari){
        float Volume = (22/7) * jari * jari * jari;
        fVolumeBola = Volume;
        return Volume;
    }
    public static int Volume(int rusuk){
        int Volume = rusuk * rusuk * rusuk;
        iVolumeKubus = Volume;
        return Volume;
    }
    public static void Average_Summary(){
        float summary = (iVolumeBalok + fVolumeBola + iVolumeKubus);
        float average = summary / 3;
        System.out.println("Average : " + average);
        System.out.println("Summary : " + summary);
    }
}
