class Staff extends Worker {
    String sJabatan;
    Staff(int id, String nama, String jabatan){
        super(id, nama);
        this.sJabatan = jabatan;
    }
    Staff(){
        super();
    }
    String getJabatan(){
        return this.sJabatan;
    }
}
