package de.rieckpil.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheUtility {

  private final RedisTemplate<String, Object> redisTemplate;
  private final ObjectMapper objectMapper;

  public void save(
      @NonNull final String key, @NonNull final Object value, @NonNull final Duration timeToLive) {
    redisTemplate.opsForValue().set(key, value);
    log.info("Cached value with key '{}' for {} seconds", key, timeToLive.toSeconds());
  }

  public <T> Optional<T> retrieve(@NonNull final String key, @NonNull final Class<T> targetClass) {
    final var value = Optional.ofNullable(redisTemplate.opsForValue().get(key));
    if (value.isEmpty()) {
      log.info("No cached value found for key '{}'", key);
      return Optional.empty();
    }
    T result = objectMapper.convertValue(value.get(), targetClass);
    log.info("Fetched cached value with key '{}'", key);
    return Optional.of(result);
  }
}
