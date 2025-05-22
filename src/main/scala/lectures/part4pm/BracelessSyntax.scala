package lectures.part4pm

object BracelessSyntax {

    // If-expressions
    val anIfExpression = if (2 > 3) "bigger" else "smaller"

    // Java-style
    val anIfExpression_v2 =
        if (2 > 3) {
            "bigger"
        } else {
            "smaller"
        }
    
    // Compact style
    val anIfExpression_v3 =
        if (2 < 3) "bigger"
        else "smaller"

    // Above styles work with Scala 2 and 3, below only work with Scala 3

    // Scala 3 style
    val anIfExpression_v4 =
        if 2 > 3 then
            "bigger" // Needs a significant indentation to eliminate curly braces.
        else
            "smaller"
    // Similar to Python syntax.

    val anIfExpression_v5 =
        if 2 > 3 then
            val result = "bigger"
            result
        else
            val result = "smaller"
            result
            // Indented parts are a code block that exist in their own scope, just as if there were curly braces.

    // Scala 3 one-liner
    val anIfExpression_v6 = if 2 > 3 then "bigger" else "smaller"

    // For comprehensions
    val aForComprehension = for {
        n <- List(1,2,3)
        s <- List("black","white")
    } yield s"$n$s"

    // Scala 3 style
    val aForComprehension_v2 =
        for
            n <- List(1,2,3)
            s <- List("black","white")
        yield s"$n$s"

    // Patterning matching
    val meaningOfLife = 42
    val aPatternMatch = meaningOfLife match {
        case 1 => "one"
        case 2 => "two"
        case _ => "something else"
    }

    // Scala 3 pattern matching
    val aPatternMatch_v2 = meaningOfLife match
        case 1 => "one"
        case 2 => "two"
        case _ => "something else"

    // Can also be
    val aPatternMatch_v3 =
        meaningOfLife match
            case 1 => "one"
            case 2 => "two"
            case _ => "something else"

    // It is purely a matter of styling choice but it is best to be consistent.

    // Methods can be defined without braces.

    def computeMeaningOfLife(arg: Int): Int = 
        val partialResult = 40


        // With or without curly braces we can have all this sp

        partialResult + 2
        // The whole indentation region is considered part of the function.
        // But it is better to keep it intact to avoid these errors.

    // Classes can be definted with significant indentation. (Also available for traits, objects, enums, etc.)
    class Animal: // Colon is necessary for the class to indicate to the compiler that there will not be an indentation region.
        def eat(): Unit =
            println("I'm eating")

        def grow(): Unit =
            println("I'm growing")
    
        // The need for indentation arose from the fact that there did not tend to be that much code in classes, enums, etc.
        // So, if there are 3000 more lines of code, we want to use an end token like this:
    end Animal
    // This tells the compiler that everything below is not part of the Animal class.
    // End tokens can be used with anything that uses significant indentation.
    // Good practice: use end tokens whenever there are more than four lines of code on an indentation level.

    // Anonymous classes
    val aSpecialAnimal = new Animal:
        override def eat(): Unit = println("I'm special")

    // Indentation = strictly larger indentation

    def main(args: Array[String]): Unit = {
        println(anIfExpression_v5)
        println(computeMeaningOfLife(999))
    }
}