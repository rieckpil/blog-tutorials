package de.rieckpil.configuration;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfiguration {

  @Bean
  public RedisConnectionFactory redisConnectionFactory(final RedisProperties redisProperties) {
    final var standaloneConfiguration =
        new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
    standaloneConfiguration.setPassword(redisProperties.getPassword());
    return new LettuceConnectionFactory(standaloneConfiguration);
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(
      final RedisConnectionFactory redisConnectionFactory) {
    final var redisTemplate = new RedisTemplate<String, Object>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
    return redisTemplate;
  }
}
