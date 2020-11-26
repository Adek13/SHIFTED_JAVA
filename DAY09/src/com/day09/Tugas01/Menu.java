package com.day09.Tugas01;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class Menu{
    static InputStreamReader r=new InputStreamReader(System.in);    
    static BufferedReader br=new BufferedReader(r);
    static ArrayList<Staff> Staffs = new ArrayList<>();
    static ArrayList<Manager> Managers = new ArrayList<>();
    static Integer iTotalGajiManager = 0;
    static Integer iTotalGajiStaff = 0;
    public static void main(String[] args) {
        try {
            String menus = "0";       
            while (Integer.parseInt(menus) < 4) {
                CLS();
                System.out.flush();
                System.out.println("Menu");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Create Worker");
                System.out.println("2. Create JSON Format and Write to File");
                System.out.println("3. Read JSON Format from a File");
                System.out.println("4. Exit");
                System.out.println("");
                System.out.print("Input Menu : ");
                menus = br.readLine();
                switch (Integer.parseInt(menus)) {
                    case 1:
                        TambahKaryawan();
                        break;
                    case 2:
                        CreateJson();
                        break;
                    case 3:
                        ReadJson();
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
                        String telp = "";
                        ArrayList telps = new ArrayList();
                        while(!telp.equals("done")){
                            System.out.println("Input \"done\" Ketika Selesai");
                            System.out.print("Input No Telepon : ");
                            String temp = br.readLine();
                            if (temp.equals("done")){
                                break;
                            }else{
                                telps.add(temp);
                            }
                        }
                        Manager manager = new Manager(id, nama, telps);
                        Managers.add(manager);
                        break;
                    case 2:
                        System.out.print("Input ID Karyawan : ");
                        id = Integer.parseInt(br.readLine());
                        System.out.print("Input Nama : ");
                        nama = br.readLine();
                        String email = "";
                        JSONArray emails = new JSONArray();
                        while(!email.equals("done")){
                            System.out.println("Input \"done\" Ketika Selesai");
                            System.out.print("Input Email : ");
                            String temp = br.readLine();
                            if (temp.equals("done")){
                                break;
                            }else{
                                emails.add(temp);
                            }
                        }
                        Staff staff = new Staff(id, nama, emails);
                        Staffs.add(staff);
                        break;
                    case 3:
//                        Laporan();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void CreateJson(){
        JSONArray arrManager = new JSONArray();
        for (Manager man:Managers) {
            JSONObject tempManager = new JSONObject();
            tempManager.put("ID", man.getId());
            tempManager.put("Nama", man.getNama());
            tempManager.put("Tunjangan Pulsa", man.getTunjPulsa());
            tempManager.put("Gaji Pokok", man.getGapok());
            tempManager.put("Absensi Hari", man.getAbsen());
            tempManager.put("Tunjangan Transport", man.getTunjTransport());
            tempManager.put("Tunjangan Entertaint", man.getTunjEntertaint());
            JSONArray tempTelepon = new JSONArray();
            for (String data:man.getTelp()){
                tempTelepon.add(data);
            }
            tempManager.put("No Telepon", tempTelepon);
            arrManager.add(tempManager);
        }
        JSONArray arrstaff = new JSONArray();
        for (Staff staf:Staffs) {
            JSONObject tempStaff = new JSONObject();
            tempStaff.put("ID", staf.getId());
            tempStaff.put("Nama", staf.getNama());
            tempStaff.put("Tunjangan Pulsa", staf.getTunjPulsa());
            tempStaff.put("Gaji Pokok", staf.getGapok());
            tempStaff.put("Absensi Hari", staf.getAbsen());
            tempStaff.put("Tunjangan Makan", staf.getTunjMakan());
            JSONArray tempEmail = new JSONArray();
            for (String data:staf.getEmail()){
                tempEmail.add(data);
            }
            tempStaff.put("Email", tempEmail);
            arrstaff.add(tempStaff);
        }
        try {
            String output1 = arrManager.toJSONString();
            String output2 = arrstaff.toString();
            FileWriter fw = new FileWriter("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY09\\src\\com\\day09\\Tugas01\\Manager.txt");
            FileWriter fw1 = new FileWriter("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY09\\src\\com\\day09\\Tugas01\\Staff.txt");
            fw.write(output1);
            fw.flush();
            fw.close();
            fw1.write(output2);
            fw1.flush();
            fw1.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void ReadJson(){
        try {
            System.out.print("Input File Name : ");
            String file = br.readLine();
            FileReader fr = new FileReader("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY09\\src\\com\\day09\\Tugas01\\" + file);
            int i;
            String input = "";
            while((i=fr.read())!=-1){ // Perulangan untuk menyimpan karakter ke variabel data
                input += (char)i;
            }
            JSONArray arr= (JSONArray) JSONValue.parse(input);
            for (int j = 0; j < arr.size(); j++) {
                JSONObject tempData = (JSONObject) arr.get(j);
                System.out.println("ID : " + tempData.get("ID"));
                System.out.println("Nama : " + tempData.get("Nama"));
                System.out.println("Tunjangan Pulsa : " + tempData.get("Tunjangan Pulsa"));
                System.out.println("Gaji Pokok : " + tempData.get("Gaji Pokok"));
                System.out.println("Absensi Hari : " + tempData.get("Absensi Hari"));
                if (file.equals("staff.txt")){
                    System.out.println("Tunjangan Makan : " + tempData.get("Tunjangan Makan"));
                    JSONArray email = (JSONArray) tempData.get("Email");
                    System.out.print("Email : ");
                    for (int k = 0; k < email.size(); k++) {
                        System.out.print(email.get(k) + ", ");
                    }
                    System.out.print("\n\n");
                }else{
                    System.out.println("Tunjangan Transport : " + tempData.get("Tunjangan Transport"));
                    System.out.println("Tunjangan Entertaint : " + tempData.get("Tunjangan Entertaint"));
                    JSONArray email = (JSONArray) tempData.get("No Telepon");
                    System.out.print("No Telepon : ");
                    for (int k = 0; k < email.size(); k++) {
                        System.out.print(email.get(k) + ", ");
                    }
                    System.out.print("\n\n");
                }
//                System.out.println(tempData);
            }
//            System.out.println(arr);

        }catch (Exception e){
            System.out.println(e);
        }
    }
//    public static void TambahAbsensi(){
//        String menu = "0";
//        int id = 0;
//        while(!menu.equals("3")){
//            try {
//                CLS();
//                System.out.println("Tambah Absensi");
//                System.out.println("=====================");
//                System.out.println("");
//                System.out.println("1. Absensi Manager");
//                System.out.println("2. Absensi Staff");
//                System.out.println("3. << Back\n");
//                System.out.print("Input Pilihan : ");
//                menu = br.readLine();
//                switch (Integer.parseInt(menu)) {
//                    case 1:
//                        System.out.print("Input ID Karyawan : ");
//                        id = Integer.parseInt(br.readLine());
//                        Managers.get(id).AbsensiMethod();
//                        break;
//                    case 2:
//                        System.out.print("Input ID Karyawan : ");
//                        id = Integer.parseInt(br.readLine());
//                        Staffs.get(id).AbsensiMethod();
//                        break;
//                    default:
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//    }
//    public static void HitungTunjangan(){
//        String menu = "0";
//        int id = 0;
//        while(!menu.equals("3")){
//            try {
//                CLS();
//                System.out.println("Hitung Tunjangan");
//                System.out.println("=====================");
//                System.out.println("");
//                System.out.println("1. Tunjangan Manager");
//                System.out.println("2. Tunjangan Staff");
//                System.out.println("3. << Back\n");
//                System.out.print("Input Pilihan : ");
//                menu = br.readLine();
//                switch (Integer.parseInt(menu)) {
//                    case 1:
//                        String submenu = "0";
//                        while (!submenu.equals("3")) {
//                            CLS();
//                            System.out.println("Tunjangan Manager");
//                            System.out.println("=====================");
//                            System.out.println("");
//                            System.out.println("1. Tunjangan Transport");
//                            System.out.println("2. Tunjangan Entertaint");
//                            System.out.println("3. << Back\n");
//                            System.out.print("Input Pilihan : ");
//                            submenu = br.readLine();
//                            switch (Integer.parseInt(submenu)) {
//                                case 1:
//                                    System.out.print("Input ID : ");
//                                    id = Integer.parseInt(br.readLine());
//                                    Managers.get(id).HitungTunjanganTransport();
//                                    break;
//                                case 2:
//                                    System.out.print("Input ID : ");
//                                    id = Integer.parseInt(br.readLine());
//                                    System.out.print("Input Jumlah Entertaint : ");
//                                    int jumlah = Integer.parseInt(br.readLine());
//                                    Managers.get(id).HitungTunjanganEntertaint(jumlah);
//                                    break;
//                                default:
//                                    break;
//                            }
//                        }
//                        break;
//                    case 2:
//                        System.out.print("Input ID Karyawan : ");
//                        id = Integer.parseInt(br.readLine());
//                        Staffs.get(id).HitungTunjanganMakan();
//                        break;
//                    default:
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//    }
//    static void HitungTotalGaji(){
//        String menu = "0";
//        while(!menu.equals("3")){
//            try {
//                CLS();
//                System.out.println("Hitung Total Gaji");
//                System.out.println("=====================");
//                System.out.println("");
//                System.out.println("1. Total Gaji Manager");
//                System.out.println("2. Total Gaji Staff");
//                System.out.println("3. << Back\n");
//                System.out.print("Input Pilihan : ");
//                menu = br.readLine();
//                switch (Integer.parseInt(menu)) {
//                    case 1:
//                        Set set = Managers.entrySet();
//                        Iterator itr = set.iterator();
//                        iTotalGajiManager = 0;
//                        while(itr.hasNext()){
//                            Map.Entry entry = (Map.Entry)itr.next();
//                            Manager data = (Manager)entry.getValue();
//                            // System.out.println("Absen : " + data.getAbsen() + " Gapok : " + data.getGapok() + " Tunj Pulsa : " + data.getTunjPulsa() + " Tunj Transport : " + data.getTunjTransport() + " Tunj Entertaint : " + data.getTunjEntertaint());
//                            int total = data.getGapok() + data.getTunjPulsa() + data.getTunjTransport() + data.getTunjEntertaint();
//                            iTotalGajiManager = iTotalGajiManager + total;
//                        }
//                        System.out.print("Berhasil Mendapatkan Total! Tekan Enter! ");
//                        br.readLine();
//                        break;
//                    case 2:
//                        Set sets = Staffs.entrySet();
//                        Iterator itrs = sets.iterator();
//                        iTotalGajiManager = 0;
//                        while(itrs.hasNext()){
//                            Map.Entry entry = (Map.Entry)itrs.next();
//                            Staff data = (Staff)entry.getValue();
//                            // System.out.println("Absen : " + data.getAbsen() + " Gapok : " + data.getGapok() + " Tunj Pulsa : " + data.getTunjPulsa() + " Tunj Pulsa : " + data.getTunjPulsa());
//                            int total = data.getGapok() + data.getTunjPulsa() + data.getTunjMakan();
//                            iTotalGajiStaff = iTotalGajiStaff + total;
//                        }
//                        System.out.print("Berhasil Mendapatkan Total! Tekan Enter! ");
//                        br.readLine();
//                        break;
//                    default:
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//    }
//    public static void Laporan(){
//        try {
//            CLS();
//            String output = "";
//            String format = "| %-2d | %-11s | %-10s | %-5d   | %-1d    | %s |%n";
//            output += output.format("+----+-------------+------------+---------+------------+%n");
//            output += output.format("| ID |  Nama       | Jabatan    | Absensi | Gaji Total |%n");
//            output += output.format("+----+-------------+------------+---------+------------+%n");
//            Set set = Managers.entrySet();
//            Iterator itr = set.iterator();
//            while(itr.hasNext()){
//                Map.Entry entry = (Map.Entry)itr.next();
//                Manager data = (Manager)entry.getValue();
//                output += output.format(format, data.getId(), data.getNama(), "Manager", data.getAbsen(), (data.getGapok() + data.getTunjPulsa() + data.getTunjTransport() + data.getTunjEntertaint()), "ss");
//            }
//            set = Staffs.entrySet();
//            itr = set.iterator();
//            while(itr.hasNext()){
//                Map.Entry entry = (Map.Entry)itr.next();
//                Staff data = (Staff)entry.getValue();
//                String emails = "";
//                for (String email:data.getEmail()) {
//                    emails += email;
//                }
//                output += output.format(format, data.getId(), data.getNama(), "Staff", data.getAbsen(), (data.getGapok() + data.getTunjPulsa() + data.getTunjPulsa()), emails);
//            }
//            output += output.format("+----+-------------+------------+---------+------------+%n");
//            System.out.println(output);
//            FileWriter fw=new FileWriter(".\\Employee.txt");
//            fw.write(output);
//            fw.close();
//            System.out.print("Tekan Enter!");
//            br.readLine();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

}