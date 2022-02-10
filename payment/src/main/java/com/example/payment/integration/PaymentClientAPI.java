package com.example.payment.integration;

import com.example.payment.events.PaymentOrderEvent;

public interface PaymentClientAPI {

	public void pay(PaymentOrderEvent event);
}
