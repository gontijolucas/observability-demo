package com.example.payment.kafka.consumer;

import com.example.payment.events.PaymentOrderEvent;
import com.example.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class PaymentOrderConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentOrderConsumer.class);

	@Autowired
	private PaymentService paymentService;

	@RetryableTopic(
			attempts = "4",
			backoff = @Backoff(delay = 1000, multiplier = 2.0),
			autoCreateTopics = "false",
			topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
			kafkaTemplate = "paymentOrderKafkaTemplate",
			listenerContainerFactory = "paymentOrderKafkaListenerContainerFactory")
	@KafkaListener(
			id = "payment-request-consumer",
			topics = "payment-order",
			groupId = "payment-order-consumer-group",
			containerFactory = "paymentOrderKafkaListenerContainerFactory",
			concurrency = "20")
	public void consume(PaymentOrderEvent event) {
		LOG.info("Consuming event value:'{}'", event);
		paymentService.pay(event);
	}

//	@DltHandler
//	public void dlt(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//		LOG.info(in + " from " + topic);
//	}

}
