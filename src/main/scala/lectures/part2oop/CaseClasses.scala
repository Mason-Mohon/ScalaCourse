package lectures.part2oop

object CaseClasses extends App {

    // For lightweight data structures we have to add in all sorts of boiler plate every time.
    // Case Classes are ideal solutions for this problem. They are good for creating defaults for lightweight data classes with minimal hastle.

    case class Person(name: String, age: Int) // Using case does a lot here!

    // 1. Class parameters are promoted to fields
    val jim = new Person("Jim", 34)
    println(jim.name)

    // 2. sensible.toString for easy debugging
    println(jim.toString)
    // Java also has a syntactic sugar that delegates the following with .toString:
    println(jim) // println(instance) == println(instance.toString)

    // 3. equals and hashCode implemented OOTB (out of the box)
    val jim2 = new Person("Jim", 34)
    println(jim == jim2) // Would be false without case class

    // 4. Case classes have handy copy methods
    val jim3 = jim.copy(age = 45)
    println(jim3)

    // 5. Case Classes have companion objects
    val thePerson = Person
    val mary = Person("Mary", 23) // Delegates to apply method. In practice, then, we do not need to say "new" with case classes

    // 6. CCs are serializable

    // 7. CCs have extractor patterns = CCs can be used in pattern matching

    case object UnitedKingdom {
        def name: String = "The UK of GB and NI"
    }

    // Expand MyList to use case classes and case objects
}