package refactor

object ExtractSurroundingMethod2a {

  case class Person(name: String, dob: String, dod: Option[String], mother: Option[Person]) {
    def isAlive = dod.isEmpty
    def isOrphan = mother.isEmpty
  }

  case class FamilyTree(person: Person, childTrees: Seq[FamilyTree]) {
    def progeny: Seq[Person] = person +: childTrees.flatMap(_.progeny)
    def liveProgenyCount = progeny.count(_.isAlive)
    def countProgenyWithName(name: String) = progeny.count(_.name == name)
  }

  case class PeopleStore(people: Seq[Person]) {

    val peopleByMother = people
      .filterNot(_.isOrphan)
      .groupBy(_.mother.get)

    def familyTreeOf(person: Person): FamilyTree = {
      val childTrees = getChildrenOf(person).map(familyTreeOf)
      FamilyTree(person, childTrees)
    }

    private def getChildrenOf(mother: Person) = peopleByMother.getOrElse(mother, Seq())
  }

}
