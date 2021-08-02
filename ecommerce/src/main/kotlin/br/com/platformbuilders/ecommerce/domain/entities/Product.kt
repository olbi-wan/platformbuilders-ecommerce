package br.com.platformbuilders.ecommerce.domain.entities

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.math.BigDecimal

@RedisHash
data class Product (@Id var sku: String?, val name: String, val price: BigDecimal)