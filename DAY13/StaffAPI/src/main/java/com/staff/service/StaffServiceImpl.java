package com.staff.service;

import com.staff.model.Staff;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Service Staff
 * Class yang mengimplementasikan interface StaffService (Meneruskan Proses Dari Controller)
 */

@Service("staffService")
public class StaffServiceImpl implements StaffService {


    //  Using two hashmaps in order to provide performance of O(1) while fetching products
    private static HashMap<Long, Staff> staffs = new HashMap<>(); // ArrayList untuk menampung data staff dengan id sebagai key
    private static HashMap<String, Long> idNameHashMap = new HashMap<>(); // ArrayList untuk menampung data id staff dengan nama sebagai key
    private final String driverName = "com.mysql.cj.jdbc.Driver";
    private final String connectionUrl = "jdbc:mysql://localhost:3306/db_karyawan";
    private final String userName = "root";
    private final String userPass = "root1234";
    private static Connection con = null;
    private static Statement stmt = null;

    //Method GET all data staff
    public List<Staff> findAllStaff() {
        ConnectToDB();
        String sql = "SELECT * FROM staff";
        List<Staff> staffCollection = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()){
                return null;
            }
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt(1));
                staff.setNama(rs.getString(2));
                staff.setTunjanganPulsa(rs.getInt(3));
                staff.setGajiPokok(rs.getInt(4));
                staff.setAbsensi(rs.getInt(5));
                staff.setTunjanganMakan(rs.getInt(6));
                staff.setEmail(rs.getString(7));
                staffCollection.add(staff);
            }
            CloseConnection();
            return staffCollection;
        }catch (Exception e){
            System.out.println(e.getMessage());
            CloseConnection();
            return null;
        }
    }

    // Method find staff based on IDKaryawan
    public Staff findById(long id) {
        ConnectToDB();
        Staff staff = new Staff();
        String sql = "SELECT * FROM staff WHERE id = '"+ id +"'";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()){
                return null;
            }
            while (rs.next()){
                staff.setId(rs.getInt(1));
                staff.setNama(rs.getString(2));
                staff.setTunjanganPulsa(rs.getInt(3));
                staff.setGajiPokok(rs.getInt(4));
                staff.setAbsensi(rs.getInt(5));
                staff.setTunjanganMakan(rs.getInt(6));
                staff.setEmail(rs.getString(7));
            }
            CloseConnection();
            return staff;
        }catch (Exception e){
            System.out.println(e.getMessage());
            CloseConnection();
            return null;
        }
    }

    // Method find staff based on Nama Karyawan
    public Staff findByName(String name) {
        try {
            Staff staff = new Staff();
            ConnectToDB();
            String sql = "SELECT * FROM staff WHERE nama = '"+ name +"'";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()){
                return null;
            }
            while (rs.next()){
                staff.setId(rs.getInt(1));
                staff.setNama(rs.getString(2));
                staff.setTunjanganPulsa(rs.getInt(3));
                staff.setGajiPokok(rs.getInt(4));
                staff.setTunjanganMakan(rs.getInt(5));
                staff.setEmail(rs.getString(6));
            }
            CloseConnection();
            return staff;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    // Method untuk save atau add data ke ArrayList staff
    public int saveStaff(Staff staff) {
        synchronized (this) {    //  Critical section synchronized
            try {
                ConnectToDB();
                String sql = "INSERT INTO staff (nama, tunjanganPulsa, gajiPokok, absensi, tunjanganMakan, email) VALUES (" +
                        "'" + staff.getNama() + "'," +
                        "'" + staff.getTunjanganPulsa() + "'," +
                        "'" + staff.getGajiPokok() + "'," +
                        "'" + staff.getAbsensi() + "'," +
                        "'" + staff.getTunjanganMakan() + "'," +
                        "'" + staff.getEmail() + "'" +
                        ")";
                int insert =  stmt.executeUpdate(sql);
                CloseConnection();
                return insert;
            }catch (Exception e){
                System.out.println(e);
                return 0;
            }
        }
    }

    // Method update data staff
    public int updateStaff(Staff staff) {
        synchronized (this) {    //  Critical section synchronized
            try {
                ConnectToDB();
                String sql = "UPDATE staff SET " +
                        "nama = '" + staff.getNama() + "', " +
                        "tunjanganPulsa = '" + staff.getTunjanganPulsa() + "', " +
                        "gajiPokok = '" + staff.getGajiPokok() + "', " +
                        "absensi = '" + staff.getAbsensi() + "', " +
                        "tunjanganMakan = '" + staff.getTunjanganMakan() + "'" +
                        "WHERE id = '"+ staff.getId() +"'";
                int update =  stmt.executeUpdate(sql);
                CloseConnection();
                return update;
            }catch (Exception e){
                System.out.println(e);
                return 0;
            }
        }
    }

    // Method untuk hapus data staff berdasarkan IDKaryawan
    public int deleteStaffById(long id) {
        synchronized (this) {    //  Critical section synchronized
            try {
                Staff staff = new Staff();
                ConnectToDB();
                String sql = "DELETE FROM staff WHERE id = '"+ id +"'";
                int delete = stmt.executeUpdate(sql);
                CloseConnection();
                return delete;
            }catch (Exception e){
                System.out.println(e);
                return 0;
            }
        }
    }

    //Method untuk cek eksistensi data dalam ArrayList staff
    public boolean isStaffExist(Staff staff) {
        return findByName(staff.getNama()) != null;
    }

    //Method untuk menghapus semua data di dalam ArrayList Staff
    public int deleteAllStaff() {
        try {
            Staff staff = new Staff();
            ConnectToDB();
            String sql = "DELETE FROM staff";
            int delete = stmt.executeUpdate(sql);
            CloseConnection();
            return delete;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public void ConnectToDB(){
        try {
            Class.forName(driverName);
            con= DriverManager.getConnection(connectionUrl,userName,userPass);
            stmt=con.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void CloseConnection(){
        try {
            if (!con.isClosed()){
                con.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
