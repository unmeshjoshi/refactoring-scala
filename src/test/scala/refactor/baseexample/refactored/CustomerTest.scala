package refactor.baseexample.refactored

import org.scalatest.FlatSpec

class CustomerTest extends FlatSpec {

  it should "construct customer statement" in {
    val customer = new Customer("Robert", Seq.empty)
      .withRental(new Rental(new Movie("Harry Potter", RegularMovie), 2))
      .withRental(new Rental(new Movie("Pirates Of the Carebian", NewRelease), 1))
      .withRental(new Rental(new Movie("RIO", ChildrenMovie), 1))

    val statement: String = customer.statement
    assert(statement.contains("Amount owed is 6.5"))
  }

}
