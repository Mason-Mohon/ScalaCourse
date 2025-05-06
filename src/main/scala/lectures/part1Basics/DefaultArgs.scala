package lectures.part1Basics

import scala.annotation.tailrec

object DefaultArgs extends App {

    @tailrec
    def trFactorial(n: Int, acc: Int): Int =
        if (n <= 1) acc
        else trFactorial(n - 1, n * acc)

    val fact10 = trFactorial(10, 1)

    /* The accumulator does one of two things:
        - Either the accumulator spoils the function signature
        - Or, we need to wrap it up into a bigger function
    */

    // Scala allows default values
    @tailrec
    def trFactorialDef(n: Int, acc: Int = 1): Int =
        if (n <= 1) acc
        else trFactorialDef(n - 1, n * acc)

    val fact10Def = trFactorialDef(10) // No need to pass the accumulator because there is a default value.

    // Passing default values into parameters brings challenges with it. For example:

    def savePicture(format: String = "jpg", width: Int = 800, height: Int = 600): Unit = println("Saving picture")
    savePicture("jpg", 800, 600) // Assuming most of the photos are in jpg, ideally we could do jpeg as the default first parameter.
    // But this is the first parameter, and it will confuse the compiler!

    /*
    Two solutions:
        1. Pass in every leading argument
        2. Name the arguments
    */
    savePicture(height=1080, width=1920, format="bmp")
    //When we name them, they can be out of order, or we can leave one out, because then the compiler will be able to figure it out.
}