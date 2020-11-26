import java.io.*;
import java.util.*;
public class MainMahasiswa {
    static InputStreamReader r=new InputStreamReader(System.in);    
    static BufferedReader br=new BufferedReader(r);    
    static Map<Integer,Mahasiswa> mMahasiswa=new HashMap<Integer,Mahasiswa>(); //declare map mMahasiswa
    //constructor(print menu)
    public static void main(String args[])throws Exception{
        String menu = "0";       
        while (Integer.parseInt(menu) != 6) {
            System.out.println("Menu");
            System.out.println("=====================");
            System.out.println("");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Edit Mahasiswa");
            System.out.println("3. Delete Mahasiswa");
            System.out.println("4. Laporan Data Mahasiswa");
            System.out.println("5. Tulis Laporan ke File");
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
            System.out.print("Nilai UTS   : ");
            int uts = Integer.parseInt(br.readLine());
            System.out.print("Nilai UAS   : ");
            int uas = Integer.parseInt(br.readLine());
            System.out.print("Nilai Tugas : ");
            int tugas = Integer.parseInt(br.readLine());
            Mahasiswa mahasiswa = new Mahasiswa(id, nama, uts, uas, tugas); //deklarasi object mahasiswa sementara untuk menampun data
            mMahasiswa.put(id, mahasiswa); //method put map/tambah data ke map dengan format (int key/id, Mahasiswa/object mahasiswa)
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //method edit data mahasiswa berdasarkan id
    public static void EditMahasiswa(){
        try {
            System.out.print("Masukkan ID Mahasiswa : ");
            int sIdEdit = Integer.parseInt(br.readLine());  
            Mahasiswa dataEdit = mMahasiswa.get(sIdEdit);// method get map (ambil data di map berdasarkan id/key)      
            System.out.println("Data Sebelumnya : ");
            System.out.println("=========================");
            System.out.println("");
            System.out.println("ID          : " + sIdEdit);
            System.out.println("Nama        : " + dataEdit.GetNama());
            System.out.println("Nilai UTS   : " + dataEdit.getUts());
            System.out.println("Nilai UAS   : " + dataEdit.getUas());
            System.out.println("Nilai Tugas : " + dataEdit.getTugas());
            System.out.println("Nilai Akhir : " + dataEdit.getNilaiAkhir());
            System.out.println("");
            System.out.print("Nama : ");
            String editNama = br.readLine();
            System.out.print("Nilai UTS : ");
            int editUts = Integer.parseInt(br.readLine());
            System.out.print("Nilai UAs : ");
            int editUas = Integer.parseInt(br.readLine());
            System.out.print("Nilai Tugas : ");
            int editTugas = Integer.parseInt(br.readLine());
            // akses method set di object mahasiswa
            dataEdit.SetNama(editNama);
            dataEdit.setUts(editUts);
            dataEdit.setUas(editUas);
            dataEdit.setTugas(editTugas);
            //
            System.out.print("Tekan Enter!");
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //method untuk print laporan ke layar
    public static void Laporan(){
        try {
            Map<Integer, Mahasiswa> map = new TreeMap<>(mMahasiswa);
            Set set = map.entrySet();
            Iterator itr=set.iterator();
            System.out.println("Laporan Data Mahasiswa");
            String formatTable = "| %-10s | %-4d | %-4d | %-5d | %.1f        |%n";
            System.out.format("+------------+------+------+-------+-------------+%n");
            System.out.format("| NAMA       | UTS  | UAS  | TUGAS | NILAI AKHIR |%n");
            System.out.format("+------------+------+------+-------+-------------+%n");
            while(itr.hasNext()){  
                Map.Entry entry = (Map.Entry)itr.next();  
                Mahasiswa data = (Mahasiswa)entry.getValue();
                System.out.format(formatTable, data.GetNama(), data.getUts(), data.getUas(), data.getTugas(), Math.round( data.getNilaiAkhir() * 100.0) / 100.0);
            }
            System.out.format("+------------+------+------+-------+-------------+%n");
            System.out.print("Tekan Enter!");
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // method hapus data di map berdasarkan id/key
    public static void Hapus(){
        try {
            System.out.print("Masukkan ID Mahasiswa : ");
            String sIdHapus = br.readLine();
            mMahasiswa.remove(Integer.parseInt(sIdHapus));// method remove map(key/id)
            System.out.print("Berhasil Hapus!, Tekan Enter!");
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //method untuk menulis/membuat file
    public static void TulisLaporan(){
        try {
            System.out.print("Input Nama File : ");
            String filename = br.readLine(); 
            FileWriter fw=new FileWriter(".\\" + filename);
            Set set = mMahasiswa.entrySet();
            Iterator itr=set.iterator();
            String output = "";
            String formatTable = "| %-10s | %-4d | %-4d | %-5d | %.1f        |%n";
            output += output.format("+------------+------+------+-------+-------------+%n");
            output += output.format("| NAMA       | UTS  | UAS  | TUGAS | NILAI AKHIR |%n");
            output += output.format("+------------+------+------+-------+-------------+%n");
            while(itr.hasNext()){  
                Map.Entry entry = (Map.Entry)itr.next();  
                Mahasiswa data = (Mahasiswa)entry.getValue();
                output += output.format(formatTable, data.GetNama(), data.getUts(), data.getUas(), data.getTugas(), data.getNilaiAkhir());
            }
            output += output.format("+------------+------+------+-------+-------------+%n");
            fw.write(output);    
            fw.close();  
            System.out.print("Berhasil Tulis Laporan Ke File!, Tekan Enter!");
            br.readLine();  
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
