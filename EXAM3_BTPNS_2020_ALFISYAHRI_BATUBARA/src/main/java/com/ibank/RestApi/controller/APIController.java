package com.ibank.RestApi.controller;

import com.ibank.Backend.entities.Nasabah;
import com.ibank.RestApi.RabbitMQ.Receive;
import com.ibank.RestApi.RabbitMQ.Send;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api")
public class APIController {

    Send send = new Send();
    Receive receive = new Receive();
    Gson gson = new Gson();

    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public ResponseEntity<?> Register(@RequestBody Nasabah req){
        String res = "";
        try {
            send.Register(new Gson().toJson(req));
            res = receive.receiveFromDatabase();
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
        }
        return new ResponseEntity<>("Gagal", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ResponseEntity<?> Login(@RequestBody Nasabah req){
        String res = "";
        try {
            send.Login(new Gson().toJson(req));
            res = receive.receiveFromDatabase();
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
        }
        return new ResponseEntity<>("Login Requested", HttpStatus.OK);
    }
    @RequestMapping(value = "/logout/", method = RequestMethod.POST)
    public ResponseEntity<?> Logout(@RequestBody Nasabah req){
        String res = "";
        try {
            send.Logout(new Gson().toJson(req));
            res = receive.receiveFromDatabase();
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
        }
        return new ResponseEntity<>("Logout Requested", HttpStatus.OK);
    }
    @RequestMapping(value = "/saldo/", method = RequestMethod.GET)
    public ResponseEntity<?> Saldo(@RequestBody Nasabah req){
        String res = "";
        try {
            send.Saldo(new Gson().toJson(req));
            res = receive.receiveFromDatabase();
            while (res.equals("")){
                res = receive.receiveFromDatabase();
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(res);
                return new ResponseEntity<>("Saldo :" + json.get("saldo"), HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println("error = " + e);
        }
        return new ResponseEntity<>("Saldo Requested", HttpStatus.OK);
    }
}
