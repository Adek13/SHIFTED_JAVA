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
    static String data = "";// Variable untuk menampung data string dari file.txt
    static Socket s;
    public static void main(String[] args)
    {
        Boolean active = true;//Untuk set status server, jika socket di close atau ada error akan menjadi false dan server berhenti
        try {
            ReadFile(args[1]);
            connect(args[0], 6000);//method set koneksi dan port
            String reqs = "";
            while (active){
                DataInputStream req = new DataInputStream(s.getInputStream()); // untuk menampung request data dari client
                reqs = req.readUTF(); // baca data dan convert utf ke string
                System.out.println(reqs); // print request untuk memastikan bahwa client melakukan request
                DataOutputStream res = new DataOutputStream(s.getOutputStream()); // untuk menampung respon yang akan dikirim ke client
                res.writeUTF(data); // tulis data dan convert string ke utf
                res.flush();
                // res.close();
            }
        }catch (Exception e){
            active = false;
        }
    }

    //method untuk set koneksi dan port
    public static void connect(String config, int port)
    {
        try {
            Properties prop = new Properties();
            OutputStream output = null;
            output = new FileOutputStream(config);
            prop.setProperty("IP", "localhost"); // set IP untuk file config.properties
            prop.setProperty("PORT", String.valueOf(port)); // set PORT untuk file config.properties
            prop.store(output, null); // do Store
            ServerSocket ss=new ServerSocket(port); // Set Koneksi
            System.out.println("Waiting For Socket....");
            s=ss.accept();//establishes connection
        }catch (Exception e){
            System.out.print(e);
        }
    }

    //Method untuk membaca file.txt yang selanjutnya di masukkan ke instance variable 'data' berupa string
    public static void ReadFile(String path)
    {
        try {
            FileReader fin = new FileReader(path); //File Reader
            int i;
            while((i=fin.read())!=-1){ // Perulangan untuk menyimpan karakter ke variabel data
                data += (char)i;
            }
        }catch (Exception e){System.out.println(e);}

    }
}
