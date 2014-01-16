package refactor

class ReplaceTempWithQuery(quantity:Int, itemPrice:Double) {

  def price:Double = {
    val basePrice = quantity * itemPrice;
    var discountFactor = 0.0
    if (basePrice > 1000) discountFactor = 0.98 else discountFactor = 0.95
    return basePrice * discountFactor
  }


  //refactored methods
  def priceRefactored = basePrice * discountFactor

  def discountFactor = if (basePrice > 1000) 0.98 else 0.95
  def basePrice = quantity * itemPrice
}
