package de.rieckpil.blog.kt

import de.rieckpil.blog.staticsmocks.OrderService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class OrderServiceKtTest {

  private val cut = OrderService()
  private val defaultUuid = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac")

  @Test
  fun `should include random order id when no parent order exists`() {
    Mockito.mockStatic(UUID::class.java).use { mockedUuid ->
      mockedUuid.`when`<Any> { UUID.randomUUID() }.thenReturn(defaultUuid)
      val result = cut.createOrder("MacBook Pro", 2L, null)
      assertEquals("8d8b30e3-de52-4f1c-a71c-9905a8043dac", result.id)
    }
  }
}
