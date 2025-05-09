package lectures.part2oop

object AbstractDataTypes extends App {

    // Abstract members - classes which contain unimplemented or abstract fields or methods are called abstract classes.

    abstract class Animal {
        val creatureType: String
        def eat: Unit
    }

    // val animal = new Animal ... This will not work because abstract class

    class Dog extends Animal{
        override val creatureType: String = "Canine" // Override is optional to write since there is no prior implementation.
        override def eat: Unit = println("crunch crunch") // The extension class MUST provide implementation for abstract values and methods from parent class
    }

    // Traits
    trait Carnivore {
        def eat(animal: Animal): Unit
        val preferredMeal: String = "Meat"
    }

    trait ColdBlooded
    // Traits can be inherited along classes.
    class Crocodile extends Animal with Carnivore with ColdBlooded {
        val creatureType: String = "Croc"
        def eat: Unit = println("nomnom")
        def eat(animal: Animal): Unit = println(s"I am a croc and I am eating ${animal.creatureType}")
    }

    val dog = new Dog
    val croc = new Crocodile
    croc.eat(dog)

    /* 
    Traits vs. abstract classes.
        - Abstract classes can have both abstract and non-abstract members.
        - So can traits.
    The differences are:
        1 - Traits do not have constructor parameters. (In Scala 3 they can have them.)
        2 - A class can only extend one class, but it can inheret multiple traits.
        3 - Traits are for a behavior, classes are for a thing.
    */
}