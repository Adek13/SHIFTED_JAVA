abstract class Worker {
    int iId = 0, iAbsen = 0;
    String sNama;
    abstract void tambahAbsensi();
    public abstract void SetNama (String newValue);
    public abstract String GetNama ();
    public abstract void setId (int newValue);
    public abstract int getId ();
    public abstract int getAbsensi ();
}
