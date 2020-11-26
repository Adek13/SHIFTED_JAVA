package org.mahasiswa.model;

public class Mahasiswa extends Details{

    private String fullname, address, status;

    private int idmahasiswa, absensi;

    public Mahasiswa(){
        this.idmahasiswa = 0;

        this.fullname = null;

        this.address = null;

        this.status = null;

        this.absensi = 0;

        this.physics = 0;

        this.calculus = 0;

        this.biologi = 0;
    }
    public Mahasiswa(int idmahasiswa, String fullname, String address, String status, int absensi){

        this.idmahasiswa = idmahasiswa;

        this.fullname = fullname;

        this.address = address;

        this.status = status;

        this.absensi = absensi;
    }

    public Mahasiswa(int idmahasiswa, String fullname, String address, String status, int absensi, int physics, int calculus, int biologi) {
//        super();
        this.idmahasiswa = idmahasiswa;

        this.fullname = fullname;

        this.address = address;

        this.status = status;

        this.absensi = absensi;

        this.physics = physics;

        this.calculus = calculus;

        this.biologi = biologi;
    }

    public Mahasiswa (int idmahasiswa, int physics, int calculus, int biologi){

        this.idmahasiswa = idmahasiswa;

        this.physics = physics;

        this.calculus = calculus;

        this.biologi = biologi;
    }
    public int getIdmahasiswa() {
        return this.idmahasiswa;
    }

    public void setIdmahasiswa(int idmahasiswa) {
        this.idmahasiswa = idmahasiswa;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAbsensi() {
        return absensi;
    }

    public void setAbsensi(int absensi) {
        this.absensi = absensi;
    }

    public int getPhysics() {
        return this.physics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public int getCalculus() {
        return this.calculus;
    }

    public void setCalculus(int calculus) {
        this.calculus = calculus;
    }

    public int getBiologi() {
        return this.biologi;
    }

    public void setBiologi(int biologi) {
        this.biologi = biologi;
    }
}
