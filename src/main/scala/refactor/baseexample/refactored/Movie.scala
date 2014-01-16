package refactor.baseexample.refactored

case class Movie(title: String, movieType: MovieType) {
  def charge(daysRented: Int) = movieType.charge(daysRented)
  def frequentRentalPoints = movieType.frequentRentalPoints
}


