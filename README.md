## Kafka Commands (DOCKER COMPOSE)

- Calcular tamanho da partição: https://eventsizer.io/partitions

### Create a topic
```bash
docker compose exec broker \
kafka-topics --create \
--topic $TOPIC_NAME \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 1
```

### Delete topic
```bash
docker compose exec broker \
kafka-topics --delete \
--topic $TOPIC_NAME \
--bootstrap-server localhost:9092 \
```

###topics:
payment-order \
payment-order-retry-1 \
payment-order-retry-2 \
payment-order-retry-3 \
payment-order-retry-4 \
payment-done \
payment-failed


### Payment order event:
```json
{
    "id": 1,
    "reference": "c595c243-f280-4416-9594-653faea4d667",
    "price": "10,99",
    "couponCode": "XPTO123"
}
```

### Payment order failed:
```json
{
    "id": 1,
    "reference": "c595c243-f280-4416-9594-653faea4d667",
    "price": "10,99",
    "couponCode": "XPTO123"
}
```

### Payment order successful:
```json
{
    "id": 1,
    "reference": "c595c243-f280-4416-9594-653faea4d667",
    "price": "10,99",
    "couponCode": "XPTO123",
    "paidAt": "01-07-2022 10:31:10"
}
```

