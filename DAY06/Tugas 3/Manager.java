public class Manager extends Worker {
    int iTunjTransport = 0, iTunjEntertaint = 0, iGapok = 8000000;
    Manager(int id, String nama){
        this.iIdKaryawan    = id;
        this.sNama          = nama;
        HitungTunjanganTransport();
        HitungTunjanganEntertaint(0);
    }
    Manager(){
        super();
    }
    int getTunjTransport(){
        return this.iTunjTransport;
    }
    void HitungTunjanganTransport(){
        this.iTunjTransport = this.iAbsen * 50000;
    }
    int getTunjEntertaint(){
        return this.iTunjEntertaint;
    }
    void HitungTunjanganEntertaint(int iJumlah){
        this.iTunjEntertaint = iJumlah * 50000;
    }
    public void AbsensiMethod(){
        this.iAbsen += 1;
    }
    public void setNama (String newValue){
        this.sNama = newValue;
    }
    public void setId(int newValue){
        this.iIdKaryawan = newValue;
    }
    public int getId (){
        return this.iIdKaryawan;
    }
    public String getNama (){
        return this.sNama;
    }
    public int getAbsen(){
        return this.iAbsen;
    }
    public int getTunjPulsa (){
        return this.iTunjPulsa;
    }
    public int getGapok (){
        return this.iGapok;
    }
}
