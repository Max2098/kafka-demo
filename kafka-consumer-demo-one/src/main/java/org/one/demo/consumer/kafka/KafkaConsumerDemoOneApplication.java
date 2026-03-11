package org.one.demo.consumer.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConsumerDemoOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerDemoOneApplication.class, args);
    }

    // TODO не реализована атомарность между комитом и записью в бд, когда kafka listener делает offset
}
