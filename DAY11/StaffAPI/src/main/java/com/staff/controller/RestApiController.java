package com.staff.controller;

import com.staff.model.Staff;
import com.staff.service.StaffService;
import com.staff.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Define Rest Controller
@RestController

//mapping/routing api
@RequestMapping("/api")
public class RestApiController {

    //define logger
    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    StaffService staffService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Staff--------------------------------------------
    // mapping GET staff yang selanjutnya diteruskan ke StaffService method findAllStaff yang mereturn semua data dalam arraylist staff
    @RequestMapping(value = "/staff/", method = RequestMethod.GET)
    public ResponseEntity<List<Staff>> listAllStaff() {
        List<Staff> staff = staffService.findAllStaff();
        if (staff.isEmpty()) {
            return new ResponseEntity<>(staff, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    // -------------------Retrieve Single Staff------------------------------------------
    /* mapping GET staff berdasarkan id dari PathVariable, diteruskan ke StaffService yang selanjutnya menjalankan method
        findById yang mereturn data staff berdasarkan id dari parameter PathVariable
     */
    @RequestMapping(value = "/staff/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStaff(@PathVariable("id") long id) {
        logger.info("Fetching Staff with id {}", id);
        Staff staff = staffService.findById(id);
        // cek hasil return StaffService, jika null maka response/return error
        if (staff == null) {
            logger.error("Staff with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Product with id " + id  + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    // -------------------Create a Staff-------------------------------------------
    /* Mapping POST staff, dari body request di buat object Staff, lalu diteruskan ke method saveStaff di StaffService
     */
    @RequestMapping(value = "/staff/", method = RequestMethod.POST)
    public ResponseEntity<?> createStaff(@RequestBody Staff staff) {
        logger.info("Creating Staff : {}", staff);
        //Cek eksistensi staff yang ingin ditambahkan, jika ada return/response error
        if (staffService.isStaffExist(staff)) {
            logger.error("Unable to create. A Staff with name {} already exist", staff.getNama());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Staff with name " +
                    staff.getNama() + " already exist."), HttpStatus.CONFLICT);
        }
        // do saveStaff di StaffService
        staffService.saveStaff(staff);
        return new ResponseEntity<>(staff, HttpStatus.CREATED);
    }

    // ------------------- Update a Staff ------------------------------------------------
    /* Mapping PUT untuk update data staff berdasarkan id, dengan alur pertama pengecekan data apakah ada atau tidak, jika tidak ada
     * return error, jika ada lanjutkan ke StaffService pada method updateStaff
     */
    @RequestMapping(value = "/staff/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStaff(@PathVariable("id") long id, @RequestBody Staff staff) {
        logger.info("Updating Staff with id {}", id);

        Staff currentStaff = staffService.findById(id);
        //Cek data staff yang akan di update, jika null return error
        if (currentStaff == null) {
            logger.error("Unable to update. Staff with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Staff with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentStaff.setNama(staff.getNama());
        currentStaff.setTunjanganPulsa(staff.getTunjanganPulsa());
        currentStaff.setGajiPokok(staff.getGajiPokok());
        currentStaff.setAbsensi(staff.getAbsensi());
        currentStaff.setNama(staff.getNama());
        currentStaff.setNama(staff.getNama());

        staffService.updateStaff(currentStaff);
        return new ResponseEntity<>(currentStaff, HttpStatus.OK);
    }

    // ------------------- Delete a Staff-----------------------------------------
    // Mapping DELETE staff berdasarkan id, selanjutnya pengecekan eksistensi jika ada maka lanjutkan proses delete
    @RequestMapping(value = "/staff/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStaff(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Staff with id {}", id);

        Staff staff = staffService.findById(id);
        if (staff == null) {
            logger.error("Unable to delete. Staff with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Staff with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        staffService.deleteStaffById(id);
        return new ResponseEntity<Staff>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Products-----------------------------
    // Mapping DELETE untuk semua data di dalam arraylist staff
    @RequestMapping(value = "/staff/", method = RequestMethod.DELETE)
    public ResponseEntity<Staff> deleteAllStaff() {
        logger.info("Deleting All Staff");

        staffService.deleteAllStaff();
        return new ResponseEntity<Staff>(HttpStatus.NO_CONTENT);
    }

}