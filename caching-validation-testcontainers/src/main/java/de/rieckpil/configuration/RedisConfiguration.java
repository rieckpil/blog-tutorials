package de.rieckpil.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class RedisConfiguration {

  private final RedisProperties redisProperties;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    final var host = redisProperties.getHost();
    final var port = redisProperties.getPort();
    final var password = redisProperties.getPassword();

    final var cacheConfiguration = new RedisStandaloneConfiguration(host, port);
    cacheConfiguration.setPassword(password);
    return new LettuceConnectionFactory(cacheConfiguration);
  }

  @Bean
  public CacheManager cacheManager(final RedisConnectionFactory redisConnectionFactory) {
    final var serializer =
        SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
    return RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
        .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(serializer))
        .build();
  }
}
