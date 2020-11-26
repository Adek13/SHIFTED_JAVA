package com.staff.service;


import com.staff.model.Staff;

import java.util.List;

//Define interface StaffService yang akan diimplementasikan di StaffServiceImpl
public interface StaffService {
	
	Staff findById(long IDKaryawan);
	
	Staff findByName(String nama);
	
	void saveStaff(Staff worker);
	
	void updateStaff(Staff staff);
	
	void deleteStaffById(long id);

	List<Staff> findAllStaff();
	
	void deleteAllStaff();
	
	boolean isStaffExist(Staff staff);
	
}
