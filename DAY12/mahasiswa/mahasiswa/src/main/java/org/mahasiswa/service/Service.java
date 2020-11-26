package org.mahasiswa.service;

import org.mahasiswa.model.Mahasiswa;

import java.util.List;

public interface Service {

    int generateId();

    List<Mahasiswa> getBothById(int id);

    List<Mahasiswa> getBoth();

    List<Mahasiswa> getMahasiswaById(int id);

    List<Mahasiswa> getMahasiswa();

    List<Mahasiswa> getDetailsById(int id);

    List<Mahasiswa> getDetails();

    int AddMahasiswa(Mahasiswa mahasiswa);

    int UpdateMahasiswa(Mahasiswa mahasiswa);

    int absensi(int id);

    Boolean deleteById(int id);

    Boolean deleteAll();

    int AddDetails(Mahasiswa mahasiswa);

    int UpdateDetails(Mahasiswa mahasiswa);

    int deleteDetailsById(int id);

    int deleteAllDetails();

    int ifExist(int id);

    int ifDetailsExist(int id);
}
