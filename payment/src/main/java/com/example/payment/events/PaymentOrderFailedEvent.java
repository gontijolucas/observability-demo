package com.example.payment.events;

public record PaymentOrderFailedEvent(Integer id,
									  String reference,
									  Double price,
									  String couponCode) {
}
