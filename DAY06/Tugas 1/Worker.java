class Worker {
    int iId = 0, iAbsen = 0;
    String sNama;
    Worker(){

    }

    Worker(int iId, String sNama){
        this.iId = iId;
        this.sNama = sNama;
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
