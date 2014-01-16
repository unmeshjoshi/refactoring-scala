package refactor

class IntroduceExplainingVariable(quantity: Int, itemPrice: Double) {
  object Original {
    def price(): Double = {
    //  price is base price - quantity discount + shipping
    return (quantity * itemPrice) - (Array(0, quantity - 500).max * itemPrice * 0.05) + (Array(quantity * itemPrice * 0.1, 100.0).min)
    }
  }

  object Refactored {
    def price(): Double = {
      //  price is base price - quantity discount + shipping
      val basePrice = quantity * itemPrice
      val quantityDiscount = Array(0, quantity - 500).max * itemPrice * 0.05
      val shipping = Array(basePrice * 0.1, 100.0).min

      basePrice - quantityDiscount + shipping
    }
  }
  
  object RefactoredWithExtractMethod {
    def priceRefactored = basePrice - quantityDiscount + shipping

    private def shipping = Array(basePrice * 0.1, 100.0).min
    private def quantityDiscount = Array(0, quantity - 500).max * itemPrice * 0.05
    private def basePrice = quantity * itemPrice
  }
}
