package lectures.part3fp

object HOFsCurries extends App {

    val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null // There is a lot going on here
    // Return type is a function that takes an int and returns an int.
    // Super function takes two parameters, an Int and a function and the function has a function in it that takes an Int and returns a Boolean

    // This is an example of a higher order function.
    // map, flatMap, and filter in MyList are all examples of HOFs

    // Function that applies a function n times over a value x
    // nTimes(f, n, x)
    // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))
    // nTimes(f, n, x) = f(f(...f(x))) = ntimes(f, n-1, f(x))
    // Functional programming directly maps to many aspects of abstract mathematics
    def nTimes(f: Int => Int, n: Int, x: Int): Int =
        if (n <= 0) x
        else nTimes(f, n-1, f(x))

    val plusOne = (x: Int) => x + 1
    println(nTimes(plusOne, 10, 1))
    // nTimes(plusOne, 10, 1) -> nTimes(plusOne, 9, 2) -> nTimes(plusOne, 8, 3) -> ... -> nTimes(plusOne, 0, 11)

    // nTimesBetter(f, n) = x => f(f(f...(x)))
    // increment10 = nTimesBetter(plusOne, 10) = x => plusOne(plusOne...(x))
    // val y increment10(1)
    def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = // We save the application of f(x) for later
        if (n <= 0) (x: Int) => x
        else (x: Int) => nTimesBetter(f, n-1)(f(x))

    val plusTen = nTimesBetter(plusOne, 10)
    println(plusTen(1)) // It figures out that x = 1 because f and n are defined in "nTimesBetter(plusOne, 10)" so when the function needs to pass something in for x only the 1 remains.
    // This is also like writing nTimesBetter(plusOne, 10)(1) because the second set of parameters gets passed into the inner function.

    // Curried functions
    val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
    val add3 = superAdder(3) // y => 3 + y
    println(add3(10))
    // Curried functions are helpful for defining helper functions that you want to use later.
    println(superAdder(3)(10))

    // Functions with multiple parameter lists. 
    // This acts like a curried function, where you can put the second parameter list into a function called on the inside.
    def curriedFormatter(c: String)(x: Double): String = c.format(x)

    val standardFormat: (Double => String) = curriedFormatter("%4.2f")
    val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))

    /* 
    Exercises:
        1. Expand MyList:
            - expand foreach method A => Unit, apply a side effect to every element of list
                [1, 2, 3].foreach(x => println(x))

            - sort function ((A, A) => Int) => MyList
                [1, 2, 3].sort((x, y) => y - x) => [3, 2, 1]

            - zipWith (list, (A, A) => B) => MyList[B]
                [1, 2, 3].zipWith([4, 5, 6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]

            - fold(start)(function) => a value. Each element gets functioned onto the start value.
                [1, 2, 3].fold(0)(x + y) = 6

        2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
           fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

        3. compose(f, g) => f(g(x))
           andThen(f, g) => x => g(f(x))
    */

    def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
        x => y => f(x, y)

    def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
        (x, y) => f(x)(y)

    // These are in the documentation library for FunctionX
    def compose[A, B, T](f: A => B, g: T => A): T => B =
        x => f(g(x))

    def andThen[A, B, C](f: A => B, g: B => C): A => C =
        x => g(f(x))

    def superAdder2: (Int => Int => Int) = toCurry(_ + _)
    def add4 = superAdder2(4)
    println(add4(17)) // Turns a non-curried function into a curried function.

    val simpleAdder = fromCurry(superAdder)
    println(simpleAdder(4, 17)) // Turns a curried function into a non curried function.

    val add2 = (x: Int) => x + 2
    val times3 = (x: Int) => x * 3

    val composed = compose(add2, times3)
    val ordered = andThen(add2, times3)
    println(composed(4)) // Gives 14 - because it does 4*3, adds 2 after. This is just like 2 + 4 * 3
    println(ordered(4)) // Gives 18 - performs functions in the order they were passed. This is just like (2 + 4) * 3
}