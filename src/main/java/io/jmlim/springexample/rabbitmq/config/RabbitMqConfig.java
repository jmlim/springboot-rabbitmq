package io.jmlim.springexample.rabbitmq.config;

import io.jmlim.springexample.rabbitmq.type.ExchangeName;
import io.jmlim.springexample.rabbitmq.type.QueueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue1() {
        return new Queue(QueueName.Q1, false);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QueueName.Q2, false);
    }

    @Bean
    public Queue queue3() {
        return new Queue(QueueName.Q3, false);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(ExchangeName.EX);
    }

    /*@Bean
    DirectExchange exchange() {
        return new DirectExchange(rabbitMQProperties().getExchangeName());
    }*/

    @Bean
    Binding binding1() {
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("shuaicj.#");
    }

    @Bean
    Binding binding2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("*.hello.*");
    }

    @Bean
    Binding binding3() {
        return BindingBuilder.bind(queue3()).to(topicExchange()).with("*.*.amqp");
    }

/*
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory*//*,
                                                    MessageListenerAdapter listenerAdapter*//*) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(JMLIM_SAMPLE_QUEUE);
        // container.setMessageListener(listenerAdapter);
        return container;
    }*/
/*
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }*/
/*

    @Bean
    public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
*/

/*    @Bean
    MessageListenerAdapter listenerAdapter(RabbitMqListener listener) {
        return new MessageListenerAdapter(listener, "listen");
    }*/
}
