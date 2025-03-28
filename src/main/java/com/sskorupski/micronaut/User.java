package com.sskorupski.micronaut;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
import lombok.NoArgsConstructor;

@Data
@MappedEntity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {

    @Id
    @AutoPopulated
    private UUID userId;

    @Nullable
    @Relation(value = Relation.Kind.ONE_TO_ONE)
    private MyEmbedded embedded;
}