package io.jmlim.springexample.rabbitmq.payload;

import lombok.Data;

@Data
public class Notification {
    private String notificationType;
    private String msg;
}
