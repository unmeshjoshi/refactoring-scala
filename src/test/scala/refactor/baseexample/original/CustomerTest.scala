package refactor.baseexample.original

import org.scalatest.FlatSpec

class CustomerTest extends FlatSpec {

  it should "construct customer statement" in {
    val customer = new Customer("Robert")
    customer.addRental(new Rental(new Movie("Harry Potter", "REGULAR"), 2))
    customer.addRental(new Rental(new Movie("Pirates Of the Carebian", "NEW_RELEASE"), 1))
    customer.addRental(new Rental(new Movie("RIO", "CHILDREN"), 1))

    val statement: String = customer.statement()
    assert(statement.contains("Amount owed is 6.5"))
  }

}
