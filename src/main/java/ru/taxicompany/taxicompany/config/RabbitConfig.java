package ru.taxicompany.taxicompany.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        String rabbitHost = System.getenv("RABBIT_HOST");
        if (rabbitHost == null || rabbitHost.isEmpty()) {
            rabbitHost = "localhost";
        }
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(rabbitHost); //"rabbitmq"
        cachingConnectionFactory.setUsername("rabbit");
        cachingConnectionFactory.setPassword("rabbit");
        cachingConnectionFactory.setVirtualHost("email");
        return cachingConnectionFactory;
    }
    @Bean
    public AmqpAdmin amqpAdmin(){
        return  new RabbitAdmin(connectionFactory());
    }
    @Bean
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(connectionFactory());
    }
    @Bean
    public DirectExchange exchange(){
        return new DirectExchange("exchangeEmail");
    }
    @Bean
    public Queue registrationQueue(){
        return new Queue("registrationQueue");
    }
    @Bean
    public Binding registration(){
        return BindingBuilder.bind(registrationQueue()).to(exchange()).with("registrationKey");
    }


    @Bean
    public Queue rentQueue(){
        return new Queue("rentQueue");
    }
    @Bean
    public Binding rent(){
        return BindingBuilder.bind(rentQueue()).to(exchange()).with("rentKey");
    }


    @Bean
    public Queue returnQueue(){
        return new Queue("returnQueue");
    }
    @Bean
    public Binding returnCar(){
        return BindingBuilder.bind(returnQueue()).to(exchange()).with("returnKey");
    }


    @Bean
    public Queue adminQueue(){
        return new Queue("adminQueue");
    }
    @Bean
    public Binding admin(){
        return BindingBuilder.bind(adminQueue()).to(exchange()).with("adminKey");
    }


    @Bean
    public Queue authQueue(){
        return new Queue("authQueue");
    }
    @Bean
    public Binding auth(){
        return BindingBuilder.bind(authQueue()).to(exchange()).with("authKey");
    }
}
