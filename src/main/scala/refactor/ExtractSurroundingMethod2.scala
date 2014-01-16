package refactor

class ExtractSurroundingMethod2 {

  class Person(private val name: String, mother: Option[Person], dob: String, private val dod: Option[String]) {

    private var children: Seq[Person] = Seq()

    mother.foreach(_.addChild(this))

    def addChild(child: Person) = children :+= child

    def numberOfLivingDescendants = children.flatMap(_.children).count(_.dod.isDefined)
    def numberOfDescendantsNamed(name: String) = children.flatMap(_.children).count(_.name == name)

  }

  class RefactoredPerson(private val name: String, mother: Option[RefactoredPerson], dob: String, private val dod: Option[String]) {

    private var children: Seq[RefactoredPerson] = Seq()

    def progeny: Seq[RefactoredPerson] = children.flatMap(_.progeny)

    mother.foreach(_.addChild(this))

    def addChild(child: RefactoredPerson) = children :+= child

    def numberOfLivingDescendants = count(_.dod.isDefined)
    def numberOfDescendantsNamed(name: String) = count(_.name == name)

    def count(f: RefactoredPerson => Boolean) = progeny.count(f)
  }
}
