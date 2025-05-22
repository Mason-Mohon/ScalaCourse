package lectures.part4pm

object PatternsEverywhere extends App {

    // Big idea #1
    try {
        // Code
    } catch {
        case e: RuntimeException => "runtime"
        case npe: NullPointerException => "npe"
        case _ => "Something else"
    }
    // Catches are the same as pattern matches.

    // Big idea #2
    val list = List(1, 2, 3, 4)
    val evenOnes = for {
        x <- list if x % 2 == 0
    } yield 10 * x
    // All generators are based on pattern matching.

    val tuples = List((1,2),(3,4))
    val filterTuples = for {
        (first, second) <- tuples // Decomposed in the same way as pattern matching
    } yield first * second
    
    // Big idea #3
    val tuple = (1, 2, 3)
    val (a, b, c) = tuple // Can assign multiple values
    println(b)
    // Multiple value definitions based on pattern matching.
    // All the power is available

    val head :: tail = list
    println(head)
    println(tail)

    // Big idea #4
    // Patrial function
    val mappedList = list.map {
        case v if v % 2 == 0 => s"$v is even"
        case 1 => "the one"
        case _ => "something else"
    } // PARTIAL FUNCTION LITERAL

    // This is equivalent to:
    val mappedList2 = list.map { x => x match {
            case v if v % 2 == 0 => s"$v is even"
            case 1 => "the one"
            case _ => "something else"
        }
    }

    println(mappedList)
}