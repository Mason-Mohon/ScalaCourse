package lectures.part2oop

object Exceptions extends App {

    // This has to do with the JVM and with preventing crashes!

    val x: String = null
    // println(x.length) ... this will crash the program with a null pointer exception

    // Goal is to throw and catch exceptions

    // 1. Throw exceptions
    // throw new NullPointerException ... This line will just straight up crash the program. This is an expression!
    // val aWeirdValue: String = throw new NullPointerException // This is type nothing so the weird value can be any type.
    // NullPointerException is a class, new instantiates it. It derives from the throwable type class.

    // throwable classes extend the Throwable class.
    // Exception and Error are the major Throwable subtypes

    // 2. How to catch exceptions
    def getInt(withExceptions: Boolean): Int = 
        if (withExceptions) throw new RuntimeException("No int for you!")
        else 42

    val potentialFail = try { // Value of try block is "AnyVal"
        // code that might throw
        getInt (false)
    } catch {
        // try to match against all exceptions that you might have
        case e: RuntimeException => println("caught a Runtime exception")
    } finally {
        // Code that will get executed no matter what
        // This block is optional
        // Does not influence return type of try block expression. Only use for side effects, such as logging.
        println("finally")
    }

    // Exceptions are from Java language and are JVM specific.
    println(potentialFail)

    // 3. How to define your own exceptions

    class MyException extends Exception
    val exception = new MyException

    // throw exception ... crashes program with a MyException
    // Very rare that you will need your own exceptions

    /* 
    Exercises:
        1. Crash program with OutOfMemoryError
        2. Crash program with a SOError
        3. Create PocketCalculator class that:
            - add(x, y) Int
            - multiply(x, y) Int
            - divide(x, y)
            - multiply(x, y)

            Make them throw custom exceptions:
                - OverfloewExecption if add(x, y) exceeds Int.MAX_VALUE
                - UnderfrlowException if subtract(x, y) exceeds Int.MIN_VALUE
                - MathCalculationException for division by 0
     */

    // 1. Out of memory error
    // val array = Array.ofDim[Int](Int.MaxValue)

    // 2. SOError
    // def stackOverflower(x: Int): Int = {
    //     x + stackOverflower(x + stackOverflower(x + stackOverflower(x * 1500)))
    // }

    class OverflowException extends RuntimeException

    class UnderflowException extends RuntimeException

    class MathCalculationException extends RuntimeException

    object PocketCalculator {

        def add(x: Int, y: Int): Int = {
            val result = x + y
            if (x > 0 && y > 0 && result < 0) throw new OverflowException
            else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
            else result
        }

        def subtract(x: Int, y: Int): Int = {
            val result = x - y
            if (x > 0 && y < 0 && result < 0) throw new OverflowException
            else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
            else result
        }

        def multiply(x: Int, y: Int): Int = {
            val result = x * y
            if (x > 0 && y > 0 && result < 0) throw new OverflowException
            else if (x < 0 && y < 0 && result < 0) throw new OverflowException
            else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
            else if (x > 0 && y > 0 && result > 0) throw new UnderflowException
            else result
        }

        def divide(x: Int, y: Int): Int = {
            if (y == 0) throw new MathCalculationException
            else x / y
        }
    }
}