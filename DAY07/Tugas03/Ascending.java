import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
public class Ascending extends Thread{
    ArrayList<Integer> Ascending = new ArrayList<>();
    String output = "";
    public void run(){
        try {
            Sort();
            FileWriter fw=new FileWriter(".\\fileDataAsc.txt");
            Iterator itr = Ascending.iterator();
            while (itr.hasNext()) {
                Integer Number = (Integer)itr.next();
                output += Number + "\n";
            }  
            fw.write(output);    
            fw.close(); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    Ascending(ArrayList<Integer> List){
        this.Ascending = List;
    }
    void Sort(){
        for (int i = 0; i < Ascending.size() - 1; i++) {
            for (int j = 0; j < Ascending.size() - 1 - i; j++) {
                if (Ascending.get(j + 1) < Ascending.get(j)) {
                    int temp = Ascending.get(j);
                    Ascending.set(j, Ascending.get(j+1));
                    Ascending.set(j+1, temp);
                }
            }
        }
    }
}
