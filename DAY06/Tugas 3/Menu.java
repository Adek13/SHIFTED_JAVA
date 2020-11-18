import java.io.*;
import java.util.*;
public class Menu{
    static InputStreamReader r=new InputStreamReader(System.in);    
    static BufferedReader br=new BufferedReader(r);
    static Map<Integer, Staff> Staffs=new HashMap<Integer, Staff>();
    static Map<Integer, Manager> Managers=new HashMap<Integer, Manager>();
    static Integer iTotalGajiManager = 0;
    static Integer iTotalGajiStaff = 0;
    public static void main(String[] args) {
        try {
            String menus = "0";       
            while (Integer.parseInt(menus) < 6) {
                CLS();
                System.out.flush();
                System.out.println("Menu");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Tambah Karyawan");
                System.out.println("2. Tambah Absensi");
                System.out.println("3. Hitung Tunjangan");
                System.out.println("4. Hitung Total Gaji");
                System.out.println("5. Laporan dan Tulis Ke File");
                System.out.println("6. Exit");
                System.out.println("");
                System.out.print("Input Menu : ");
                menus = br.readLine();
                switch (Integer.parseInt(menus)) {
                    case 1:
                        TambahKaryawan();
                        break;
                    case 2:
                        TambahAbsensi();
                        break;
                    case 3:
                        HitungTunjangan();
                        break;
                    case 4:
                        HitungTotalGaji();
                        break;
                    case 5:
                        Laporan();
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } 
    
    public static void CLS() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    public static void TambahKaryawan(){
        String menu = "0", nama;
        int id = 0;
        while(!menu.equals("3")){
            try {
                CLS();
                System.out.println("Tambah Karyawan");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Tambah Manager");
                System.out.println("2. Tambah Staff");
                System.out.println("3. << Back\n");
                System.out.print("Input Pilihan : ");
                menu = br.readLine();
                switch (Integer.parseInt(menu)) {
                    case 1:
                        System.out.print("Input ID Karyawan : ");
                        id = Integer.parseInt(br.readLine());
                        System.out.print("Input Nama : ");
                        nama = br.readLine();
                        Manager manager = new Manager(id, nama);
                        Managers.put(id, manager);
                        break;
                    case 2:
                        System.out.print("Input ID Karyawan : ");
                        id = Integer.parseInt(br.readLine());
                        System.out.print("Input Nama : ");
                        nama = br.readLine();
                        Staff staff = new Staff(id, nama);
                        Staffs.put(id, staff);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void TambahAbsensi(){
        String menu = "0";
        int id = 0;
        while(!menu.equals("3")){
            try {
                CLS();
                System.out.println("Tambah Absensi");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Absensi Manager");
                System.out.println("2. Absensi Staff");
                System.out.println("3. << Back\n");
                System.out.print("Input Pilihan : ");
                menu = br.readLine();
                switch (Integer.parseInt(menu)) {
                    case 1:
                        System.out.print("Input ID Karyawan : ");
                        id = Integer.parseInt(br.readLine());
                        Managers.get(id).AbsensiMethod();
                        break;
                    case 2:
                        System.out.print("Input ID Karyawan : ");
                        id = Integer.parseInt(br.readLine());
                        Staffs.get(id).AbsensiMethod();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void HitungTunjangan(){
        String menu = "0";
        int id = 0;
        while(!menu.equals("3")){
            try {
                CLS();
                System.out.println("Hitung Tunjangan");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Tunjangan Manager");
                System.out.println("2. Tunjangan Staff");
                System.out.println("3. << Back\n");
                System.out.print("Input Pilihan : ");
                menu = br.readLine();
                switch (Integer.parseInt(menu)) {
                    case 1:
                        String submenu = "0";
                        while (!submenu.equals("3")) {
                            CLS();
                            System.out.println("Tunjangan Manager");
                            System.out.println("=====================");
                            System.out.println("");
                            System.out.println("1. Tunjangan Transport");
                            System.out.println("2. Tunjangan Entertaint");
                            System.out.println("3. << Back\n");
                            System.out.print("Input Pilihan : ");
                            submenu = br.readLine();
                            switch (Integer.parseInt(submenu)) {
                                case 1:
                                    System.out.print("Input ID : ");
                                    id = Integer.parseInt(br.readLine());
                                    Managers.get(id).HitungTunjanganTransport();
                                    break;
                                case 2:
                                    System.out.print("Input ID : ");
                                    id = Integer.parseInt(br.readLine());
                                    System.out.print("Input Jumlah Entertaint : ");
                                    int jumlah = Integer.parseInt(br.readLine());
                                    Managers.get(id).HitungTunjanganEntertaint(jumlah);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case 2:
                        System.out.print("Input ID Karyawan : ");
                        id = Integer.parseInt(br.readLine());
                        Staffs.get(id).HitungTunjanganMakan();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    static void HitungTotalGaji(){
        String menu = "0";
        while(!menu.equals("3")){
            try {
                CLS();
                System.out.println("Hitung Total Gaji");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Total Gaji Manager");
                System.out.println("2. Total Gaji Staff");
                System.out.println("3. << Back\n");
                System.out.print("Input Pilihan : ");
                menu = br.readLine();
                switch (Integer.parseInt(menu)) {
                    case 1:
                        Set set = Managers.entrySet();
                        Iterator itr = set.iterator();
                        iTotalGajiManager = 0;
                        while(itr.hasNext()){
                            Map.Entry entry = (Map.Entry)itr.next();
                            Manager data = (Manager)entry.getValue();
                            // System.out.println("Absen : " + data.getAbsen() + " Gapok : " + data.getGapok() + " Tunj Pulsa : " + data.getTunjPulsa() + " Tunj Transport : " + data.getTunjTransport() + " Tunj Entertaint : " + data.getTunjEntertaint());
                            int total = data.getGapok() + data.getTunjPulsa() + data.getTunjTransport() + data.getTunjEntertaint();
                            iTotalGajiManager = iTotalGajiManager + total;
                        }
                        System.out.print("Berhasil Mendapatkan Total! Tekan Enter! ");
                        br.readLine();
                        break;
                    case 2:
                        Set sets = Staffs.entrySet();
                        Iterator itrs = sets.iterator();
                        iTotalGajiManager = 0;
                        while(itrs.hasNext()){
                            Map.Entry entry = (Map.Entry)itrs.next();
                            Staff data = (Staff)entry.getValue();
                            // System.out.println("Absen : " + data.getAbsen() + " Gapok : " + data.getGapok() + " Tunj Pulsa : " + data.getTunjPulsa() + " Tunj Pulsa : " + data.getTunjPulsa());
                            int total = data.getGapok() + data.getTunjPulsa() + data.getTunjMakan();
                            iTotalGajiStaff = iTotalGajiStaff + total;
                        }
                        System.out.print("Berhasil Mendapatkan Total! Tekan Enter! ");
                        br.readLine();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void Laporan(){
        try {
            CLS();
            String output = "";
            String format = "| %-2d | %-11s | %-10s | %-5d   | %-1d    |%n";
            output += output.format("+----+-------------+------------+---------+------------+%n");
            output += output.format("| ID |  Nama       | Jabatan    | Absensi | Gaji Total |%n");
            output += output.format("+----+-------------+------------+---------+------------+%n");
            Set set = Managers.entrySet();
            Iterator itr = set.iterator();
            while(itr.hasNext()){
                Map.Entry entry = (Map.Entry)itr.next();  
                Manager data = (Manager)entry.getValue();
                output += output.format(format, data.getId(), data.getNama(), "Manager", data.getAbsen(), (data.getGapok() + data.getTunjPulsa() + data.getTunjTransport() + data.getTunjEntertaint()));
            }
            set = Staffs.entrySet();
            itr = set.iterator();
            while(itr.hasNext()){
                Map.Entry entry = (Map.Entry)itr.next();  
                Staff data = (Staff)entry.getValue();
                output += output.format(format, data.getId(), data.getNama(), "Staff", data.getAbsen(), (data.getGapok() + data.getTunjPulsa() + data.getTunjPulsa()));
            }
            output += output.format("+----+-------------+------------+---------+------------+%n");
            System.out.println(output);
            FileWriter fw=new FileWriter(".\\Employee.txt");
            fw.write(output);
            fw.close();
            System.out.print("Tekan Enter!");
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}