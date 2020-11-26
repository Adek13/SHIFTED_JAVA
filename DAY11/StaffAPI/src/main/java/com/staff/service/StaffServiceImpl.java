package com.staff.service;

import com.staff.model.Staff;
import org.springframework.stereotype.Service;

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

    //Method GET all data staff
    public List<Staff> findAllStaff() {
        return new ArrayList<>(staffs.values());
    }

    // Method find staff based on IDKaryawan
    public Staff findById(long id) {
            return staffs.get(id);
    }

    // Method find staff based on Nama Karyawan
    public Staff findByName(String name) {
        if (idNameHashMap.get(name) != null){
            return staffs.get(idNameHashMap.get(name));
        }

        return null;
    }

    // Method untuk save atau add data ke ArrayList staff
    public void saveStaff(Staff staff) {
        synchronized (this) {    //  Critical section synchronized
            staffs.put(staff.getId(), staff);
            idNameHashMap.put(staff.getNama(), staff.getId());
        }
    }

    // Method update data staff
    public void updateStaff(Staff staff) {
        synchronized (this) {    //  Critical section synchronized
            staffs.put(staff.getId(), staff);
            idNameHashMap.put(staff.getNama(), staff.getId());
        }
    }

    // Method untuk hapus data staff berdasarkan IDKaryawan
    public void deleteStaffById(long id) {
        synchronized (this) {    //  Critical section synchronized
            idNameHashMap.remove(staffs.get(id).getNama());
            staffs.remove(id);
        }
    }

    //Method untuk cek eksistensi data dalam ArrayList staff
    public boolean isStaffExist(Staff staff) {
        return findByName(staff.getNama()) != null;
    }

    //Method untuk menghapus semua data di dalam ArrayList Staff
    public void deleteAllStaff() {
        staffs.clear();
    }

}
