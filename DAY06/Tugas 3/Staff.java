class Staff extends Worker {
    int iTunjMakan = 0, iGapok = 5000000;
    Staff(int id, String nama){
        this.iIdKaryawan = id;
        this.sNama = nama;
        this.HitungTunjanganMakan();
    }
    Staff(){
        super();
    }
    int getTunjMakan(){
        return this.iTunjMakan;
    }
    void HitungTunjanganMakan(){
        this.iTunjMakan = this.iAbsen * 20000;
    }
    void AbsensiMethod(){
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
        return this.iTunjMakan;
    }
}
