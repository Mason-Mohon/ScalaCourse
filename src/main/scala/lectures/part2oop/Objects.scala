package lectures.part2oop

/*
    - Objects and classes can share a name in the same file and scope. This separates instance level functionality, in the case of classes, from static/class level functionality, in the case of objects.
    - A class and object that are in the same scope with the same name are known as companions. This means we can write everything to exist within a class and object.
    - class Person and object Person are companions.
    - Companion designs means that the whole code that we will write will reside in an instance or a singleton object.
    - All the code we are ever going to access will be accessed from some kind of instance, whether it is a singleton instance or regular instance, making Scala a truly object-oriented language.
*/ 

object Objects extends App {

    // Scala does not have class-levle functionality ("static").
    // It has something even better: Objects

    object Person {
        val N_EYES = 2
        def canFly: Boolean = false

        def apply(mother: Person, father: Person): Person = new Person("Bobby") // This method is called a factory method, because it creates new instances. Usually assigned to apply.
    }

    class Person(val name: String) {

    }

    // This is how Scala defines class level functionality
    println(Person.N_EYES)
    // Objects can be defined in a similar way to classes, but they do not receive parameters.
    println(Person.canFly)

    // Scala objects are singleton instances. Meaning: when an object is defined, its type and only instance are defined.
    val Mary = Person
    val John = Person
    println(Mary == John) // Returns true, because they both point to the same instance, an object called person. Singleton instances by definition without any other code needed.

    val Stacy = new Person("Stacy")
    val Jeff = new Person("Jeff")
    println(Stacy == Jeff) // Will return false, because these names were both instantiated as part of class Person, rather than just assigned to object Person

    val Bobby = Person(Stacy, Jeff)

    // Scala Application is a Scala object with a very important method:
        // EXACTLY THE FOLLOWING: def main(args: Array[String]): Unit
    // The method has that exact definition because Scala applications are turned into JVM applications with entry-point public static void main. Unit is static.
    // That is what the extends App at the top does, it extends that main method within another file


}