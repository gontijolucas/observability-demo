package com.example.payment.kafka.producer;

import com.example.payment.events.PaymentOrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentOrderProducer {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentOrderProducer.class);

	@Autowired
	private KafkaTemplate<String, PaymentOrderEvent> paymentOrderTemplate;

	@Value("${custom.producer.topic}")
	private String TOPIC_NAME;

	public void send(PaymentOrderEvent event) {
		LOG.info("Sending message, topic:'{}'  event:'{}'", TOPIC_NAME, event);
		paymentOrderTemplate.send(TOPIC_NAME, event);
	}

}
