package org.demo.producer.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerDemoApplication.class, args);
    }

    // TODO как обязательно отправлять сообщение, будут ли retry и как не потерять отправку сообщений (можно сделать в функциональном виде)
    // TODO на продюсере есть enableIdempotence - написать, что делает конкретный параметр, как он работает, по каким признакам принимает решение, нужно ли отправить сообщение снова
    // TODO retry.backoff
    // TODO научиться регулировать batch сообщениями, а что будет, если какое-то число сообщений не набирается
    // TODO avro и protobuf, compression type - сильно ест cpu
    // TODO используется kraft,
    // TODO параметр для размера сообщения - можно было также определить в docker-compose
    // TODO unclean leader election - когда отстает реплика и падает лидер, и тут принимается решение, можно ли давать лидера этой реплике
}
