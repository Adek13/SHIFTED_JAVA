package com.day09.Tugas01;
import java.lang.reflect.Array;
import java.util.ArrayList;
import org.json.simple.JSONArray;
class Staff extends Worker {
    int iTunjMakan = 0, iGapok = 5000000;
    ArrayList email = new ArrayList<>();

    Staff(int id, String nama, ArrayList email){
        this.iIdKaryawan = id;
        this.sNama = nama;
        this.email = email;
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
    public ArrayList<String> getEmail(){return this.email;}
}
