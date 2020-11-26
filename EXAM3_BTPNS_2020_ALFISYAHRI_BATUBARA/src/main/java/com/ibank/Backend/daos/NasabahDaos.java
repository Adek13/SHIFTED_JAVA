package com.ibank.Backend.daos;

import com.google.gson.Gson;
import com.ibank.Backend.entities.Nasabah;
import com.ibank.Backend.rabbitmq.Send;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@org.springframework.stereotype.Service
public class NasabahDaos {

    Send send = new Send();
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private Gson gson = new Gson();

    public NasabahDaos(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityTransaction = this.entityManager.getTransaction();
    }

    public void Register(String req) throws IOException, TimeoutException {
        beginTransaction();
        Nasabah nasabah = gson.fromJson(req, Nasabah.class);
        List<Nasabah> getExist = findByUsername(nasabah.getUsername());
        JSONObject response = new JSONObject();
        if(getExist.isEmpty()){
            entityManager.persist(nasabah);
            commitTransaction();
            response.put("Type", "Success");
            response.put("Message", "Berhasil Register!");
            send.sendToRestApi(response.toJSONString());
        }else{
            System.out.println("Already Exist!");
            response.put("Type", "Failed");
            response.put("Message", "User Exist!");
            send.sendToRestApi(response.toJSONString());
        }
    }

    public void Login(String req) throws IOException, TimeoutException {
        beginTransaction();
        Nasabah nasabah = gson.fromJson(req, Nasabah.class);
        Nasabah filter =  findByUsernamePassword(nasabah.getUsername(), nasabah.getPassword());
        JSONObject response = new JSONObject();
        if (filter != null){
            filter.setLoggedin(1);
            System.out.println(filter.getLoggedin());
            entityManager.merge(filter);
            commitTransaction();
        }else{
            System.out.println("You Are Not Registered!");
            response.put("Type", "Failed");
            response.put("Message", "Not Registered!");
            send.sendToRestApi(new Gson().toJson(response));
        }

    }

    public void Logout(String req) throws IOException, TimeoutException {
        beginTransaction();
        Nasabah nasabah = gson.fromJson(req, Nasabah.class);
        Nasabah filter =  findByUsernamePassword(nasabah.getUsername(), nasabah.getPassword());
        JSONObject response = new JSONObject();
        if (filter != null){
            if (filter.getLoggedin() == 1){
                filter.setLoggedin(0);
                System.out.println(filter.getLoggedin());
                entityManager.merge(filter);
                commitTransaction();
            }else{
                System.out.println("You are already logged out!");
                response.put("Type", "Failed");
                response.put("Message", "Already Logged out!");
                send.sendToRestApi(response.toJSONString());
            }
        }else{
            System.out.println("You Are Not Registered!");
        }
    }
    public void cekSaldo(String req) throws IOException, TimeoutException {
        beginTransaction();
        Nasabah nasabah = gson.fromJson(req, Nasabah.class);
        Nasabah filter =  findByUsernamePassword(nasabah.getUsername(), nasabah.getPassword());
        JSONObject response = new JSONObject();
        if (filter != null){
            if (filter.getLoggedin() == 1){
                String res = new Gson().toJson(filter);
                send.sendToRestApi(res);
            }else{
                System.out.println("Silahkan Login Untuk Cek Saldo!");
                response.put("Type", "Failed");
                response.put("Message", "Silahkan Login untuk Cek Saldo!!");
                send.sendToRestApi(response.toJSONString());
            }
        }else{
            System.out.println("You Are Not Registered!");
            response.put("Type", "Failed");
            response.put("Message", "Not Registered!!");
            send.sendToRestApi(response.toJSONString());
        }
    }


    public Nasabah findByUsernamePassword(String username, String password){
        try {
            return entityManager.createQuery("select n from Nasabah n where n.username=:username and n.password=:password", Nasabah.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .setMaxResults(1)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }

    }

    public List<Nasabah> findByUsername(String username){
       try {
           List<Nasabah> nasabahs = entityManager.createQuery("select n from Nasabah n where n.username=:username", Nasabah.class)
                   .setParameter("username", username)
                   .setMaxResults(1)
                   .getResultList();
           return nasabahs;
       }catch (NoResultException e){
           return null;
       }
    }

    private void beginTransaction() {
        try {
            entityTransaction.begin();
        }catch (Exception e){

        }
    }

    private void commitTransaction() {
        try {
            entityTransaction.commit();
//            entityManager.close();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    private void rollbackTransaction() {
        try {
            entityTransaction.rollback();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

}