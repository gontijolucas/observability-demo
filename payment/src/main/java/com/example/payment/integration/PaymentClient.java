package com.example.payment.integration;

import com.example.payment.events.PaymentOrderEvent;
import org.springframework.stereotype.Component;

@Component
class PaymentClient implements PaymentClientAPI {

	private final PaymentAPIFeignClient paymentAPI;

	public PaymentClient(PaymentAPIFeignClient paymentAPI) {
		this.paymentAPI = paymentAPI;
	}

	@Override
	public void pay(PaymentOrderEvent event) {
		paymentAPI.pay(event.id());
	}

	public void pay(Integer id) {
		paymentAPI.pay(id);
	}
}
