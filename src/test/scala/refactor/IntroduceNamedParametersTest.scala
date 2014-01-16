package refactor

import org.scalatest.FlatSpec

class IntroduceNamedParametersTest extends FlatSpec {

  it should "pass named parameters" in {
    val criteria = new IntroduceNamedParameters.SearchCriteria(authorId = "Author", publisherId = "publisherId", isbn = "isbn")
    assert(criteria.authorId == "Author")
    assert(criteria.publisherId == "publisherId")
    assert(criteria.isbn == "isbn")
  }

  it should "pass named parameters to build query" in {
    val sql = IntroduceNamedParameters.BooksQuery.build("first", conditions=Option("authors.name='Jenny James'"), joins=Seq("authors"))
    assert("SELECT * FROM books LEFT OUTER JOIN authors ON books.authors_id  = authors.id  WHERE Some(authors.name='Jenny James') LIMIT 1"
      == sql)
  }

}
