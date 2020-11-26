package day10;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Pattern;
import java.sql.*;

public class Consolidated {
    static InputStreamReader r=new InputStreamReader(System.in);
    static BufferedReader br=new BufferedReader(r);
    static Properties prop = new Properties();
    static Connection con;
    static Statement stmt;
    static FTPClient ftpClient = new FTPClient();
    static String data = "";
    public static void main(String[] args) {
        if (Login()){
            Menu();
        }else{
            System.out.println("Gagal Login!");
        }
    }

    public static Boolean Login(){
        try {
            System.out.print("Username : ");
            String username = br.readLine();
            System.out.print("Password : ");
            String password = br.readLine();
            Boolean bUsername = Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", username);
            Boolean bPassword = Pattern.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", password);
            if (bUsername && bPassword){
                Properties prop = new Properties();
                InputStream input = null;
                input = new FileInputStream("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY10\\src\\day10\\config.properties"); // load config.properties
                prop.load(input);
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection(
                        "jdbc:mysql://"+ prop.getProperty("DB_HOST") +":"+ prop.getProperty("DB_PORT") +"/"+ prop.getProperty("DB_NAME") +"?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC",prop.getProperty("DB_USER"),prop.getProperty("DB_PASS"));
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_user WHERE username = '"+username+"' AND password = '"+password+"'");
                if (rs.next()){
                    System.out.println("Found!");
                    return true;
                }else{
                    System.out.println("Username atau Password Salah!");
                    return false;
                }
            }else{
                System.out.println("Format Salah!");
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static void Menu(){
        try {
            int Menu = 0;
            while(Menu!=7){
                // Print Menu
                System.out.println("Menu");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Start Socket Server and Client DB");
                System.out.println("2. Connect to DB and FTP Server");
                System.out.println("3. Get Data from FTP");
                System.out.println("4. Send Data to DB");
                System.out.println("5. Input, Edit dan Delete Data from DB");
                System.out.println("6. Send Report to FTP");
                System.out.println("7. Exit");
                System.out.println("");
                System.out.print("Input Menu : ");
                Menu = Integer.parseInt(br.readLine());
                switch (Menu){
                    case 1:
                        SocketServer server = new SocketServer();
                        server.start();
                        break;
                    case 2:
                        Connect();
                        break;
                    case 3:
                        GetData();
                        break;
                    case 4:
                        SendData();
                        break;
                    case 5:
                        CRUD();
                        break;
                    case 6:
                        SendReport();
                        break;
                    default:
                        break;
                }
            }
            con.close();
            ftpClient.logout();
            ftpClient.disconnect();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void Connect(){
        ConnectDB();
        ConnectFTP();
    }
    public static void ConnectDB(){
        try {
            InputStream input = null;
            input = new FileInputStream("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY10\\src\\day10\\config.properties"); // load config.properties
            prop.load(input);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://"+ prop.getProperty("DB_HOST") +":"+ prop.getProperty("DB_PORT") +"/"+ prop.getProperty("DB_NAME") +"?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC",prop.getProperty("DB_USER"),prop.getProperty("DB_PASS"));
            stmt=con.createStatement();
            System.out.println("Connected to Database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void ConnectFTP(){
        try {
            ftpClient.connect(prop.getProperty("FTP_SERVER"), Integer.parseInt(prop.getProperty("FTP_PORT")));
            ftpClient.login(prop.getProperty("FTP_USER"), prop.getProperty("FTP_PASS"));
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (ftpClient.isConnected()){
                System.out.println("Connected To FTP Server!");
            }else{
                System.out.println("Failed!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void GetData(){
        ShowDirectory("");
        String menu = "";
        try {
            while (!menu.equals("3")){
                System.out.println("Directory Menu : \n");
                System.out.println("1. Show Directory");
                System.out.println("2. Enter Full Directory of File");
                System.out.println("3. << Back");
                System.out.print("Input Pilihan : ");
                menu = br.readLine();
                switch (menu){
                    case "1":
                        System.out.print("Input Full Directory : ");
                        String dir = br.readLine();
                        ShowDirectory(dir);
                        break;
                    case "2":
                        DownloadFile();
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void ShowDirectory(String path){
        try {
            FTPFile[] files = ftpClient.listFiles(path);
            DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (FTPFile file : files) {
                String details = file.getName();
                if (file.isDirectory()) {
                    details = "[" + details + "]";
                }
                details += "\t\t" + file.getSize();
                details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
                System.out.println(details);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void DownloadFile(){
        try {
            System.out.print("Input File Directory : ");
            String file = br.readLine();
            System.out.print("Input File Name : ");
            String filename = br.readLine();
            String remoteFile1 = file;
            File downloadFile1 = new File("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY10\\src\\day10\\"+filename);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();
            if (success) {
                System.out.println("File has been downloaded successfully.");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void SendData(){
        try {
            data = "";
            ReadFile();
            System.out.println(data);
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
    public static void ReadFile(){
        try {
            FileReader fr = new FileReader("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY10\\src\\day10\\mahasiswa_alfi.json");
            int i;
            while ((i=fr.read())!=-1){
                data += (char) i;
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void CRUD(){
        try {
            int Menu = 0;
            while(Menu!=4){
                // Print Menu
                System.out.println("Menu CRUD");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Input Data");
                System.out.println("2. Edit Data");
                System.out.println("3. Delete Data");
                System.out.println("4. Back");
                System.out.println("");
                System.out.print("Input Menu : ");
                Menu = Integer.parseInt(br.readLine());
                switch (Menu){
                    case 1:
                        InputData();
                        break;
                    case 2:
                        EditData();
                        break;
                    case 3:
                        DeleteData();
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void InputData(){
        try {
            System.out.print("Input Data Baru\n\n");
            System.out.print("Fullname : ");
            String Fullname = br.readLine();
            System.out.print("Address : ");
            String Address = br.readLine();
            System.out.print("Status : ");
            String Status = br.readLine();
            System.out.println("Grades...");
            System.out.print("Physics : ");
            String Physics = br.readLine();
            System.out.print("Calculus : ");
            String Calculus = br.readLine();
            System.out.print("Biologi : ");
            String Biologi = br.readLine();
            String query = "INSERT INTO tbl_data(fullname, address, status, physics, calculus, biologi) VALUES('" +
                    Fullname + "','" +
                    Address + "','" +
                    Status + "','" +
                    Physics + "','" +
                    Calculus + "','" +
                    Biologi + "')";
            int res = stmt.executeUpdate(query);
            if(res==1){
                System.out.println("Sukses Input Data!");
            }else{
                System.out.println("Gagal Input Data!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void EditData(){
        ShowAll();// tampilkan data dalam DB
        try {
            System.out.print("Update Data\n\n");
            System.out.print("Input ID : ");
            String id = br.readLine();
            System.out.print("Fullname : ");
            String Fullname = br.readLine();
            System.out.print("Address : ");
            String Address = br.readLine();
            System.out.print("Status : ");
            String Status = br.readLine();
            System.out.println("Grades...");
            System.out.print("Physics : ");
            String Physics = br.readLine();
            System.out.print("Calculus : ");
            String Calculus = br.readLine();
            System.out.print("Biologi : ");
            String Biologi = br.readLine();
            String query = "UPDATE tbl_data SET " +
                    "fullname = '" + Fullname + "'," +
                    "address = '" + Address + "'," +
                    "status = '" + Status + "'," +
                    "physics = '" + Physics + "'," +
                    "calculus = '" + Calculus + "'," +
                    "biologi = '" + Biologi + "' " +
                    "WHERE id = '"+ id +"'";
            int res = stmt.executeUpdate(query);
            if(res==1){
                System.out.println("Sukses Update Data!");
            }else{
                System.out.println("Gagal Update Data!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void DeleteData(){
        ShowAll();// tampilkan data dalam DB
        try {
            System.out.print("Delete Data\n\n");
            System.out.print("Input ID : ");
            String id = br.readLine();
            String query = "DELETE FROM tbl_data WHERE id= '" + id + "'";
            int res = stmt.executeUpdate(query);
            if(res==1){
                System.out.println("Sukses Delete Data!");
            }else{
                System.out.println("Gagal Delete Data!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void ShowAll(){
        try {
            String query = "SELECT * FROM tbl_data";
            ResultSet rs = stmt.executeQuery(query);
            String format = "| %-2d | %-15s | %-10s | %-10s | %-2d      | %-2d       | %-2d      |%n";
            System.out.printf("+----+-----------------+------------+------------+---------+----------+---------+%n");
            System.out.printf("| ID |  Fullname       |   Adress   |   Status   | Physics | Calculus | Biologi |%n");
            System.out.printf("+----+-----------------+------------+------------+---------+----------+---------+%n");
            while (rs.next()){
                System.out.printf(format,
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7));
            }
            System.out.printf("+----+-----------------+------------+------------+---------+----------+---------+%n");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void SendReport(){
        try {
            String csv = "";
            String query = "SELECT * FROM tbl_data";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                csv = csv + rs.getInt(1) + "," +
                        rs.getString(2) + "," +
                        rs.getString(3) + "," +
                        rs.getString(4) + "," +
                        rs.getInt(5) + "," +
                        rs.getInt(6) + "," +
                        rs.getInt(7) + "\n";
            }
            String FileName = "result_alfi.csv";
            FileWriter fw = new FileWriter("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY10\\src\\day10\\" + FileName);
            fw.write(csv);
            fw.flush();
            fw.close();
            File firstLocalFile = new File("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY10\\src\\day10\\" + FileName);
            String firstRemoteFile = FileName;
            InputStream inputStream = new FileInputStream(firstLocalFile);
            System.out.println("Start uploading file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("file is uploaded successfully.");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
