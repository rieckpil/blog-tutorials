import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.html.*
import kotlinx.html.dom.create
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.random.Random

fun main() {
  window.onload = {
    var table = document.getElementById("tableBody")

    MainScope().launch {
      fetchUsers().forEach {
        var tr = document.create.tr {
          th {
            scope = ThScope.row
            +"${it.id}"
          }
          td {
            classes = setOf("text-center", "mark")
            +"${it.username}"
          }
          td { +"${it.name}" }
          td { +"${it.email}" }
          td { +"${it.website}" }
          td { +"${it.phone}" }
        }
        table?.appendChild(tr)
      }
    }
    drawCanvas()
  }
}

private fun drawCanvas() {
  val canvas = document.getElementById("myCanvas") as HTMLCanvasElement
  val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
  with(ctx) {
    repeat(30) {
      beginPath()
      fillStyle = listOf("red", "green", "orange", "blue").random()
      rect(randomCoordinate(), randomCoordinate(), 20.0, 20.0)
      fill()
      closePath()
    }
  }
}

private fun randomCoordinate() = Random.nextDouble(0.0, 200.0)

suspend fun fetchUsers() = window.fetch("https://jsonplaceholder.typicode.com/users")
  .await()
  .json()
  .await()
  .unsafeCast<Array<User>>()

data class User(
  val id: String,
  val name: String,
  val username: String,
  val email: String,
  val phone: String,
  val website: String,
  val company: Company,
  val address: Address
)

data class Address(
  val street: String,
  val suite: String,
  val city: String,
  val zipcode: String,
  val geo: GeoCode
)

data class GeoCode(
  val lat: String,
  val lng: String
)

data class Company(
  val name: String,
  val catchPhrase: String,
  val bs: String
)
