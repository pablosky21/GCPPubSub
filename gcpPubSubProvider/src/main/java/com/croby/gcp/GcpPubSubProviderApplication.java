package com.croby.gcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;

@SpringBootApplication
public class GcpPubSubProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpPubSubProviderApplication.class, args);
	}
	
	@Bean
	@ServiceActivator(inputChannel = "pubsubOutputChannel")
	public MessageHandler messageSender(PubSubTemplate pubSubTemplate) {
	    return new PubSubMessageHandler(pubSubTemplate, "PubSubDemo");
	    
	}
	// Create Messaging Gateway interface to publish the message
    @MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
    public interface PubsubOutboundGateway {
        void sendToPubsub(String text);
    }

}
