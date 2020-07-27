package io.jmlim.springexample.rabbitmq.listener;

import com.google.gson.Gson;
import io.jmlim.springexample.rabbitmq.payload.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class RabbitMqListener {

    //  @RabbitListener(queues="jmlim.sample.queue")
    public void listen(byte[] message) {
        String msg = new String(message);
        Notification not = new Gson().fromJson(msg, Notification.class);
        System.out.println("Received a new notification...");
        System.out.println(not.toString());
    }
}