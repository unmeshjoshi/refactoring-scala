package refactor

import org.scalatest.FlatSpec

class ExtractSurroundingMethodTest extends FlatSpec {
  it should "connect to server" in {
    new ExtractSurroundingMethod().Original.charge(2.0, "2828282")
  }

  it should "connect to server in refactored method" in {
    new ExtractSurroundingMethod().Refactored.charge(2.0, "2828282")
  }
}
