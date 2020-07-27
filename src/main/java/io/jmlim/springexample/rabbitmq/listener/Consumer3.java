package io.jmlim.springexample.rabbitmq.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jmlim.springexample.rabbitmq.payload.Notification;
import io.jmlim.springexample.rabbitmq.type.QueueName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = QueueName.Q3)
@RequiredArgsConstructor
public class Consumer3 {

    private final ObjectMapper objectMapper;

    @RabbitHandler
    public void receive(String msg) throws JsonProcessingException {
        Notification notification = objectMapper.readValue(msg, Notification.class);

        log.info("Consumer3 receive message: {}", notification);
    }
}