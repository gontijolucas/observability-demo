#! /bin/bash

function initialize_kafka_topics() {
  declare -a arr=("payment-order"
                  "payment-order-retry-0"
                  "payment-order-retry-1"
                  "payment-order-retry-2"
                  "payment-order-retry-3"
                  "payment-order-dlt"
                  "payment-done")

  for topicName in "${arr[@]}"
  do
    docker exec broker \
    kafka-topics --bootstrap-server broker:9092 \
                 --create \
                 --topic "$topicName" \
                 --replication-factor 1 \
                 --partitions 20
  done
}

function show_kafka_topics() {
  docker exec zookeeper \
  kafka-topics \
    --list \
    --bootstrap-server broker:9092
}

help(){
    echo "NAME"
    echo "    helper for kafka."
    echo ""
    echo "USAGE"
    echo "    ./init-kafka [COMMANDS]"
    echo ""
    echo "COMMANDS"
    echo "    Some of the options require an additional value next to them."
    echo ""
    echo "    init-topics"
    echo "        Create kafka topics. "
    echo "    show-topics"
    echo "        List all topics of kafka. "
    echo ""
}

if [ "$1" = "init-topics" ]; then
    initialize_kafka_topics
elif [ "$1" = "show-topics" ]; then
    show_kafka_topics
else
    help
fi

exit 0