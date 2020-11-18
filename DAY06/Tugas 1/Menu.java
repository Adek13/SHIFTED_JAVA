import java.io.*;
import java.util.*;
public class Menu extends Staff{
    static InputStreamReader r=new InputStreamReader(System.in);    
    static BufferedReader br=new BufferedReader(r);
    static Map<Integer, Staff> wWorker=new HashMap<Integer, Staff>();
    Menu(){
        super();
    }
    public static void main(String[] args) {
        try {
            String menu = "0";       
            while (Integer.parseInt(menu) != 4) {
                System.out.println("Menu");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Buat Staff");
                System.out.println("2. Tambah Absensi");
                System.out.println("3. Laporan Staff");
                System.out.println("4. Exit");
                System.out.println("");
                System.out.print("Input Menu : ");
                menu = br.readLine();
                switch (Integer.parseInt(menu)) {
                    case 1:
                        TambahStaff();
                        break;
                    case 2:
                        TambahAbsensi();
                        break;
                    case 3:
                        Laporan();
                        break;
                    case 4:
                        
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } 
    
    public static void TambahStaff(){
        try {
            System.out.print("Input ID : ");
            int id = Integer.parseInt(br.readLine());
            System.out.print("Input Nama : ");
            String nama = br.readLine();
            System.out.print("Input Jabatan : ");
            String jabatan = br.readLine();
            Staff staff = new Staff(id, nama, jabatan);
            wWorker.put(id, staff);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void TambahAbsensi(){
        try {
            System.out.print("Input ID : ");
            int id = Integer.parseInt(br.readLine());
            Staff absen = (Staff)wWorker.get(id);
            absen.tambahAbsensi();
            System.out.print("Tambah Absensi Berhasil!");
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void Laporan(){
        try {
            String output = "";
            String format = "| %-2d | %-11s | %-10s | %-2d           |%n";
            System.out.format("+----+-------------+------------+--------------+%n");
            System.out.format("| ID |  Nama       | Jabatan    | Absensi/Hari |%n");
            System.out.format("+----+-------------+------------+--------------+%n");
            Set set = wWorker.entrySet();
            Iterator itr = set.iterator();
            while(itr.hasNext()){
                Map.Entry entry = (Map.Entry)itr.next();  
                Staff staffs = (Staff)entry.getValue();
                System.out.format(format, staffs.getId(), staffs.GetNama(), staffs.getJabatan(), staffs.getAbsensi());
            }
            System.out.format("+----+-------------+------------+--------------+%n");
            System.out.print("Tekan Enter!");
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}