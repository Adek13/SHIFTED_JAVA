package com.staff.model;

import java.util.concurrent.atomic.AtomicLong;

/** Abstract Class Worker
 * deklarasi method abstrak yang harus di define di staff
 */
abstract class Worker {
    public static AtomicLong counter = new AtomicLong(); // auto counter/increment untuk IDKaryawan

    public long id;

    public String nama;

    public int tunjanganPulsa, gajiPokok, absensi;

    public abstract long getId();

    public abstract void setId(long id);

    public abstract String getNama();

    public abstract void setNama(String nama);

    public abstract int getTunjanganPulsa();

    public abstract void setTunjanganPulsa(int tunjanganPulsa);

    public abstract int getGajiPokok();

    public abstract void setGajiPokok(int gajiPokok);

    public abstract int getAbsensi();

    public abstract void setAbsensi(int absensi);

    public abstract void tambahAbsensi();


}
