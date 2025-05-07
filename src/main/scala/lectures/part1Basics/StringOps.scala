package lectures.part1Basics

object StringOps extends App {

    val str: String = "Hello, I am learning Scala"

    println(str.charAt(2)) // This will print the character at index 2 of string (third character)
    println(str.substring(7, 11)) // Slicing those indexes
    println(str.split(" ").toList) // This will split everything split by a space into its own string in a list
    println(str.startsWith("Hello")) // Returns a bool
    println(str.replace(" ", "-"))
    println(str.toLowerCase())
    println(str.length)
    // All of the above are also available in Java

    // These are scala features below:
    val aNumberString = "2"
    val aNumber = aNumberString.toInt // Convers the string to an integer
    println('a' +: aNumberString :+ 'z') // The :+ prepends and the :+ appends. These are Scala-specific operators.
    println(str.reverse)
    println(str.take(2)) // Prints the first two characters.

    // Scala-specific: String interpolators.

    // S-interpolators
    val name = "David"
    val age = 20
    val greeting = s"Hello, my name is $name and I am $age years old." // The s tells the compiler to fill in the $ words with the corresponding values.
    val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old." 
    // The braces allow us to put other code into the s-interpolated string.
    println(anotherGreeting)

    // F-interpolators. Can also expand values and complex expressions like s-interpolators, but can also expand print f formats.
    val speed = 1.2f
    val myth = f"$name can east $speed%2.2f burgers per minute." // The %2.2f means two characters minimum with two characters of decimal precision.
    println(myth) // Prints speed as 1.20, since f-interpolator asked for two decimals of precision.

    val x = 1.1f // This will be a float
    //val string = f"$x%3d" -- %3d expects an integer, which forces the compiler to evaluate the type of x to ensure the types match. If they do not match, it will issue an error.

    // Raw-interpolator. Can print characters literally.
    println(raw"This is a \n newline but printed literally! Backslashes are not escaped in the raw interpolator.")
    val escaped = "This is a \n newline but printed literally!"
    println(raw"$escaped") // Injected variables/values DO get escaped, so this will have a new line.
}