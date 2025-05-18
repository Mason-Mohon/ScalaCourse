package lectures.part3fp

object WhatsAFunction extends App {

    // Dream: use functions as first class elements just like values.
    // Problem: we come from an object-oriented world, meaning that everything is an object, an instance of some kind of class. 
    // This is due to JVM design.
    // The only way to simulate functional programming is to use classes.

    val doubler = new MyFunction[Int, Int] {
        override def apply(element: Int): Int = element * 2
    }

    println(doubler(2)) // We can call this method like it is a function. Even though it is an instance of a class, it can be called like a function.

    // Function types: Function1 to Function22
    // Function1[A, B]
    val stringToIntConverter = new Function1[String, Int] {
        override def apply(string: String): Int = string.toInt
    }

    println(stringToIntConverter("3") + 4)

    // Scala supports function types with up to 22 parameters.
    val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] { //((Int, Int) => Int) is syntactic sugar.
        // This has two parameters and the third Int is the return type.
        override def apply(a: Int, b: Int): Int = a + b
    }

    // Function types Function2[A, B, R] === (A, B) => R
    // All Scala functions are objects. Laying the groundwork for functional programming with the JVM.

    /* 
    Exercises:
        1. Define a function which takes 2 strings and concatenates them.
        2. In MyList, transform MyPredicate and MyTransformer into function types.
        3. Define a function which takes an Int and returns another function which takes an Int and returns an Int:
            - What's the type of this function?
            - How do you implement this?
    */

    val concatenator: ((String, String) => String) = new Function2[String, String, String] {
        override def apply(a: String, b: String): String = a + b
    }

    println(concatenator("Hello ","World"))

    // Higher-order function. Curried function.
    val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
        override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
            override def apply(y: Int): Int = x + y
        }
    }

    val adder3 = superAdder(3) // Implentation is the inside function, so y is 3
    println(adder3(4)) // This provides the x value.

    println(superAdder(3)(4)) // Curried function. Has propert: can be called with multiple parameter lists.
}

class Action {
    def execute(element: Int): String = ??? // Using this as a function would mean we have to instantiate the class.
}

// Can also genericize and abstract away most of the boilerplate to simulate functions.
trait MyFunction[A, B] {
    def apply(element: A): B = ???
}