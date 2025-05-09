package lectures.part2oop

object InheritanceAndTraits extends App {

    // Scala has single class inheritance:
    class Animal {
        def eat = println("omnomnom")
        private def eatMore = println("chomp chomp") // cat.eatMore would not work because private.
        protected def eatAgain = println("mmmm yum")
        val creatureType = "Wild"
    }

    class Cat extends Animal {// You can only extend one class at a time.
        def crunch = {
            eatAgain // protected modifier allows the use of eat in the sub-class, but not outside of the sub-class.
            println("crunch crunch")
        }
    }
    val cat = new Cat
    cat.eat
    // Cat is a sub-class of animal, and animal is a super-class of cat.
    // Sub-class only inherits non-private members of the subperclass.
    // Private, protected, and public are access modifiers for methods.

    // Constructors
    class Person(name: String, age: Int) {
        def this(name: String) = this(name, 0)
    }
    // class Adult(name: String, age: Int, idCard: String) extends Person -- This will not compile because the JVM needs to call the constructor from the parent class.
    // Compiler forces you to guarantee superconstructed call when using a derived class. Therefore, child class must pass in correct arguments.
    class Adult(name: String, age: Int, idCard: String) extends Person(name, age) // Arguments are passed so it works

    // Overriding
    class Dog(override val creatureType: String) extends Animal { // values can also be overwritten in the constructor.
        override def eatAgain = {
            super.eatAgain // This will call the version of the method from the parent class.
            println("crunch crunch") // Overriding works for methods.
        }
        // override val creatureType: String = "Domestic" This overrides the creature type in the original animal class
    }
    // class Dog(dogType: String) extends Animal {
    //     override val creatureType: String = dogType
    // }
    val dog = new Dog("Domestic") // Overrider works right here.
    dog.eatAgain

    // Type subsitution. In a broad sense, this is called polymorphism.
    val unknownAnimal: Animal = new Dog("K9")
    unknownAnimal.eat // A method call will always go to the most overridden version whenever possible.

    // overRIDING vs. overLOADING.
    // Overriding means supplying a different implementation in derived classes. 
    // Overloading is providing multiple methods with different signatures, but the same name in the same class.

    // Preventing overrides.
    // 1 - Use the keyword "final" - prevents derived classes from overriding the method. Ex: "final def ..."
    // 2 - Use final on the class itself. Ex: "final class ..." Numerical classes, string types, are final.
    // 3 - Seal the class. This lets you extend classes in the same file only, but prevents extension in other files. This is used for being exhaustive in type hierarchies. Ex: "sealed class ..."
}