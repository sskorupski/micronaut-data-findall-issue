package com.sskorupski.micronaut;

import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest(transactional = false)
public class OrderRepositoryTest {

    @Inject
    OrderRepository orderRepository;

   @Test
    void findAll_withPageableSort_andSearch() {
        // GIVEN 
       Sort.Order.Direction sortDirection = Sort.Order.Direction.ASC;
        Pageable pageable = Pageable.UNPAGED.order(new Sort.Order("embedded.someProp", sortDirection, false));
        PredicateSpecification<Order> predicate = null;
        // WHEN
        orderRepository.findAll(predicate, pageable);
       // THEN no exception is thrown
    }
}