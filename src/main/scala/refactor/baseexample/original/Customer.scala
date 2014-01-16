package refactor.baseexample.original

import scala.collection.mutable.ListBuffer

class Customer(name: String) {
  val rentals = new ListBuffer[Rental]();

  def addRental(rental: Rental): Unit = {
    this.rentals.+=(rental)
  }

  def statement(): String = {
    var totalAmount = 0.0
    var frequentRentalPoints = 0
    var result:String = "Rental record for " + this.name + "\n"
    for (rental <- rentals) {
      var thisAmount = 0.0
      rental.movie.priceCode match {
        case "REGULAR" => {
          thisAmount = thisAmount + 2
          if (rental.daysRented > 2) {
            thisAmount = thisAmount + ((rental.daysRented - 2) * 1.5);
          }
        }
        case "CHILDREN" => {
          thisAmount = thisAmount + 1.5
          if (rental.daysRented > 3) {
            thisAmount = thisAmount  + ((rental.daysRented - 3) * 1.5);
          }
        }
        case "NEW_RELEASE" => {
          thisAmount = thisAmount + (rental.daysRented * 3)
        }
      }

      frequentRentalPoints = frequentRentalPoints + 1
      if (rental.movie.priceCode == "NEW_RELEASE") {
        frequentRentalPoints = frequentRentalPoints + 1
      }
      result = result + "\t" + rental.movie.title + "\t" + thisAmount + "\n"
      totalAmount = totalAmount + thisAmount
    }
    result = result +  "Amount owed is " + totalAmount + "\n"
    result = result +  "You earned " + frequentRentalPoints  + " frequent renter points"
    return result;
  }

}
