package com.day09.Tugas02;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.*;
import java.io.FileOutputStream;
import java.util.Properties;

/** Server Docs
 * Class Server (Run Melalui Terminal, ex: java Server config.properties file.txt)
 * config.properties dan file.txt wajib ada
 * Alur Program : membaca file.txt dan convert ke string, Server membuat koneksi, Jika terhubung dengan client dan client melakukan request,
 *                server akan merespon string data file.txt
 */
public class Server {
    static String arrData;// Variable untuk menampung data array json
    static Socket s;
    public static void main(String[] args)
    {
        Boolean active = true;//Untuk set status server, jika socket di close atau ada error akan menjadi false dan server berhenti
        try {
            ReadFile();
            Properties prop = new Properties();
            InputStream input = null;
            input = new FileInputStream("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY09\\src\\com\\day09\\Tugas02\\config.properties");
            prop.load(input);
            ServerSocket ss=new ServerSocket(Integer.parseInt(prop.getProperty("PORT"))); // Set Koneksi
            System.out.println("Waiting For Socket....");
            String reqs = "";
            while (active){
                s=ss.accept();//establishes connection
                DataInputStream req = new DataInputStream(s.getInputStream()); // untuk menampung request data dari client
                reqs = req.readUTF(); // baca data dan convert utf ke string
                System.out.println(reqs); // print request untuk memastikan bahwa client melakukan request
                DataOutputStream res = new DataOutputStream(s.getOutputStream()); // untuk menampung respon yang akan dikirim ke client
                res.writeUTF(arrData); // tulis data dan convert string ke utf
                res.flush();
                // res.close();
            }
        }catch (Exception e){
            active = false;
        }
    }

    //Method untuk membaca file.txt yang selanjutnya di masukkan ke instance variable 'data' berupa string
    public static void ReadFile()
    {
        try {
            FileReader fin = new FileReader("C:\\Users\\btpnshifted\\btpns_shifted\\JAVA\\DAY09\\src\\com\\day09\\Tugas02\\file.txt"); //File Reader
            String data = "";
            int i;
            while((i=fin.read())!=-1){ // Perulangan untuk menyimpan karakter ke variabel data
                data += (char)i;
            }
            String output = "";
            String[] split1 = data.split("\\n");
            JSONArray arr = new JSONArray();
            for (String string : split1) {
                JSONObject tempObj = new JSONObject();
                String[] data2 = string.split("\\,");
                Integer Fisika = Integer.parseInt(data2[1]);
                Integer Kimia = Integer.parseInt(data2[2]);
                tempObj.put("nama", data2[0]);
                tempObj.put("fisika", Fisika);
                tempObj.put("kimia", Kimia);
                String[] biologi = data2[3].split("\r");
                Integer Biologi = Integer.parseInt(biologi[0]);
                tempObj.put("biologi", Biologi);
                arr.add(tempObj);
            }
            arrData = arr.toJSONString();
        }catch (Exception e){System.out.println(e);}

    }
}
