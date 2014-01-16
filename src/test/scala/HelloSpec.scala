import org.scalatest._
import org.scalatest.matchers.ShouldMatchers

class HelloSpec extends FlatSpec with ShouldMatchers {
  "Hello" should "have tests" in {
    val multiplier = (i: Int) => i * 2
    print(multiplier(2));
  }


}
