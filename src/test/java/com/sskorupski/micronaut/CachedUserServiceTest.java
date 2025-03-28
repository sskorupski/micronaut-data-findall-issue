package com.sskorupski.micronaut;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@MicronautTest
class CachedUserServiceTest {
  @InjectMocks
  @Inject CachedUserService cachedUserService;
  @Inject UserService userService;

  @MockBean(UserService.class)
  UserService userService() {
    return Mockito.mock(UserService.class);
  }

  @BeforeEach
  void setUp() throws InterruptedException {
    when(userService.findById(Mockito.any())).thenReturn(new User());
  }

  private UUID testedUUID = UUID.randomUUID();

  @Test
  @org.junit.jupiter.api.Order(1)
  public void firstInvocation_shouldCallCache() throws InterruptedException {
    var user = cachedUserService.findById(testedUUID);
    assert user != null;
    verify(userService, Mockito.times(1)).findById(testedUUID);
  }


  @Test
  @Order(2)
  public void secondInvocation_shouldNotCallCache() throws InterruptedException {
    var user = cachedUserService.findById(testedUUID);
    assert user != null;
    verify(userService, Mockito.times(0)).findById(testedUUID);
  }

}