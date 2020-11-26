package com.day09.Tugas01;

abstract class Worker {
    int iIdKaryawan = 0, iAbsen = 20, iTunjPulsa = 500000;
    String sNama = "";

    abstract void AbsensiMethod();
    abstract void setId(int newValue);
    abstract int getId();
    abstract void setNama(String newValue);
    abstract String getNama();
    abstract int getAbsen();
    abstract int getTunjPulsa();
}
