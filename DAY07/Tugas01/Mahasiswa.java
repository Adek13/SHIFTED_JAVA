import java.util.*;
public class Mahasiswa {
    // deklarasi dan inisialisasi instance variabel
    int iId = 0;
    String sNama;
    ArrayList<Double> aNilai = new ArrayList<>();
    //constructor
    public Mahasiswa(int id, String nama, Double bhs, Double fisika, Double algoritma){
        this.iId = id;
        this.sNama = nama;
        TambahNilai(bhs, fisika, algoritma);
    }
    public void setId (int newValue){
        iId = newValue;
    }
    public int getId (){
        return iId;
    }
    public void SetNama (String newValue){
        sNama = newValue;
    }
    public String GetNama (){
        return sNama;
    }
    public void TambahNilai (Double BhsInggris, Double Fisika, Double Algoritma){
        this.aNilai.add(BhsInggris);
        this.aNilai.add(Fisika);
        this.aNilai.add(Algoritma);
    }
    public Double getNilai(int index){
        return this.aNilai.get(index);
    }

}