package de.rieckpil.blog.kt

import java.time.LocalDateTime
import java.util.*

class OrderService {

  fun createOrder(productName: String, amount: Long, parentOrderId: String?) =
    Order(
      id = parentOrderId ?: UUID.randomUUID().toString(),
      creationDateTime = LocalDateTime.now(),
      amount = amount,
      productName = productName
    )
}

data class Order(
  val productName: String,
  val amount: Long,
  val id: String,
  val creationDateTime: LocalDateTime
)
