package com.day09.Tugas02;

import java.io.*;
import java.net.*;
import java.util.Properties;
import java.io.DataOutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Client {
    //Instance Variable br -> scanner
    static InputStreamReader r=new InputStreamReader(System.in);
    static BufferedReader br=new BufferedReader(r);
    static Socket s; // Tampung socket
    static String data;// Variable untuk menampung json array data file.txt dari server

    //Main Method
    public static void main(String[] args) {
        Menu();
    }

    public static void Menu(){
        try {
            int Menu = 0;
            while(Menu!=99){
                // Print Menu
                System.out.println("Menu");
                System.out.println("=====================");
                System.out.println("");
                System.out.println("1. Connect Socket");
                System.out.println("2. Create FileProses.txt");
                System.out.println("3. Tampil Dilayar, Kirim File JSON via FTP (Multi Threading)");
                System.out.println("4. Close All Connection");
                System.out.println("99. Exit");
                System.out.println("");
                System.out.print("Input Menu : ");
                Menu = Integer.parseInt(br.readLine());
                switch (Menu){
                    case 1:
                        ConnectSocket();
                        break;
                    case 2:
                        CreateFile();
                        break;
                    case 3:
                        MultiThreading();
                        break;
                    case 4:
                        CloseConnection();
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //Method koneksi ke Server
    public static void ConnectSocket(){
        try {
            if(s==null||s.isClosed()){
                Properties prop = new Properties();
                InputStream input = null;
                input = new FileInputStream("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY08\\Tugas03\\src\\com\\tugas03\\config.properties"); // load config.properties
                prop.load(input);
                String ip = prop.getProperty("IP");
                int port = Integer.parseInt(prop.getProperty("PORT"));
                s = new Socket(ip,port); // set koneksi
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());// Untuk mengirim request ke server
                dout.writeUTF("Request Data"); // kirim request ke server dalam format UTF
                dout.flush();
                DataInputStream din = new DataInputStream(s.getInputStream());// untuk menampung respons dari server
                data = din.readUTF(); // baca respons dan convert ke string
                System.out.println(data);
            }else{
                System.out.println("Already Connected");
            }

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    //Method Create FileProses.txt
    public static void CreateFile(){
        try {
            String output = "";
            JSONArray arr = (JSONArray) JSONValue.parse(data);
            for (int i = 0; i < arr.size(); i++) {
                JSONObject objData = (JSONObject) arr.get(i);
                output += "Nama : " + objData.get("nama") + "\n";
                output += "Nilai Fisika : " + objData.get("fisika") + "\n";
                output += "Nilai Kimia : " + objData.get("kimia") + "\n";
                output += "Nilai Biologi : " + objData.get("biologi") + "\n\n";
//                System.out.print(objData);
            }
            FileWriter fr = new FileWriter("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY09\\src\\com\\day09\\Tugas02\\FileProses.txt");
            fr.write(output);
            fr.flush();
            fr.close();
//            System.out.print(arr);
        }catch (Exception e){
            System.out.print(e);
        }
    }

    /*Method multi threading dengan 3 thread
        1. t1 = thread print data ke layar (FileProses.txt);
        2. t2 = thread mencari nilai rata2
        3. t3 = thread Upload FileProses.txt ke FTP Server
     */
    public static void MultiThreading(){
        try {
            String output = "";
            JSONArray arr = (JSONArray) JSONValue.parse(data);
            for (int i = 0; i < arr.size(); i++) {
                JSONObject objData = (JSONObject) arr.get(i);
                output += "Nama : " + objData.get("nama") + "\n";
                output += "Nilai Fisika : " + objData.get("fisika") + "\n";
                output += "Nilai Kimia : " + objData.get("kimia") + "\n";
                output += "Nilai Biologi : " + objData.get("biologi") + "\n\n";
//                System.out.print(objData);
            }

            PrintToScreen t1 = new PrintToScreen(output);
            FTPUpload t3 = new FTPUpload(data);
            t1.start();
            t3.start();
        }catch (Exception e){System.out.println(e);}
    }

    // Method Download file dari FTP Server
    public static void Download(){
        try {
            // Config FTP
            String server = "ftp.myth.co.id";
            int port = 21;
            String user = "ftpuser@myth.co.id";
            String pass = "password";

            FTPClient ftpClient = new FTPClient();
            try {

                ftpClient.connect(server, port);
                ftpClient.login(user, pass);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                // APPROACH #1: using retrieveFile(String, OutputStream)
                String remoteFile1 = "/download/average.txt";
                File downloadFile1 = new File("C:\\Download\\average.txt");
                OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
                boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
                outputStream1.close();

                if (success) {
                    System.out.println("File has been downloaded successfully.");
                }
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void CloseConnection(){
        try {
            if (!s.isClosed()){
                s.close();
                System.out.println("Disconnected from server!");
            }else{
                System.out.println("You Are Not Connected Yet");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
