package lectures.part2oop

object OOBasics extends App {

    val person = new Person("John", 26) // Instantiates a class
    println(person.age) // NOT person.age. Age is a class parameter, not a class member that can be accessed with '.'
    // Class parameters are NOT fields, unless you add the val or var keyword before it. 
    // If val was not in the class parameters, the compiler would be angry!
    println(person.x) // Prints "4" as well, because every expression and side effect within the class will be evaluated.

    person.greet("Daniel") // This prints Daniel in both places, but we want one of the name parameters to be the name parameter from the class.
    person.greetAgain("Daniel")
}

// Class definitions can sit on the top level code.
// A class organizes data and behavior.
// An instantiation is concrete realizations in memory that conforms to the data structure that the class describes.
class Person(name: String, val age: Int = 0) {// This is a constructor, which means that every instance of this class needs these value parameters passed into it
    // This is the body of the class.
    val x = 2

    println(1 + 3)
    // Can do anything inside of this codeblock that you can do inside a block expression.

    // Methods. Because these functions are defined inside of a class definition, they are called methods.
    def greet(name: String): Unit = println(s"$name says: Hi $name.")
    def greetAgain(name: String): Unit = println(s"${this.name} says: Hi $name.")

    def greet(): Unit = println(s"Hi, I am $name") // Using this.name is unecessary because it assumes the class parameter of name since there is not method parameter called name.
    /* 
    Note: this method has the same name as above, but because they have different parameters, the compiler is able to figure out which is which.
    The compiler WILL complain if there are two methods with the same parameters and different signatures.
    This is called overloading, when two methods have the same name and parameters but different things they do.
    */

    // In Scala, we can provide multiple constructors.
    def this(name: String) = this(name, 0) // Auxillary constructor. 
    // The implementation of an auxillary constructor has to be another constructor and nothing else.
    def this() = this("John Doe") // These are not very useful, only for default parameters. But we can just describe the default parameter from the get-go.

    val counter = new Counter
    counter.inc.print
}

/* 
Exercise: Implement a Novel and Writer Class

The Writer should have a first name, surname, and birth year.
    - A method called full name which returns the concatenation of first name and surname.

The novel should have a name and a year of release, as well as an author, which is an instance of type writer. Should have methods:
    - Author age, returns age of author at year of release.
    - isWrittenBy (an author)
    - Copy, which receives a new year of release and returns a new instance of novel with a new year of release.
*/

class Writer(firstName: String, surname: String, val birthYear: Int) {

    def fullName: String = s"$firstName $surname"
}

class Novel(title: String, releaseYear: Int, author: Writer) {

    def authorAge = releaseYear - author.birthYear

    def isWrittenBy(author: Writer) = author == this.author

    def copy(newYear: Int): Novel = new Novel(title, newYear, author)
}

/*  
    Counter class
        - receives an int value
        - method current count
        - method to increment/decrement => new Counter
        - overload inc/dec to receive an amount
*/

class Counter(val num: Int = 1) {

    def currentCount = num

    def inc = {
        println("incrementing")
        new Counter(num + 1) // Instances are immutable so this much create a new instance
    }
    def dec = {
        println("decrementing")
        new Counter(num - 1)
    }

    def inc(n: Int): Counter = {
        if (n <= 0) this
        else inc.inc(n+1)
    }

    def dec(n: Int): Counter = {
        if (n <= 0) this
        else dec.dec(n-1)
    }

    def print = println(currentCount)
}