package lectures.part1Basics

object Functions extends App {
    //  functionName(parameter:type): return type = 
    def aFunction(a: String, b: Int): String = // After the equals sign follows a single expression which is the implementation of the function.
        a + " " + b // This is a concatenation function. Implementation can also be in a block.

    def aFunctionAgain(a: String, b: Int): String = {
        a + " " + b
    } // function with code block

    println(aFunction("hello", 3))

    // parameterless functions can be called with just their name.
    def aParameterlessFunction(): Int = 42
    println(aParameterlessFunction())
    //println(aParameterlessFunction) -- This would work in Scala 2 but in Scala 3 you must include () if the function has them

    // How to do loops
    def aRepeatedFunction(aString: String, n: Int): String = {
        if (n==1) aString
        else aString + aRepeatedFunction(aString, n-1)
    } //This is a recursive function

    println(aRepeatedFunction("hello", 3))

    // In a normal programming language, you would use loops, but in Scala, USE RECURSIVE FUNCTIONS
    // Functional programmers use expressive code by using recursion instead of loops

    // The compiler can infer the return type of a function, so it is okay not to specify it. 
    // The compiler figures out the return type by looking at the implentation.
    // BUT, a recursive function NEEDS the return type. The compiler cannot figure that out on its own.

    def aFunctionWithSideEffects(aString: String): Unit = println(aString)
    // We want side effects when we are doing something that has nothing to do with computation itself.

    // Code blocks allow you to define auxillary functions inside
    def aBigFunction(n: Int): Int = {
        def aSmallerFunction(a: Int, b: Int): Int = a + b

        aSmallerFunction(n, n-1)
    }

    // Exercises
    // I. Write a greeting function that takes two parameters, a name and age, and says my name is name and I am how many years old.

    def aGreetingFunction(name: String, age: Int): String = {
        "Hello, my name is " + name + " and I am " + age + " years old!" 
    }

    // II. Factorial function which computes the product of all numbers up to a given number. This will be recursive.

    def factorialFunction(n: Int): Int = {
        if (n<=1) 1
        else n * factorialFunction(n-1)
    }

    // III. Fibonacci function. A stream of numbers that goes 0, 1, 1, 2, 3, 5, 8, on and on.
    // Specifically: f(1) = 1, f(2) = 1, f(n) = f(n-1)+f(n-2). Implement the nth Fibonacci number.

    def fibonacciFunction(n: Int): Int = {
        if (n<=2) 1
        else fibonacciFunction(n-1) + fibonacciFunction(n-2)
    }

    /// IV. A function that tests if a number is prime.

    def isPrime(n: Int): Boolean = {
        def isPrimeUntil(t: Int): Boolean =
            if (t<=1) true
            else n % t != 0 && isPrimeUntil(t-1)

        isPrimeUntil(n/2)
    }
}