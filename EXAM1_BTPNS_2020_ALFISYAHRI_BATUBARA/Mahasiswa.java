public class Mahasiswa {
    // deklarasi dan inisialisasi instance variabel
    int iId = 0, iUts = 0, iUas = 0, iTugas = 0;
    String sNama;
    double dNilaiAkhir;

    //constructor
    public Mahasiswa(int id, String nama, int uts, int uas, int tugas){
        this.iId = id;
        this.sNama = nama;
        this.iUts = uts;
        this.iUas = uas;
        this.iTugas = tugas;
        this.dNilaiAkhir = (0.35 * uts) + (0.45 * uas) + (0.2 * tugas);
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
    //method untuk Update Nilai
    public void setUts (int newValue){
        iUts = newValue;
        NilaiAkhir(this.iUts, this.iUas, this.iTugas);
    }
    public int getUts (){
        return iUts;
    }
    //method untuk Update Nilai
    public void setUas (int newValue){
        iUas = newValue;
        NilaiAkhir(this.iUts, this.iUas, this.iTugas);
    }
    public int getUas (){
        return iUas;
    }
    //method untuk Update Nilai
    public void setTugas (int newValue){
        iTugas = newValue;
        NilaiAkhir(this.iUts, this.iUas, this.iTugas);
    }
    public int getTugas (){
        return iTugas;
    }
    public double getNilaiAkhir (){
        return dNilaiAkhir;
    }
    //method untuk perhitungan nilai akhir
    public void NilaiAkhir(int uts, int uas, int tugas){
        double NilaiAkhir = (0.35 * uts) + (0.45 * uas) + (0.2 * tugas);
        this.dNilaiAkhir = Math.round((NilaiAkhir*100)/100);
    }
}