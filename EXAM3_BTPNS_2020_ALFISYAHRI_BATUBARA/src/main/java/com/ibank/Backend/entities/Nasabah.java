package com.ibank.Backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "nasabah")
public class Nasabah {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nama;
    private String username;
    private String password;
    private String alamat;
    private String norek;
    private String telp;
    private long saldo;
    private int isLoggedin = 0;

    public Nasabah(){

    }

    public Nasabah(String nama, String username, String password, String alamat, String norek, String telp, long saldo) {
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.norek = norek;
        this.telp = telp;
        this.saldo = saldo;
    }
    public Nasabah(long id, String nama, String username, String password, String alamat, String norek, String telp, long saldo) {
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.norek = norek;
        this.telp = telp;
        this.saldo = saldo;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public int getLoggedin() {
        return isLoggedin;
    }

    public void setLoggedin(int loggedin) {
        isLoggedin = loggedin;
    }
}

