package refactor

object IntroduceNamedParameters {

  case class SearchCriteria(authorId: String, publisherId: String, isbn: String)

  object BooksQuery {
    def build(selector: String, conditions: Option[String] = None, joins: Seq[String] = Seq()): String = {
      var sql = "SELECT * FROM books "
      joins.foreach({
        joinTable =>
          sql += s"LEFT OUTER JOIN $joinTable ON "
          sql += s"books.$joinTable" + "_id "
          sql += s" = $joinTable.id "
      })
      if (conditions.isDefined) sql += s" WHERE $conditions"
      if (selector == "first") sql += " LIMIT 1"

      sql
    }
  }

}
