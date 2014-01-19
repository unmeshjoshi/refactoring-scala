package refactor

import scala.util.control.NonFatal

object ExtractSurroundingMethod {

  object Original {
    def charge(amount: Double, creditCardNumber: String) = {
      val connection: Conn = new Conn {}
      try {
        connection.send(amount, creditCardNumber)
      } catch {
        case NonFatal(ex) => println(s"Could not submit amount $amount to the server: ${ex.getMessage}")
      } finally {
        connection.close()
      }
    }
  }

  object Refactored {
    def charge(amount: Double, creditCardNumber: String) = SurroundingMethod.connect { connection =>
      connection.send(amount, creditCardNumber)
    }
  }
}

object SurroundingMethod {
  def connect[T](f: Conn => T): T = {
    val connection: Conn = new Conn {}
    try {
      f(connection)
    } catch {
      case NonFatal(ex) => println(s"Could not submit to the server: ${ex.getMessage}"); throw ex
    } finally {
      connection.close()
    }
  }
}

trait Conn {
  def send(amount: Double, creditCardNumber: String) = println("sending")
  def close() = println("closing")
}
