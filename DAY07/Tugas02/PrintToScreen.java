import java.util.ArrayList;
import java.util.Iterator;
public class PrintToScreen extends Thread{
    ArrayList<Mahasiswa> mMahasiswa = new ArrayList<>();
    public void run(){
        try {
            Iterator itr=mMahasiswa.iterator();
            System.out.println("Laporan Data Mahasiswa");
            String formatTable = "| %-10s | %.1f        | %.1f     | %.1f       |%n";
            System.out.format("+------------+------------+---------+-----------+%n");
            System.out.format("| NAMA       | B.Inggris  | Fisika  | Algoritma |%n");
            System.out.format("+------------+------------+---------+-----------+%n");
            while(itr.hasNext()){  
                Mahasiswa data = (Mahasiswa)itr.next();
                System.out.format(formatTable, data.GetNama(), data.getNilai(0), data.getNilai(1), data.getNilai(2));
            }
            System.out.format("+------------+------------+---------+-----------+%n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    PrintToScreen(ArrayList<Mahasiswa> mahasiswa){
        this.mMahasiswa = mahasiswa;
    }
}
