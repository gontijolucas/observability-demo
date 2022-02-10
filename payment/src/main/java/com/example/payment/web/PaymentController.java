package com.example.payment.web;

import com.example.payment.events.PaymentOrderEvent;
import com.example.payment.kafka.producer.PaymentOrderProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/producer")
public class PaymentController {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentOrderProducer producer;

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public void foo(@RequestParam("amount") Integer amount) {

		if (amount != null) {
			for (int i = 0; i < amount; i++) {
				int id = new Random().ints(0, 9).findFirst().getAsInt();
				produceEvent(id);
			}
		} else {
			int id = new Random().ints(0, 9).findFirst().getAsInt();
			produceEvent(id);
			LOG.info("Producing single event.");
		}
	}

	private void produceEvent(int id) {

		String reference = id + "--" + UUID.randomUUID();
		double price = new Random().doubles(1, 10).findFirst().getAsDouble();
		String coupon = "XPTO-" + id;
		PaymentOrderEvent event = new PaymentOrderEvent(id, reference, price, coupon);

		producer.send(event);
	}


}
