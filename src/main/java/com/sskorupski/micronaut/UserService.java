package com.sskorupski.micronaut;

import jakarta.inject.Singleton;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Singleton
public class UserService {

  public User findById(UUID uuid) throws InterruptedException {
    TimeUnit.MILLISECONDS.sleep(200L);
    return new User(uuid, null);
  }
}
