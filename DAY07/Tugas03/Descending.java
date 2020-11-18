import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
public class Descending extends Thread{
    ArrayList<Integer> Descending = new ArrayList<>();
    String output = "";
    public void run(){
        try {
            Sort();
            FileWriter fw=new FileWriter(".\\fileDataDesc.txt");
            Iterator itr = Descending.iterator();
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
    Descending(ArrayList<Integer> List){
        this.Descending = List;
    }
    void Sort(){
        for (int i = 0; i < Descending.size() - 1; i++) {
            for (int j = 0; j < Descending.size() - 1 - i; j++) {
                if (Descending.get(j) < Descending.get(j + 1)) {
                    Integer temp = Descending.get(j);
                    Descending.set(j, Descending.get(j+1));
                    Descending.set(j+1, temp);
                }
            }
        }
    }
}
