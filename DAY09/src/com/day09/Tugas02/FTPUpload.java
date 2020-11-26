package com.day09.Tugas02;

import java.io.*;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FTPUpload extends Thread{
    static String data;
    public void run(){
        String server = "ftp.myth.co.id";
        int port = 21;
        String user = "ftpuser@myth.co.id";
        String pass = "password";

        FTPClient ftpClient = new FTPClient();
        try {
            FileWriter fr = new FileWriter("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY09\\src\\com\\day09\\Tugas02\\filetoupload.txt");
            fr.write(data);
            fr.flush();
            fr.close();
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY09\\src\\com\\day09\\Tugas02\\filetoupload.txt");

            String firstRemoteFile = "FileProses_Alfi.txt";
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Start uploading file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("file is uploaded successfully.");
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
    }
    public FTPUpload(String data){
        this.data = data;
    }
}
