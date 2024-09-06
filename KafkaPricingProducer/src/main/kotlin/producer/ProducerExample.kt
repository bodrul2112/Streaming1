package producer;

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*


import java.util.Properties

fun main(args: Array<String>) {
    val props = Properties().apply {
        put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
    }

    val producer = KafkaProducer<String, String>(props)

    val topic = "my-topic"
    val message = "Hello, Kafka!"

    val record = ProducerRecord<String, String>(topic, message)
    producer.send(record) { metadata, exception ->
        if (exception != null) {
            println("Error sending message: ${exception.message}")
        } else {
            println("Message sent to topic ${metadata.topic()} partition ${metadata.partition()} offset ${metadata.offset()}")
        }
    }

    producer.close()
}