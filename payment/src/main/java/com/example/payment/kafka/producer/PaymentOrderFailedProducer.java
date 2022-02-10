package com.example.payment.kafka.producer;

import com.example.payment.events.PaymentOrderFailedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentOrderFailedProducer {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentOrderFailedProducer.class);

	@Autowired
	private KafkaTemplate<String, PaymentOrderFailedEvent> paymentOrderTemplate;

	@Value("${custom.dlt.topic}")
	private String DLT_TOPIC;

	public void send(PaymentOrderFailedEvent event) {
		LOG.info("Sending message, topic:'{}'  event:'{}'", DLT_TOPIC, event);
		paymentOrderTemplate.send(DLT_TOPIC, event);
	}

}
