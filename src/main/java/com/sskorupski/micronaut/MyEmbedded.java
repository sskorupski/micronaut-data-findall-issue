package com.sskorupski.micronaut;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

import java.util.UUID;

@Data
@MappedEntity
public class MyEmbedded {

    @Id
    @AutoPopulated
    private UUID id;

    private String someProp;
}