package br.com.jjorgeparrilla.pedidos.api.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	// Variavel de acesso
	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;
	
	// Cria uma exchange do tipo Fanout
	@Bean
	public Exchange pedidosExchange() {
		return new FanoutExchange(exchangeName);
	}
	
	// Faz a conexão com RabbitMQ
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}
	
	// Conerte Java em Json
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	// Responsável por enviar a mensagem
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}
	
	//Faz a conexão e cria a mensagem
	@Bean
	public ApplicationListener<ApplicationReadyEvent> applicationReadlyEventApplicationListener(RabbitAdmin rabbitAdmin) {
//		return new ApplicationListener<ApplicationReadyEvent>() {
//			
//			@Override
//			public void onApplicationEvent(ApplicationReadyEvent event) {
//				rabbitAdmin.initialize();
//				
//			}
//		};
		
//		O código simplificado com lambada
		return event -> rabbitAdmin.initialize();
	}

}
