package com.example.payment.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "paymentClient", url = "http://localhost:8882")
interface PaymentAPIFeignClient {

	@PostMapping(path = "/payment/pay/{id}")
	public void pay(@PathVariable Integer id);
}
