package com.ibank.RestApi.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Send {

    public void Register(String req) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("register", false, false, false, null);
            channel.basicPublish("", "register", null, req.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + req + "'");
        }
    }
    public void Login(String req) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("login", false, false, false, null);
            channel.basicPublish("", "login", null, req.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + req + "'");
        }
    }
    public void Logout(String req) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("logout", false, false, false, null);
            channel.basicPublish("", "logout", null, req.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + req + "'");
        }
    }

    public void Saldo(String req) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("saldo", false, false, false, null);
            channel.basicPublish("", "saldo", null, req.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + req + "'");
        }
    }

}
