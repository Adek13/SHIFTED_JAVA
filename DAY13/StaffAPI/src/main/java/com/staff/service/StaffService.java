package com.staff.service;


import com.staff.model.Staff;

import java.util.List;

//Define interface StaffService yang akan diimplementasikan di StaffServiceImpl
public interface StaffService {
	
	Staff findById(long IDKaryawan);
	
	Staff findByName(String nama);
	
	int saveStaff(Staff worker);
	
	int updateStaff(Staff staff);
	
	int deleteStaffById(long id);

	List<Staff> findAllStaff();
	
	int deleteAllStaff();
	
	boolean isStaffExist(Staff staff);

	void ConnectToDB();
	
}
