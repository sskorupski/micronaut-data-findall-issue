package com.sskorupski.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.repeatable.JoinSpecifications;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

import java.util.UUID;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface OrderRepository extends CrudRepository<Order, UUID> {
    @NonNull
    @JoinSpecifications({
        @Join(value = "embedded", type = Join.Type.LEFT_FETCH)
    })
    Page<Order> findAll(PredicateSpecification<Order> spec, Pageable pageable);

}