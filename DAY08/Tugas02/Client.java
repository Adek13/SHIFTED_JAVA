import java.io.*;  
import java.net.*;  
import java.util.Properties;
import java.util.regex.*; 
public class Client {
    static InputStreamReader r=new InputStreamReader(System.in);    
    static BufferedReader br=new BufferedReader(r); 
    static Properties prop = new Properties();
    static InputStream input = null;
    static Socket s;
    public static void main(String[] args) {
        try {
            System.out.print("Username : ");
            String username = br.readLine();
            System.out.print("Password : ");
            String password = br.readLine();
            Boolean bUser = Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", username);
            Boolean bPass = Pattern.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", password);
            if(bUser && bPass){
                MethodMenu();                
            }         
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public static void MethodMenu(){
        String menu = "0";
        try {
            while (Integer.parseInt(menu) != 4) {
                System.out.println("Menu");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Connect Socket");
                System.out.println("2. Send Data to Server");
                System.out.println("3. Close Socket");
                System.out.println("4. Exit");
                System.out.println("");
                System.out.print("Input Menu : ");
                menu = br.readLine();
                switch (Integer.parseInt(menu)) {
                    case 1:
                        ConnectToSocket();
                        break;
                    case 2:
                        SendData();
                        break;
                    case 3:
                        CloseSocket();
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void ConnectToSocket(){
        try {
            Properties prop = new Properties();
            input = new FileInputStream("config.properties");
            prop.load(input);
            String ip = prop.getProperty("IP");
            int port = Integer.parseInt(prop.getProperty("PORT"));
            s=new Socket(ip,port); 
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public static void SendData(){
        try {
            FileReader fr=new FileReader(".\\data.txt");    
            int i;    
            String data = "";
            while((i=fr.read())!=-1){
                data += (char)i;    
            }  
            fr.close(); 
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(data);
            dout.flush();
            DataInputStream res = new DataInputStream(s.getInputStream());
            String resp = res.readUTF();
            System.out.println(resp);  
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public static void CloseSocket(){
        try {  
            s.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
