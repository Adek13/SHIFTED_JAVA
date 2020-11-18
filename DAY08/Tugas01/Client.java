import java.io.*;  
import java.net.*;  
import java.io.InputStream;
import java.util.Properties; 
public class Client {  
    static InputStreamReader r=new InputStreamReader(System.in);    
    static BufferedReader br=new BufferedReader(r);    
    static Properties prop = new Properties();
    static InputStream input = null; 
    static String str, message;
    public static void main(String[] args) {  
        try{
            Socket s=new Socket("localhost",6666);
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            while (s.isConnected()) {
                System.out.print("Tulis Pesan : ");
                str = br.readLine();
                dout.writeUTF(str);  
                dout.flush(); 
                DataInputStream din = new DataInputStream(s.getInputStream());
                String str = (String)din.readUTF();
                System.out.println("Pesan : " + str);
            }           
        }catch(Exception e){System.out.println(e);}  
    }  
}
