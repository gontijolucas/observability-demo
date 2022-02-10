package com.example.payment.events;

import java.time.LocalDateTime;

public record PaymentOrderSuccessfulEvent(Integer id,
										  String reference,
										  Double price,
										  String couponCode,
										  LocalDateTime paidAt) {
}
