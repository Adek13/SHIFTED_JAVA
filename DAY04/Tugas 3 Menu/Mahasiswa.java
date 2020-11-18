public class Mahasiswa {
    int iId, iNilai;
    String sNama;

    public Mahasiswa(int id, String nama, int nilai){
        this.iId = id;
        this.sNama = nama;
        this.iNilai = nilai;
    }
    public void SetNama (String newValue){
        sNama = newValue;
    }
    public String GetNama (){
        return sNama;
    }
    public void setId (int newValue){
        iId = newValue;
    }
    public int getId (){
        return iId;
    }
    public void setNilai (int newValue){
        iNilai = newValue;
    }
    public int getNilai (){
        return iNilai;
    }
}