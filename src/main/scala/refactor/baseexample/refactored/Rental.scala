package refactor.baseexample.refactored

case class Rental(movie: Movie, daysRented: Int) {
  def charge = movie.charge(daysRented)
  def frequentRentalPoints = movie.frequentRentalPoints
}
