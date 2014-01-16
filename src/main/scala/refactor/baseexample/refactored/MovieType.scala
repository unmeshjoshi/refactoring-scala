package refactor.baseexample.refactored

trait MovieType {
  def charge(daysRented: Int): Double
  val frequentRentalPoints: Int = 1
}

abstract class MoviesWithPenalty(amount: Double, penaltyFreeDays: Int) extends MovieType {
  def charge(daysRented: Int): Double = {
    def lateFee = (daysRented - penaltyFreeDays) * amount
    if (daysRented > penaltyFreeDays) amount + lateFee else amount
  }
}

object NewRelease extends MovieType {
  def charge(daysRented: Int) = daysRented * 3
  override val frequentRentalPoints = 2
}

object ChildrenMovie extends MoviesWithPenalty(amount = 1.5, penaltyFreeDays = 3)
object RegularMovie extends MoviesWithPenalty(amount = 2.0, penaltyFreeDays = 2)
