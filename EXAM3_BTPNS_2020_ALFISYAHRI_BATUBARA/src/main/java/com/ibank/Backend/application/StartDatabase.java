package com.ibank.Backend.application;

import com.ibank.Backend.daos.NasabahDaos;
import com.ibank.Backend.entities.Nasabah;
import com.ibank.Backend.rabbitmq.Receive;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class StartDatabase {

    private final NasabahDaos nasabahDaos;
    private Receive receive = new Receive();

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public static void main(String[] args) throws Exception {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("nasabah-unit")
                .createEntityManager();
        NasabahDaos nasabahDaos = new NasabahDaos(entityManager);
        new StartDatabase(nasabahDaos).run();
    }

    public StartDatabase(NasabahDaos nasabahDaos) {
        this.nasabahDaos = nasabahDaos;
    }

    private void run() throws IOException, TimeoutException {
        System.out.println(" [*] Waiting for messages..");
        receive.Register(nasabahDaos);
        receive.Login(nasabahDaos);
        receive.Logout(nasabahDaos);
        receive.cekSaldo(nasabahDaos);
    }

}