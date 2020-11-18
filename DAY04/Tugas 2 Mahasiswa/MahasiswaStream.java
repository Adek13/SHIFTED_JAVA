import java.io.*;
public class MahasiswaStream {
    public static void main(String[] args){
        MahasiswaArray mhs1 = new MahasiswaArray(1, "Breach", "Laki-laki");
        MahasiswaArray mhs2 = new MahasiswaArray(2, "Viper", "Perempuan");
        MahasiswaArray mhs3 = new MahasiswaArray(3, "Phoenix", "Laki-laki");

        String[] mahasiswa = {
            "ID : " + mhs1.getID() + " Nama : " + mhs1.getNama() + " Gender : " + mhs1.getGender(), 
            "ID : " + mhs2.getID() + " Nama : " + mhs2.getNama() + " Gender : " + mhs2.getGender(), 
            "ID : " + mhs3.getID() + " Nama : " + mhs3.getNama() + " Gender : " + mhs3.getGender()
        };
        String output = mahasiswa[0] + ", " + mahasiswa[1] + ", " + mahasiswa[2];

        FileOutputStream fout;
        FileInputStream fin;
        BufferedOutputStream bout;
        try {
            String path = ".\\output\\mahasiswa.txt";
            fout=new FileOutputStream(path); 
            fin=new FileInputStream(path);
            bout = new BufferedOutputStream(fout);
            byte b[] = output.getBytes();
            bout.write(b);    
            bout.flush();    
            bout.close();  
            System.out.println("Sukses!");
            int i=0;    
            while((i=fin.read())!=-1){    
             System.out.print((char)i);    
            }  
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
