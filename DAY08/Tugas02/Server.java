import java.io.*;  
import java.net.*;  
import java.util.Properties;
import java.util.regex.*;  
public class Server {
    static InputStreamReader r = new InputStreamReader(System.in);    
    static BufferedReader br = new BufferedReader(r); 
    static Properties prop = new Properties();
    static InputStream input = null;
    public static void main(String[] args) {
        try {
            // Print Login username dan password
            System.out.print("Username : ");
            String username = br.readLine();
            System.out.print("Password : ");
            String password = br.readLine();

            // cek Regex username dan password
            Boolean bUser = Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", username);
            Boolean bPass = Pattern.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", password);
            if(bUser && bPass){
                Properties prop = new Properties();
                OutputStream output = null;
                int socket = 6000;
                output = new FileOutputStream("config.properties");
                prop.setProperty("IP", "localhost");
                prop.setProperty("PORT", String.valueOf(socket));
                prop.store(output, null);
                ServerSocket ss=new ServerSocket(socket);  
                System.out.println("Waiting For Socket....");
                Socket s=ss.accept();//establishes connection
                System.out.println("Waiting For Data....");
                String data = "";
                while (!data.equals("EXIT")) {
                    DataInputStream din = new DataInputStream(s.getInputStream());
                    data = (String)din.readUTF();
                    String[] data1 = data.split("\\n");
                    for (String string : data1) {
                        String[] data2 = string.split("\\,");
                        System.out.println(String.join("\n", "Nama : " + data2[0], "Nilai Fisika : " + data2[1], "Nilai Biologi : " + data2[2], "Nilai Kimia : " + data2[3]));
                    }
                    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                    dout.writeUTF("Data Done Processing");
                    dout.flush();
                }
            }else{
                System.out.println("Format Username atau Password Salah!");
            }         
        } catch (Exception e) {
            System.out.print(e);
        }

    }
}