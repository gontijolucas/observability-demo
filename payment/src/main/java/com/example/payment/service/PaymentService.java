package com.example.payment.service;

import com.example.payment.events.PaymentOrderEvent;
import com.example.payment.events.PaymentOrderFailedEvent;
import com.example.payment.events.PaymentOrderSuccessfulEvent;
import com.example.payment.integration.PaymentClientAPI;
import com.example.payment.kafka.producer.PaymentOrderFailedProducer;
import com.example.payment.kafka.producer.PaymentOrderSuccessfulProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

	private final PaymentClientAPI paymentAPI;
	private final PaymentOrderSuccessfulProducer successfulProducer;
	private final PaymentOrderFailedProducer failedProducer;

	public PaymentService(PaymentClientAPI paymentAPI,
						  PaymentOrderSuccessfulProducer successfulProducer,
						  PaymentOrderFailedProducer failedProducer) {
		this.paymentAPI = paymentAPI;
		this.successfulProducer = successfulProducer;
		this.failedProducer = failedProducer;
	}

	public void pay(PaymentOrderEvent event) {
//		try {
			this.paymentAPI.pay(event);
			this.successfulProducer.send(parseToSuccessfulEvent(event));
//		} catch (Exception e) {
//			this.failedProducer.send(parseToFailedEvent(event));
//		}
	}

	private PaymentOrderFailedEvent parseToFailedEvent(PaymentOrderEvent event) {
		return new PaymentOrderFailedEvent(
				event.id(),
				event.reference(),
				event.price(),
				event.couponCode()
		);
	}

	private PaymentOrderSuccessfulEvent parseToSuccessfulEvent(PaymentOrderEvent event) {

		return new PaymentOrderSuccessfulEvent(
				event.id(),
				event.reference(),
				event.price(),
				event.couponCode(),
				LocalDateTime.now()
		);
	}
}
