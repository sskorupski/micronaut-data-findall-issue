## Expected behavior
Given a repository repository declare a `Page findAll(PredicateSpecification spec, Pageable pageable);` method
When calling this method with a `Pageable.sort.property` referencing an embedded object property
Then it should return the expected `Page` without throwing exception

## Actual behavior
Working with `micronaut-parent 4.6.3` the method return the expected `Page` without throwing exception
Upgrading to `micronaut-parent 4.7.0` the modhod throws an`java.lang.IllegalStateException: Cannot query entity [Order] on non-existent property`

## Steps to reproduce
Declare a `MappedEntity` with an embedded object property:
```java
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

@Data
@MappedEntity
public class MyEmbedded {

    @Id
    @AutoPopulated
    private UUID id;

    @NotNull
    private String someProp;
}
```

Declare the corresponding repository:
```java
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface OrderRepository extends CrudRepository<Order, UUID> {
    @NonNull
    @JoinSpecifications({
        @Join(value = "embedded", type = Join.Type.LEFT_FETCH)
    })
    Page<Order> findAll(PredicateSpecification<Order> spec, Pageable pageable);

}
```

Test it
```java
@MicronautTest(transactional = false)
public class OrderRepositoryIT {

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
```
