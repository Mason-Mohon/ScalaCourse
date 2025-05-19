package lectures.part3fp

object AnonymousFunctions extends App {

    val doubler = new Function1[Int, Int] { // This is the object-oriented way of making the function
        override def apply(x: Int) = x * 2
    }

    val doublerAnon = (x: Int) => x * 2 // This does the exact same thing. It instantiates a Function1 and overrides an apply.
    // This is called an anonymous function/lambda function.
    val doubleAnon2: Int => Int = x => x * 2 // The compiler understands this to be the same too, and it is even shorter.

    // Multiple parameters in a lambda
    val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

    // No parameters in a lambda
    val justDoSomething: () => Int = () => 3 // Just returns the value of 3.

    println(justDoSomething) // Prints: "lectures.part3fp.AnonymousFunctions$$$Lambda$4/0x0000000131026f90@34033bd0" - this prints the function itself
    println(justDoSomething()) // Prints: 3 - this calls the function
    // Methods can be called without the parenthesis, but lambdas need parenthesis.

    // Curly braces with lambdas
    val stringToInt = { (str:String) =>
        str.toInt
    } // This is a common style

    // More syntactic sugar

    val niceIncrementer: Int => Int = _ + 1 // Equivalent to x => x + 1. Only one Int parameter so it will go in the underscore spot.
    val niceAdder: (Int, Int) => Int = _ + _ // Equivalent to (a, b) => a + b.
    // Underscore notation is extremely contextual.

    /* 
    Exercises:
        1. Replace all FunctionX calls with lambdas in MyList
        2. Rewrite the special adder from the previous lesson as an anonymous function.
    */

    val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
        override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
            override def apply(y: Int): Int = x + y
        }
    }

    val superAdd = (x: Int) => (y: Int) => x + y // Lambda version of the above curried function.

}