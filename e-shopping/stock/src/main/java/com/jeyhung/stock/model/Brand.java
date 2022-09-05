package com.jeyhung.stock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "brands")
public class Brand {
    @Transient
    public static final String SEQUENCE_NAME = "brands_sequence";

    @Id
    private long id;

    @Field(name = "name")
    @Indexed(unique = true)
    private String name;

    @Field(name = "creation_at")
    private LocalDateTime creationAt = LocalDateTime.now();
}
