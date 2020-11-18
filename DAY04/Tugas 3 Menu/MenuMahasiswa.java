import java.io.*;
import java.util.*;
public class MenuMahasiswa {
    static InputStreamReader r=new InputStreamReader(System.in);    
    static BufferedReader br=new BufferedReader(r);    
    static Map<Integer,Mahasiswa> mMahasiswa=new HashMap<Integer,Mahasiswa>(); 

    public static void main(String args[])throws Exception{
        String menu = "0";       
        while (Integer.parseInt(menu) != 6) {
            System.out.println("Menu");
            System.out.println("=====================");
            System.out.println("");
            System.out.println("1. Buat Object Mahasiswa");
            System.out.println("2. Edit Data Mahasiswa");
            System.out.println("3. Remove Object Mahasiswa");
            System.out.println("4. Laporan Data Mahasiswa");
            System.out.println("5. Tulis Laporan ke File TXT");
            System.out.println("6. Exit");
            System.out.println("");
            System.out.print("Input Menu : ");
            menu = br.readLine();
            switch (Integer.parseInt(menu)) {
                case 1:
                    BuatObject();
                    break;
                case 2:
                    EditMahasiswa();
                    break;
                case 3:
                    Hapus();
                    break;
                case 4:
                    Laporan();
                    break;
                case 5:
                    TulisLaporan();
                    break;
                default:
                    break;
            }
        }
    }

    public static void BuatObject(){
        try {
            System.out.println("Buat Object Mahasiswa");
            System.out.println("=====================");
            System.out.println("");
            System.out.print("ID        : ");
            String id = br.readLine();
            System.out.print("Nama      : ");
            String nama = br.readLine();
            System.out.print("Nilai     : ");
            String nilai = br.readLine();
            Mahasiswa mahasiswa = new Mahasiswa(Integer.parseInt(id), nama, Integer.parseInt(nilai));
            mMahasiswa.put(Integer.parseInt(id), mahasiswa);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void EditMahasiswa(){
        try {
            System.out.print("Masukkan ID Mahasiswa : ");
            String sIdEdit = br.readLine();  
            Mahasiswa dataEdit = mMahasiswa.get(Integer.parseInt(sIdEdit));          
            System.out.println("Data Sebelumnya : ");
            System.out.println("=====================");
            System.out.println("");
            System.out.println("ID        : " + sIdEdit);
            System.out.println("Nama      : " + dataEdit.GetNama());
            System.out.println("Nilai     : " + dataEdit.getNilai());
            System.out.println("");
            System.out.print("Nama : ");
            String editNama = br.readLine();
            System.out.print("Nilai : ");
            String editNilai = br.readLine();
            dataEdit.setId(Integer.parseInt(sIdEdit));
            dataEdit.SetNama(editNama);
            dataEdit.setNilai(Integer.parseInt(editNilai));
            System.out.print("Tekan Enter!");
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Laporan(){
        try {
            Map<Integer, Mahasiswa> map = new TreeMap<>(mMahasiswa);
            Set set = map.entrySet();//Converting to Set so that we can traverse  
            Iterator itr=set.iterator();
            System.out.println("Laporan Data Mahasiswa");
            System.out.println("=====================");
            System.out.println("");  
            while(itr.hasNext()){  
                //Converting to Map.Entry so that we can get key and value separately  
                Map.Entry entry = (Map.Entry)itr.next();  
                Mahasiswa data = (Mahasiswa)entry.getValue();
                System.out.println(data.getId() + "   " + data.GetNama() + "   " + data.getNilai());
            }
            System.out.print("Tekan Enter!");
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Hapus(){
        try {
            System.out.print("Masukkan ID Mahasiswa : ");
            String sIdHapus = br.readLine();
            mMahasiswa.remove(Integer.parseInt(sIdHapus));
            System.out.print("Berhasil Hapus!, Tekan Enter!");
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void TulisLaporan(){
        try {
            System.out.print("Input Directory : ");
            String directory = br.readLine(); 
            System.out.print("Input Nama File : ");
            String filename = br.readLine(); 
            FileWriter fw=new FileWriter(directory + filename);
            Set set = mMahasiswa.entrySet();
            Iterator itr=set.iterator();
            String output = "";
            while(itr.hasNext()){  
                Map.Entry entry = (Map.Entry)itr.next();  
                Mahasiswa data = (Mahasiswa)entry.getValue();
                output = output + "ID : " + data.getId() + "\n" + "Nama : " + data.GetNama() + "\n"+ "Nilai : " + data.getNilai() + "\n\n";
            }
            fw.write(output);    
            fw.close();  
            System.out.print("Berhasil Tulis Laporan Ke File!, Tekan Enter!");
            br.readLine();  
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
