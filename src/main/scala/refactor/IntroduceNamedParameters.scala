package refactor

/**
 * named parameters are by default supported since Scala 2.8. So no need to pass Hash like in Ruby. #see IntroduceNamedParametersTest.
 */
object IntroduceNamedParameters {

  case class SearchCriteria(authorId: String, publisherId: String, isbn: String)

  object BooksQuery {
    def build1(selector: String, conditions: Option[String] = None, joins: Seq[String] = Seq()) = {
      var sql = Seq("SELECT * FROM books ")
      joins.foreach {
        joinTable =>
          sql :+= s"LEFT OUTER JOIN $joinTable ON "
          sql :+= s"books.$joinTable" + "_id "
          sql :+= s" = $joinTable.id "
      }
      if (conditions.isDefined) sql :+= s" WHERE $conditions"
      if (selector == "first") sql :+= " LIMIT 1"

      sql.mkString
    }

    def build(selector: String, conditions: Option[String] = None, joins: Seq[String] = Seq()) = {

      val baseQuery = "SELECT * FROM books "

      val joinsStr = joins.map { joinTable =>
        s"LEFT OUTER JOIN $joinTable ON books.${joinTable}_id  = $joinTable.id "
      }.mkString(" ")

      val conditionsStr = conditions.map(x => s" WHERE $x").getOrElse("")
      val selectorStr = if(selector == "first") " LIMIT 1" else ""

      baseQuery + joinsStr + conditionsStr + selectorStr
    }
  }
}
