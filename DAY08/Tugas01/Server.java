import java.io.*;  
import java.net.*;  
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class Server {
    static InputStreamReader r=new InputStreamReader(System.in);    
    static BufferedReader br=new BufferedReader(r); 
    static Properties prop = new Properties();
    static InputStream input = null; 
    static String str = "";
    public static void main(String[] args){  
        try{
            ServerSocket ss=new ServerSocket(6666);  
            Socket s=ss.accept();//establishes connection
            while (!str.equals("EXIT")) {
                DataInputStream din = new DataInputStream(s.getInputStream());
                String str = (String)din.readUTF();
                System.out.println("Pesan : " + str);
                System.out.print("Kirim Pesan : ");
                String pesan = br.readLine();
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                dout.writeUTF(pesan);
                dout.flush();
            }
            try {
                s.close();   
            } catch (Exception e) {
                System.out.println(e);
            }
        }catch(Exception e){System.out.println(e);}
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } 
        }
    }
}  