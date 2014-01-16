package refactor.baseexample.refactored

class Customer(name: String, rentals:Seq[Rental]) {
  def withRental(rental: Rental) = new Customer(name, this.rentals :+ rental)

  def totalCharge = rentals.map(_.charge).sum
  def totalFrequentFlyerPoints = rentals.map(_.frequentRentalPoints).sum

  def rentalStatement(rental: Rental) = rental.movie.title + "\t" + rental.charge
  def rentalsStatement = rentals.map(rentalStatement).mkString("\n")

  def statement =
    s"""
      |Rental record for $name
      |$rentalsStatement
      |Amount owed is $totalCharge
      |You earned $totalFrequentFlyerPoints frequent renter points
    """.stripMargin
}
