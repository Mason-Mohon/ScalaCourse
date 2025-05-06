package lectures.part1Basics

object CBNvsCBV extends App {

    def calledByValue(x: Long): Unit = {
        println("By value: " + x)
        println("By value: " + x) // Nano Time values are exactly the same
    }

    def calledByName(x: => Long): Unit = { // The arrow tells the compiler that the paramter will be called by name
        println("By name: " + x)
        println("By name: " + x) // Nano Time values are different on these too calls.
    }

    calledByValue(System.nanoTime()) 
    // The exact value of the expression is computed before the function evaluates, and the same value is used in the function definition.
    // So it gets the nanotime, and then it puts it into the function.

    calledByName(System.nanoTime())
    // For by name calls, the expression is passed literally as is, and the expression is evaluated every time.
    // So the arrow delays the evaluation of the expression passed as a parameter, and it is used literally every single time.

    def infinite(): Int = 1 + infinite()
    def printFirst(x: Int, y: => Int) = println(x)
    // printFirst(infinite(), 34)   --Crash with stack overflow error of course

    printFirst(34, infinite())
    // This does not lead to a stack overflow, because it delays the evaluation of the expression until it is used.
    // Because the infinite function is never used in the function, it is never evaluated, thanks to the by name call.
    // I assume that if it was called by value, however, despite it not being used in the actual function, it would still cause a crash.

    /* 
    In conclusion:

    Called by value:
        - value is computed before the call
        - same value is used everywhere

    Called by name: (=>)
        - expression is passed literally
        - expression is evalyated at every use within the function definition.
    */
}