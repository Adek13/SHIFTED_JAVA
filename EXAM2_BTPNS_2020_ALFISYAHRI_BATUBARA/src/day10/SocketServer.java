package day10;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

public class SocketServer extends Thread{
    Properties prop = new Properties();
    Socket s = new Socket();
    Connection con;
    Statement stmt;
    String data;

    public void run(){
        DB_Connection();
        SocketStart();
    }

    public void DB_Connection(){
        try {
            InputStream input = null;
            input = new FileInputStream("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY10\\src\\day10\\config.properties"); // load config.properties
            prop.load(input);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://"+ prop.getProperty("DB_HOST") +":"+ prop.getProperty("DB_PORT") +"/"+ prop.getProperty("DB_NAME") +"?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC",prop.getProperty("DB_USER"),prop.getProperty("DB_PASS"));
            stmt=con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SocketStart(){
        try {
            ServerSocket ss=new ServerSocket(Integer.parseInt(prop.getProperty("PORT"))); // Set Koneksi
            System.out.println("Waiting For Socket....");
            s=ss.accept();//establishes connection
            DataInputStream req = new DataInputStream(s.getInputStream()); // untuk menampung request data dari client
            data = req.readUTF(); // baca data dan convert utf ke string
            InputData();
            s.close();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void InputData(){
        try {
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(data);

            JSONObject jsonObject = (JSONObject) obj;

            for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                JSONObject data = (JSONObject) jsonObject.get(key);
                JSONObject grades = (JSONObject) data.get("grades");
                String sql = "INSERT INTO tbl_data (fullname,address,status,physics,calculus,biologi) VALUES(" +
                        "'" + data.get("fullname") + "'," +
                        "'" + data.get("address") + "'," +
                        "'" + data.get("status") + "'," +
                        "'" + grades.get("physics") + "'," +
                        "'" + grades.get("calculus") + "'," +
                        "'" + grades.get("biologi") + "'" +
                        ")";
                int insert = stmt.executeUpdate(sql);
                if(insert<1){
                    System.out.println("Gagal!");
                }
            }
            System.out.println("Data Added Succesfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
