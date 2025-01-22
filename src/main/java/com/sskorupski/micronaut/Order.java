package com.sskorupski.micronaut;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.Data;

import java.util.UUID;

@Data
@MappedEntity
public class Order {

    @Id
    @AutoPopulated
    private UUID orderId;

    @Nullable
    @Relation(value = Relation.Kind.ONE_TO_ONE)
    private MyEmbedded embedded;
}