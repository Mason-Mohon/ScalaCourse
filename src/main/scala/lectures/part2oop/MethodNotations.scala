package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

    class Person(val name: String, favoriteMovie: String, val age: Int) {

        def likes(movie: String): Boolean = movie == favoriteMovie

        def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
        def +(nickname: String): Person = new Person(s"${name} ($nickname)", favoriteMovie, age)
        
        def unary_! : String  = s"$name, what the heck?"
        def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

        def isAlive: Boolean = true

        def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
        def apply(times: Int): String = s"$name watched $favoriteMovie $times times."

        def learns(skill: String): String = s"$name learns $skill"
        def learnsScala: String = this.learns("Scala")
    }

    val Mary = new Person("Mary","Inception", 25)
    println(Mary.likes("Inception")) // This will evaluate to true
    println(Mary likes "Inception") // This is equivalent to above
    // This is called infix notation or operator notation, and it only works when a method has a single parameter.
    // Infix notation is a type of syntactic sugar. Syntactic sugar is a nicer way or writing code that would be cumbersome otherwise.

    // "Operators" in Scala
    val Tom = new Person("Tom", "Fight Club", 25)
    println(Mary + Tom) // This acts like an operator between Mary and Tom. Works between two things and yields a third thing.
    // Reminiscent of an operator in math. Scala has an extremely permissive method naming scheme, and it can even be a plus sign.
    println(Mary.+(Tom)) // This is the same thing.

    // Plus operators between numbers is an operator as well that acts in the exact same way.
    println(1 + 2)
    // Is the same as
    println(1.+(2))
    // ALL OPERATORS ARE METHODS
    // Akka actors have methods like ! or ?

    // Prefix notation - all about unary operators.
    val x = -1 // The negative sign is a unary operator, and is also a method!
    val y = 1.unary_- // This is the same thing!
    // unary_ prefix only works with a few operators: - + ! ~

    println(!Mary)
    println(Mary.unary_!)

    // Postfix notation - functions that do not receive any parameters can be used with postfix notation
    println(Mary.isAlive)
    println(Mary isAlive) // Would not work without a package, I guess the tutorial had it pre-installed?

    // Apply
    println(Mary.apply())
    println(Mary()) // This is equivalent because of the apply method in the class. 
    // If an instance is called like a function it looks for the apply method and just uses that.
    // THIS is what breaks the barrier between object-oriented programming and functional programming. This apply method will be used throughout the course.

    /*
        Exercises: 
            1. Overload the + operator in the Person class to receive a string for a nickname.

            2. Add an age to the person class, and add a unary + operator which increments the age and returns a new Person

            3. Add a "learns" method in the person class, which receives a string and returns a sentence which says "Mary learns Scala" or some other skill.
               Then, add a learnsScala method, which does not receive any parameters but calls the learns method with Scala as a parameter. Use it in postfix notation.

            4. Overload the apply method. mary.apply(2) => "Mary watched Inception 2 times"
    */

    println(Mary + "The Winner") // 1.
    println((+Mary).age) // 2.
    println(Tom learns "Python")
    println(Tom learnsScala) // 3.
    println(Mary(2))
}