package day10;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.util.Properties;

public class SocketClient {
    static String data = "";
    public static void main(String[] args) {
        ReadFile();
        Connect();
    }

    public static void Connect(){
        try {
            Properties prop = new Properties();
            InputStream input = null;
            input = new FileInputStream("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY10\\src\\day10\\config.properties"); // load config.properties
            prop.load(input);
            String ip = prop.getProperty("SERVER");
            int port = Integer.parseInt(prop.getProperty("PORT"));
            Socket s = new Socket(ip,port); // set koneksi
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());// Untuk mengirim request ke server
            dout.writeUTF(data); // kirim request ke server dalam format UTF
            dout.flush();
            dout.close();
            s.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public static void ReadFile(){
        try {
            FileReader fr = new FileReader("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY10\\src\\day10\\mahasiswa2_alfi.json");
            int i;
            while ((i=fr.read())!=-1){
                data += (char) i;
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
