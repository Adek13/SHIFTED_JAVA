import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.xml.crypto.dsig.SignedInfo;
public class Menu {
    static InputStreamReader r=new InputStreamReader(System.in);    
    static BufferedReader br=new BufferedReader(r);    
    static ArrayList<Mahasiswa> mMahasiswa = new ArrayList<>();
    //constructor(print menu)
    public static void main(String args[])throws Exception{
        try {
            System.out.print("Username : ");
            String LoginUsername = br.readLine();
            if(Pattern.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", LoginUsername)){
                    System.out.print("Password : ");
                    String LoginPassword = br.readLine();
                if (Pattern.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", LoginPassword)) {
                    FileReader frusername=new FileReader(".\\username.txt");    
                    int i = 0 ; 
                    String username = "";   
                    while((i=frusername.read())!=-1)    
                        username += (char)i;
                    frusername.close();
                    FileReader frpassword=new FileReader(".\\password.txt");    
                    i = 0 ; 
                    String password = "";   
                    while((i=frpassword.read())!=-1)    
                        password += (char)i;
                    frpassword.close();
                    System.out.println(username);
                    System.out.println(password);
                    if(LoginUsername.equals(username) && LoginPassword.equals(password)){
                        MethodMenu();
                    }else{
                        System.out.println("Username atau Password Salah!");
                    }
                }else{
                    System.out.println("Format Password Salah!");
                }
            }else{
                System.out.println("Format Username Salah!");
            } 
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
                System.out.println("2. Edit Or Delete Mahasiswa");
                System.out.println("3. Laporan Data Mahasiswa");
                System.out.println("4. Exit");
                System.out.println("");
                System.out.print("Input Menu : ");
                menu = br.readLine();
                switch (Integer.parseInt(menu)) {
                    case 1:
                        BuatObject();
                        break;
                    case 2:
                        EditMenu();
                        break;
                    case 3:
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
    //method edit data mahasiswa berdasarkan id
    public static void EditMenu(){
        try {
            String menu = "0";
            while (Integer.parseInt(menu) != 3) {
                System.out.println("Menu");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Edit Mahasiswa");
                System.out.println("2. Hapus Mahasiswa");
                System.out.println("3. Exit");
                System.out.println("");
                System.out.print("Input Menu : ");
                menu = br.readLine();
                switch (Integer.parseInt(menu)) {
                    case 1:
                        EditMahasiswa();
                        break;
                    case 2:
                        DeleteMahasiswa();
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void EditMahasiswa(){
        try {
            System.out.print("Masukkan ID Mahasiswa : ");
            int sIdEdit = Integer.parseInt(br.readLine());
            Mahasiswa dataEdit = mMahasiswa.get(BinarySearch(mMahasiswa, sIdEdit));      
            System.out.println("Data Sebelumnya : ");
            System.out.println("=========================");
            System.out.println("");
            System.out.println("ID          : " + sIdEdit);
            System.out.println("Nama        : " + dataEdit.GetNama());
            System.out.println("Nilai UTS   : " + dataEdit.getNilai(0));
            System.out.println("Nilai UAS   : " + dataEdit.getNilai(1));
            System.out.println("Nilai Tugas : " + dataEdit.getNilai(2));
            System.out.println("");
            System.out.print("Nama : ");
            String editNama = br.readLine();
            System.out.print("Nilai Bahasa Inggris : ");
            Double editBhs = Double.parseDouble(br.readLine());
            System.out.print("Nilai Fisika : ");
            Double editFisika = Double.parseDouble(br.readLine());
            System.out.print("Nilai Algoritma : ");
            Double editAlgo = Double.parseDouble(br.readLine());
            // akses method set di object mahasiswa
            dataEdit.SetNama(editNama);
            dataEdit.aNilai.set(0, editBhs);
            dataEdit.aNilai.set(1, editFisika);
            dataEdit.aNilai.set(2, editAlgo);
            //
            System.out.print("Tekan Enter!");
            br.readLine();
            
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public static void DeleteMahasiswa(){
        try {
            System.out.print("Masukkan ID Mahasiswa : ");
            int sIdEdit = Integer.parseInt(br.readLine());  
            mMahasiswa.remove(BinarySearch(mMahasiswa, sIdEdit));
            System.out.print("Tekan Enter!");
            br.readLine();
            
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    //method untuk print laporan ke layar
    public static void Laporan(){
        try {
            BubbleSort(mMahasiswa);
            System.out.println("Laporan Data Mahasiswa");
            String formatTable = "| %-10s | %.1f        | %.1f     | %.1f       |%n";
            System.out.format("+------------+------------+---------+-----------+%n");
            System.out.format("| NAMA       | B.Inggris  | Fisika  | Algoritma |%n");
            System.out.format("+------------+------------+---------+-----------+%n");
            Iterator itr = mMahasiswa.iterator();
            while(itr.hasNext()){  
                Mahasiswa data = (Mahasiswa)itr.next();
                System.out.format(formatTable, data.GetNama(), data.getNilai(0), data.getNilai(1), data.getNilai(2));
            }
            System.out.format("+------------+------------+---------+-----------+%n");
            System.out.print("Tekan Enter!");
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
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
