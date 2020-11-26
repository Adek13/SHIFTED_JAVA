package com.staff.model;


import java.util.ArrayList;

/** Model Staff
 * Membuat object staff dengan id auto increment menggunakan AtomicLong
 */
public class Staff extends Worker{

    private int tunjanganMakan;

    private ArrayList<String> email = new ArrayList<>();

    public Staff() {
        this.id = counter.incrementAndGet();
    }

    public Staff(String nama, int tunjanganPulsa, int gajiPokok, int absensi, int tunjanganMakan, ArrayList<String>email) {
        this.id = counter.incrementAndGet();
        this.nama = nama;
        this.tunjanganPulsa = tunjanganPulsa;
        this.gajiPokok = gajiPokok;
        this.absensi = absensi;
        this.tunjanganMakan = tunjanganMakan;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getTunjanganPulsa() {
        return tunjanganPulsa;
    }

    public void setTunjanganPulsa(int tunjanganPulsa) {
        this.tunjanganPulsa = tunjanganPulsa;
    }
    public int getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(int gajiPokok) {
        this.gajiPokok = gajiPokok;
    }
    public int getAbsensi() {
        return absensi;
    }

    public void setAbsensi(int absensi) {
        this.absensi = absensi;
    }
    public int getTunjanganMakan() {
        return tunjanganMakan;
    }

    public void setTunjanganMakan(int tunjanganMakan) {
        this.tunjanganMakan = tunjanganMakan;
    }

    public ArrayList<String> getEmail() {
        return email;
    }

    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }

    public void tambahAbsensi(){
        this.absensi += 1;
    }
}
