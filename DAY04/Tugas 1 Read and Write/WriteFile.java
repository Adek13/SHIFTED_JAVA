import java.io.FileOutputStream;  
import java.io.FileInputStream; 
public class WriteFile {
    public static void main(String[] args) {
        try {
            FileOutputStream fout=new FileOutputStream("./output/" + args[0] + ".txt");    
            String string = args[1];    
            byte b[]=string.getBytes();//converting string into byte array    
            fout.write(b);    
            fout.close();    
            System.out.println("Success!"); 
        } catch (Exception e) {
            System.out.println(e);
        }
        try{    
            FileInputStream fin=new FileInputStream("./output/" + args[0] + ".txt");    
            int i=0;    
            while((i=fin.read())!=-1){    
             System.out.print((char)i);    
            }    
            fin.close();   
          }catch(Exception e){System.out.println(e);}
    }
}
