class Staff extends Worker {
    String sJabatan;
    Staff(int id, String nama, String jabatan){
        this.iId = id;
        this.sNama = nama;
        this.sJabatan = jabatan;
    }
    Staff(){
        super();
    }
    String getJabatan(){
        return this.sJabatan;
    }
    void tambahAbsensi(){
        this.iAbsen += 1;
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
    public int getAbsensi (){
        return iAbsen;
    }
}
