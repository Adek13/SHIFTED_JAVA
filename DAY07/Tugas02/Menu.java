import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;
public class Menu {
    static InputStreamReader r=new InputStreamReader(System.in);    
    static BufferedReader br=new BufferedReader(r);    
    static ArrayList<Mahasiswa> mMahasiswa = new ArrayList<>();
    //constructor(print menu)
    public static void main(String args[])throws Exception{
        try {
            MethodMenu();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void MethodMenu(){
        String menu = "0";
        try {
            while (Integer.parseInt(menu) != 4) {
                System.out.println("Menu");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Tambah Mahasiswa");
                System.out.println("2. Laporan Mahasiswa");
                System.out.println("3. Laporan Mahasiswa dan Tulis (Thread)");
                System.out.println("4. Exit");
                System.out.println("");
                System.out.print("Input Menu : ");
                menu = br.readLine();
                switch (Integer.parseInt(menu)) {
                    case 1:
                        BuatObject();
                        break;
                    case 2:
                        Laporan();
                        break;
                    case 3:
                        PrintLaporan();
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // method tambah mahasiswa(simpan ke map)
    public static void BuatObject(){
        try {
            System.out.println("Tambah Mahasiswa");
            System.out.println("=====================");
            System.out.println("");
            System.out.print("ID          : ");
            int id = Integer.parseInt(br.readLine());
            System.out.print("Nama        : ");
            String nama = br.readLine();
            System.out.print("Nilai Bahasa Inggris   : ");
            Double BhsInggris = Double.parseDouble(br.readLine());
            System.out.print("Nilai Fisika   : ");
            Double Fisika = Double.parseDouble(br.readLine());
            System.out.print("Nilai Algoritma : ");
            Double Algoritma = Double.parseDouble(br.readLine());
            Mahasiswa mahasiswa = new Mahasiswa(id, nama, BhsInggris, Fisika, Algoritma); //deklarasi object mahasiswa sementara untuk menampun data
            mMahasiswa.add(mahasiswa); //method put map/tambah data ke map dengan format (int key/id, Mahasiswa/object mahasiswa)
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void Laporan(){
        BubbleSort(mMahasiswa);
        PrintToScreen prt = new PrintToScreen(mMahasiswa);
        prt.start();
    }
    public static void BubbleSort(ArrayList<Mahasiswa> List){
        for (int i = 0; i < List.size() - 1; i++) {
            for (int j = 0; j < List.size() - 1 - i; j++) {
                if (List.get(j + 1).getId() < List.get(j).getId()) {
                    Mahasiswa temp = List.get(j);
                    List.set(j, List.get(j+1));
                    List.set(j+1, temp);
                }
            }
        }
    }
    public static void PrintLaporan(){
        BubbleSort(mMahasiswa);
        PrintToScreen Thread1 = new PrintToScreen(mMahasiswa);
        Print Thread2 = new Print(mMahasiswa);
        Thread1.start();
        Thread2.start();
    }
    public static int BinarySearch(ArrayList<Mahasiswa> List, int id) {
        BubbleSort(List);
        int target = id; // the element to be searched
        int left = 0;
        int middle = 0;
        int right = List.size() - 1;
        while (left <= right) {
            middle = (left + right) / 2;
            if (List.get(middle).getId() == target) {
                break;
            } else if (List.get(middle).getId() < target) {
                left = middle + 1;
            } else if (List.get(middle).getId() > target) {
                right = middle - 1;
            }
        }
        return middle;
    }
}
