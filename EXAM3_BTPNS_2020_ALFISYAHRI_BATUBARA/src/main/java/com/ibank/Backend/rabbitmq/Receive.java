package com.ibank.Backend.rabbitmq;

import com.ibank.Backend.daos.NasabahDaos;
import com.ibank.Backend.entities.Nasabah;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Receive {

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
//    private Adapter adapter = new Adapter();


    public void connectRabbitMQ() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
    }

    public void Register(NasabahDaos nasabahDaos) {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("register", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String req = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + req + "'");
                try {
                    nasabahDaos.Register(req);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("register", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Registering = " + e);
        }
    }

    public void cekSaldo(NasabahDaos nasabahDaos) {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("saldo", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String req = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + req + "'");
                try {
                    nasabahDaos.cekSaldo(req);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("saldo", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Checking = " + e);
        }
    }

    public void Login(NasabahDaos nasabahDaos) {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("login", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String req = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + req + "'");
                try {
                    nasabahDaos.Login(req);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("login", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Login = " + e);
        }
    }

    public void Logout(NasabahDaos nasabahDaos) {
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("logout", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String req = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + req + "'");
                try {
                    nasabahDaos.Logout(req);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("logout", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Logout = " + e);
        }
    }
}
