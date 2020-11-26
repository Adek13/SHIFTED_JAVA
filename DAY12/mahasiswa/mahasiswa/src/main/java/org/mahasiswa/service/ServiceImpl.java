package org.mahasiswa.service;

import org.mahasiswa.model.Mahasiswa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.xml.soap.Detail;
import java.util.List;

@org.springframework.stereotype.Service("Service")
public class ServiceImpl implements Service {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int generateId() {
        return jdbcTemplate.queryForObject("SELECT idmahasiswa from mahasiswa ORDER BY idmahasiswa DESC LIMIT 1", Integer.class);
    }

    @Override
    public List<Mahasiswa> getBothById(int id){
        return jdbcTemplate.query("SELECT * FROM mahasiswa a JOIN details b ON a.idmahasiswa = b.idmahasiswa WHERE a.idmahasiswa = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        new Mahasiswa(
                                rs.getInt("idmahasiswa"),
                                rs.getString("fullname"),
                                rs.getString("address"),
                                rs.getString("status"),
                                rs.getInt("absensi"),
                                rs.getInt("physics"),
                                rs.getInt("calculus"),
                                rs.getInt("biologi")
                        ));
    }

    @Override
    public List<Mahasiswa> getBoth() {
        return jdbcTemplate.query("SELECT * FROM mahasiswa a JOIN details b ON a.idmahasiswa = b.idmahasiswa",
                (rs, rowNum) ->
                        new Mahasiswa(
                                rs.getInt("idmahasiswa"),
                                rs.getString("fullname"),
                                rs.getString("address"),
                                rs.getString("status"),
                                rs.getInt("absensi"),
                                rs.getInt("physics"),
                                rs.getInt("calculus"),
                                rs.getInt("biologi")
                        ));
    }

    @Override
    public int AddMahasiswa(Mahasiswa mahasiswa) {
        int id = generateId() + 1;
        if (ifExist(id) > 0){
            return 0;
        }
        return jdbcTemplate.update(
                "INSERT INTO mahasiswa (idmahasiswa, fullname, address, status, absensi) VALUES(?, ?, ?, ?, ?)",
                id,
                mahasiswa.getFullname(),
                mahasiswa.getAddress(),
                mahasiswa.getStatus(),
                mahasiswa.getAbsensi()
        );
    }

    @Override
    public int UpdateMahasiswa(Mahasiswa mahasiswa) {
        return jdbcTemplate.update(
                "UPDATE mahasiswa SET fullname = ?, address = ?, status = ?, absensi = ? WHERE idmahasiswa = ?",
                mahasiswa.getFullname(),
                mahasiswa.getAddress(),
                mahasiswa.getStatus(),
                mahasiswa.getAbsensi(),
                mahasiswa.getIdmahasiswa()
        );
    }

    @Override
    public int absensi(int id){
        return jdbcTemplate.update("UPDATE mahasiswa set absensi = absensi + 1 WHERE idmahasiswa = ?", id);
    }

    @Override
    public Boolean deleteById(int id){
        int iMahasiswa = jdbcTemplate.update("DELETE FROM mahasiswa WHERE idmahasiswa = ?", id);
        int iDetails = jdbcTemplate.update("DELETE FROM details WHERE idmahasiswa = ?", id);

        if(iMahasiswa > 0 && iDetails > 0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteAll(){
        int iMahasiswa = jdbcTemplate.update("DELETE FROM mahasiswa");
        int iDetails = jdbcTemplate.update("DELETE FROM details");

        if(iMahasiswa > 0 && iDetails > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Mahasiswa> getMahasiswaById(int id){
        return jdbcTemplate.query("SELECT * FROM mahasiswa WHERE idmahasiswa = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        new Mahasiswa(
                                rs.getInt("idmahasiswa"),
                                rs.getString("fullname"),
                                rs.getString("address"),
                                rs.getString("status"),
                                rs.getInt("absensi")
                        ));
    }

    @Override
    public List<Mahasiswa> getMahasiswa() {
        return jdbcTemplate.query("SELECT * FROM mahasiswa",
                (rs, rowNum) ->
                        new Mahasiswa(
                                rs.getInt("idmahasiswa"),
                                rs.getString("fullname"),
                                rs.getString("address"),
                                rs.getString("status"),
                                rs.getInt("absensi")
                        ));
    }

    @Override
    public List<Mahasiswa> getDetailsById(int id){
        return jdbcTemplate.query("SELECT * FROM details WHERE idmahasiswa = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        new Mahasiswa(
                                rs.getInt("idmahasiswa"),
                                rs.getInt("physics"),
                                rs.getInt("calculus"),
                                rs.getInt("biologi")
                        ));
    }

    @Override
    public List<Mahasiswa> getDetails() {
        return jdbcTemplate.query("SELECT * FROM details",
                (rs, rowNum) ->
                        new Mahasiswa(
                                rs.getInt("idmahasiswa"),
                                rs.getInt("physics"),
                                rs.getInt("calculus"),
                                rs.getInt("biologi")
                        ));
    }

    @Override
    public int ifExist(int id){
        return jdbcTemplate.queryForObject("SELECT count(*) FROM mahasiswa where idmahasiswa = "+id+"",Integer.class);
    }

    public int ifDetailsExist(int id){
        return jdbcTemplate.queryForObject("SELECT count(*) FROM details where idmahasiswa = "+id+"",Integer.class);
    }

    @Override
    public int AddDetails(Mahasiswa mahasiswa) {
        return jdbcTemplate.update(
                "INSERT INTO details (idmahasiswa, physics, calculus, biologi) VALUES(?, ?, ?, ?)",
                mahasiswa.getIdmahasiswa(),
                mahasiswa.getPhysics(),
                mahasiswa.getCalculus(),
                mahasiswa.getBiologi()
        );
    }

    @Override
    public int UpdateDetails(Mahasiswa mahasiswa) {
        return jdbcTemplate.update(
                "UPDATE details SET physics = ?, calculus = ?, biologi = ? WHERE idmahasiswa = ?",
                mahasiswa.getPhysics(),
                mahasiswa.getCalculus(),
                mahasiswa.getBiologi(),
                mahasiswa.getIdmahasiswa()
        );
    }

    @Override
    public int deleteDetailsById(int id){
        return jdbcTemplate.update("DELETE FROM details WHERE idmahasiswa = ?", id);

    }

    @Override
    public int deleteAllDetails(){
       return jdbcTemplate.update("DELETE FROM details");

    }
}
