package com.example.payment.kafka.producer;

import com.example.payment.events.PaymentOrderSuccessfulEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentOrderSuccessfulProducer {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentOrderSuccessfulProducer.class);

	@Autowired
	private KafkaTemplate<String, PaymentOrderSuccessfulEvent> paymentOrderTemplate;

	@Value("${custom.producer.successful.topic}")
	private String SUCCESSFUL_TOPIC;

	public void send(PaymentOrderSuccessfulEvent event) {
		LOG.info("Sending message, topic:'{}'  event:'{}'", SUCCESSFUL_TOPIC, event);
		paymentOrderTemplate.send(SUCCESSFUL_TOPIC, event);
	}

}
