package com.ibank.RestApi.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive {
    public static String message = "";
    public String receiveFromDatabase() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("messageFromDatabase", false, false, false, null);
        System.out.println(" [*] Waiting for messages from database");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String messages = new String(delivery.getBody(), "UTF-8");
            message = messages;
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume("messageFromDatabase", true, deliverCallback, consumerTag -> { });
        return message;
    }
}
