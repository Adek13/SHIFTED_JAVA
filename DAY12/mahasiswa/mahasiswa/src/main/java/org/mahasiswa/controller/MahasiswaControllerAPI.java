package org.mahasiswa.controller;

import org.mahasiswa.model.Mahasiswa;

import org.mahasiswa.service.Service;

import org.mahasiswa.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api")
public class MahasiswaControllerAPI {

    public static final Logger logger = LoggerFactory.getLogger(MahasiswaControllerAPI.class);

    @Autowired
    Service service;

    /** GET mahasiswa and details based on idmahasiswa from table mahasiswa and table details **/
    @RequestMapping(value = "/both/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBothById(@PathVariable("id") int id) {
        List<Mahasiswa> mahasiswa = service.getBothById(id);

        if (mahasiswa.isEmpty()){
            logger.error("No Data In Database");
            return new ResponseEntity<>(new CustomErrorType("No Data Found!"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
    }

    /** GET all mahasiswa and details from table mahasiswa and details **/
    @RequestMapping(value = "/both/", method = RequestMethod.GET)
    public ResponseEntity<?> getBoth() {
        List<Mahasiswa> mahasiswa = service.getBoth();

        if (mahasiswa.isEmpty()){
            logger.error("No Data In Database");
            return new ResponseEntity<>(new CustomErrorType("No Data Found!"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
    }

    /** POST | add data to table mahasiswa**/
    @RequestMapping(value = "/mahasiswa/", method = RequestMethod.POST)
    public ResponseEntity<?> addMahasiswa(@RequestBody Mahasiswa mahasiswa) {

        if(service.AddMahasiswa(mahasiswa) <= 0){
            logger.error("Cant Add Data Mahasiswa!");
            return new ResponseEntity<>(new CustomErrorType("Internal Server Error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
    }

    /** PUT | Update data on table mahasiswa **/
    @RequestMapping(value = "/mahasiswa/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMahasiswa(@RequestBody Mahasiswa mahasiswa, @PathVariable("id") int id) {

        mahasiswa.setIdmahasiswa(id);
        if(service.UpdateMahasiswa(mahasiswa)<=0){
            logger.error("Cant Update Data!");
            return new ResponseEntity<>(new CustomErrorType("Internal Server Error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
    }

    /** PUT absensi on table mahasiswa **/
    @RequestMapping(value = "/absensi/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> absensi(@PathVariable("id") int id) {

        if (service.absensi(id) <= 0){
            logger.error("Cant Update Absensi!");
            return new ResponseEntity<>(new CustomErrorType("Internal Server Error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /** DELETE mahasiswa and details Based On idmahasiswa **/
    @RequestMapping(value = "/mahasiswa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {

        if (!service.deleteById(id)){
            logger.error("Cant Delete mahasiswa with id : {}", id);
            return new ResponseEntity<>(new CustomErrorType("Mahasiswa Not Found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /** DELETE all mahasiswa and details **/
    @RequestMapping(value = "/mahasiswa/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAll() {

        if (!service.deleteAll()){
            logger.error("Cant Delete mahasiswa");
            return new ResponseEntity<>(new CustomErrorType("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /** GET mahasiswa based on idmahasiswa from table mahasiswa **/
    @RequestMapping(value = "/mahasiswa/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMahasiswaById(@PathVariable("id") int id) {
        List<Mahasiswa> mahasiswa = service.getMahasiswaById(id);

        if (mahasiswa.isEmpty()){
            logger.error("No Data In Database");
            return new ResponseEntity<>(new CustomErrorType("No Data Found!"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
    }

    /** GET all mahasiswa and details from table mahasiswa **/
    @RequestMapping(value = "/mahasiswa/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMahasiswa() {
        List<Mahasiswa> mahasiswa = service.getMahasiswa();

        if (mahasiswa.isEmpty()){
            logger.error("No Data In Database");
            return new ResponseEntity<>(new CustomErrorType("No Data Found!"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
    }

    /** GET details based on idmahasiswa from table details **/
    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetailsById(@PathVariable("id") int id) {
        List<Mahasiswa> mahasiswa = service.getDetailsById(id);

        if (mahasiswa.isEmpty()){
            logger.error("No Data In Database");
            return new ResponseEntity<>(new CustomErrorType("No Data Found!"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
    }

    /** GET all details from table details **/
    @RequestMapping(value = "/details/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDetails() {
        List<Mahasiswa> mahasiswa = service.getDetails();

        if (mahasiswa.isEmpty()){
            logger.error("No Data In Database");
            return new ResponseEntity<>(new CustomErrorType("No Data Found!"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
    }

    /** POST | add data to table details based on idmahasiswa **/
    @RequestMapping(value = "/details/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> addDetails(@RequestBody Mahasiswa mahasiswa, @PathVariable("id") int id) {
        mahasiswa.setIdmahasiswa(id);
        if(service.ifDetailsExist(id) > 0){
            logger.error("Details Already Exist");
            return new ResponseEntity<>(new CustomErrorType("Details Already Exist!"), HttpStatus.CONFLICT);
        }
        service.AddDetails(mahasiswa);
        return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
    }

    /** PUT | Update data on table details **/
    @RequestMapping(value = "/details/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDetails(@RequestBody Mahasiswa mahasiswa, @PathVariable("id") int id) {

        mahasiswa.setIdmahasiswa(id);
        if(service.UpdateDetails(mahasiswa)<=0){
            logger.error("Cant Update Details!");
            return new ResponseEntity<>(new CustomErrorType("Internal Server Error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
    }

    /** DELETE details Based On idmahasiswa **/
    @RequestMapping(value = "/details/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deteleteDetailsById(@PathVariable("id") int id) {

        if (service.deleteDetailsById(id)<=0){
            logger.error("Cant Delete Details if idmahasiswa : {}", id);
            return new ResponseEntity<>(new CustomErrorType("Details Not Found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /** DELETE all details **/
    @RequestMapping(value = "/details/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllDetails() {

        if (service.deleteAllDetails()<=0){
            logger.error("Cant Delete Details");
            return new ResponseEntity<>(new CustomErrorType("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
