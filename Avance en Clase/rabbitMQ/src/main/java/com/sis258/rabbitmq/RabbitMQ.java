package com.sis258.rabbitmq;

// ✅ IMPORTS CORRECTOS
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class RabbitMQ {

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println("Conectado a RabbitMQ");

        channel.close();
        connection.close();
    }
}