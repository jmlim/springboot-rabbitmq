package io.jmlim.springexample.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jmlim.springexample.rabbitmq.payload.Notification;
import io.jmlim.springexample.rabbitmq.type.ExchangeName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMqProducer {

    private final RabbitTemplate rabbit;

    private final ObjectMapper objectMapper;

    private int count = 0;

    @Scheduled(fixedDelay = 2000L)
    public void send() throws JsonProcessingException {
        String message = "Hello Message " + count + "!";
        Notification notification = new Notification();
        notification.setMsg(message);
        notification.setNotificationType("test1");

        String notificationJson = objectMapper.writeValueAsString(notification);

        int num = 7;
        if (count % num == 0) {
            log.info("send message q1: {}", notification);
            rabbit.convertAndSend(ExchangeName.EX, "shuaicj.xxx.zzz", notificationJson);
        } else if (count % num == 1) {
            log.info("send message q2: {}", notification);
            rabbit.convertAndSend(ExchangeName.EX, "xxx.hello.zzz", notificationJson);
        } else if (count % num == 2) {
            log.info("send message q3: {}", notification);
            rabbit.convertAndSend(ExchangeName.EX, "xxx.zzz.amqp", notificationJson);
        } else if (count % num == 3) {
            log.info("send message q1,q2: {}", notification);
            rabbit.convertAndSend(ExchangeName.EX, "shuaicj.hello.xxx", notificationJson);
        } else if (count % num == 4) {
            log.info("send message q1,q3: {}", notification);
            rabbit.convertAndSend(ExchangeName.EX, "shuaicj.xxx.amqp", notificationJson);
        } else if (count % num == 5) {
            log.info("send message q2,q3: {}", notification);
            rabbit.convertAndSend(ExchangeName.EX, "xxx.hello.amqp", notificationJson);
        } else {
            log.info("send message q1,q2,q3: {}", notification);
            rabbit.convertAndSend(ExchangeName.EX, "shuaicj.hello.amqp", notificationJson);
        }
        count++;
    }
}