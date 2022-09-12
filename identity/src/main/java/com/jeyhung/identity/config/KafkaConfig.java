package com.jeyhung.identity.config;

import com.jeyhung.identity.model.User;
import com.jeyhung.identity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
@Slf4j
public class KafkaConfig {
    private final UserService userService;

    public KafkaConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public NewTopic userTopic() {
        return TopicBuilder.name("users")
                .partitions(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name("payments")
                .partitions(1)
                .compact()
                .build();
    }

    @Bean
    public KStream<Long, User> stream(StreamsBuilder builder) {
        JsonSerde<User> userSerde = new JsonSerde<>(User.class);
        KStream<Long, User> stream = builder.stream("payments", Consumed.with(Serdes.Long(), userSerde));
        stream.peek((k, o) -> log.info("Output: {}")).to("users");
        return stream;
    }

    @Bean
    public KTable<Long, User> table(StreamsBuilder builder) {
        KeyValueBytesStoreSupplier store = Stores.persistentKeyValueStore("users");
        JsonSerde<User> userSerde = new JsonSerde<>(User.class);
        KStream<Long, User> stream = builder.stream("users", Consumed.with(Serdes.Long(), userSerde));
        return stream.toTable(Materialized.<Long, User>as(store)
                .withKeySerde(Serdes.Long())
                .withValueSerde(userSerde));
    }
}
