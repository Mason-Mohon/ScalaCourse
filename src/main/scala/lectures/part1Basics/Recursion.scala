package lectures.part1Basics

import scala.annotation.tailrec

object Recusion extends App {

    def factorial(n: Int): Int = {
        if (n<=1) 1
        else {
            println("Computing factorial of " + n + " - I first need the factorial of " + (n - 1))
            val result = n * factorial(n-1)
            println("Computed factorial of " + n)
            result
        }
    } 
    // This is a recursive function.
    // In order to run a recursive function, the JVM uses a call stack to store the results while it runs the function.
    // Print statements added to function to show how it works recursively.

    println(factorial(10))
    // When it finds the base case it frees the call stack of intermediate results and works all the way back up.
    // Problem is that the JVM keeps all the calls on its internal stack where it has limited memory.
    // Running println(factorial(5000)) will cause a stack overflow error because the recursive depth is too big!

    // So, if we are not going to use loops, and recursion has limitations, what are we going to do??

    def anotherFactorial(n: Int): BigInt = {
        @tailrec // This tells the compiler it is supposed to be tail recursive. If it is not tail recursive, then it will give you an error.
        def factorialHelper(x: Int, accumulator: BigInt): BigInt =
            if (x <= 1) accumulator
            else factorialHelper(x - 1, x * accumulator) // Tail recursive.

        factorialHelper(n, 1)
    }
    //factorialHelper has the actual factorial logic within it.
    println("another factorial function: " + anotherFactorial(10)) // But it prints 0 because it overflows the integer representation, so we have to use BigInt

    // So why does this not crash??
    // The trick is that the helper is the last expression of its codepath, allowing Scala to preserve the same stackframe and not use additional stackframes for recursive calls.
    // In the previous implementation, Scala needed a new stackframe for each recursive call.
    // Stackhelper keeps all recursive calls within the same stackframe.
    // This is called: TAIL RECURSION - key ingredient is to use each recursive call of the last expression.

    // WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION.

    /* 
        Exercises for tail recursion:
        I. Concatenate a string n times
        II. Prime function tail recursive
        III. Fibonacci tail recursive
    */

    // I.

    @tailrec
    def concatenateTailRec(aString: String, n: Int, accumulator: String): String = 
        if (n<=0) accumulator
        else concatenateTailRec(aString, n-1, aString + accumulator)
    
    println(concatenateTailRec("hello",3,""))

    // II.

    def isPrime(n: Int): Boolean = {
        def isPrineTailRec(t: Int, isStillPrime: Boolean): Boolean = 
            if (!isStillPrime) false
            else if (t<=1) true
            else isPrineTailRec(t-1,n%t != 0 && isStillPrime)
        
        isPrineTailRec(n / 2, true)
    }

    // III.

    def fibonacci(n: Int): Int = {
        def fiboTailRec(i: Int, last: Int, nextToLast: Int): Int = //This one has two accumulators
            if (i>=n) last
            else fiboTailRec(i+1, last+nextToLast, last)

        if (n<=2) 1
        else fiboTailRec(2, 1, 1)
    }
}