package com.example.payment.events;

public record PaymentOrderEvent(Integer id,
								String reference,
								Double price,
								String couponCode) {
}
