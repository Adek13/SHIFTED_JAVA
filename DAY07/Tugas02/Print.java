import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
public class Print extends Thread{
    ArrayList<Mahasiswa> mMahasiswa = new ArrayList<>();
    String output = "";
    public void run(){
        try{
            FileWriter fw=new FileWriter(".\\mahasiswa.txt");
            Iterator itr=mMahasiswa.iterator();
            String formatTable = "| %-10s | %.1f        | %.1f     | %.1f       |%n";
            output += output.format("+------------+------------+---------+-----------+%n");
            output += output.format("| NAMA       | B.Inggris  | Fisika  | Algoritma |%n");
            output += output.format("+------------+------------+---------+-----------+%n");
            while(itr.hasNext()){   
                Mahasiswa data = (Mahasiswa)itr.next();
                output += output.format(formatTable, data.GetNama(), data.getNilai(0), data.getNilai(1), data.getNilai(2));
            }
            output += output.format("+------------+------------+---------+-----------+%n");
            fw.write(output);    
            fw.close();  
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    Print(ArrayList<Mahasiswa> mahasiswa){
        this.mMahasiswa = mahasiswa;
    }
}
