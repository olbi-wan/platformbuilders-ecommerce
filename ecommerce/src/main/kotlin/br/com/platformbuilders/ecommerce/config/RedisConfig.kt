package br.com.platformbuilders.ecommerce.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

/**
 * Configuracao da conexao com o Redis.
 */
@Configuration
class RedisConfig

// Lettuce eh o driver padrao do Spring Data Redis.
@Bean
fun connection() = LettuceConnectionFactory()