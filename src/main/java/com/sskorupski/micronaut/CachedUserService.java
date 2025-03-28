package com.sskorupski.micronaut;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.UUID;

@Singleton
@CacheConfig(cacheNames = {"users"})
public class CachedUserService {

  @Inject UserService userService;

  @Cacheable
  public User findById(UUID uuid) throws InterruptedException {
    return userService.findById(uuid);
  }
}
