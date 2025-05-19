package lectures.part3fp

object MapFlatmapFilterFor extends App {

    val list = List(1, 2, 3)
    println(list)
    println(list.head)
    println(list.tail)

    // map
    println(list.map(_ + 1))
    // println(list.map(_ + " is a number"))

    // filter
    println(list.filter(_ % 2 == 0))

    // flatMap
    val toPair = (x: Int) => List(x, x + 1)
    println(list.flatMap(toPair))

    // Print out all combinations between two lists
    val numbers = List(1, 2, 3, 4)
    val chars = List('a', 'b', 'c', 'd')
    val colors = List("black", "white")

    val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
    // map and flatMap work kind of like loops. They can iterate over a list.
    println(combinations)

    // Two loops, use a map and a flatMap. For three loops, use flatMap, flatMap, then map
    val combinations2 = numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
    println(combinations2)
    // This is how we "iterate"

    // foreach
    list.foreach(println)

    // These chains are not very readable, so we use for-comprehensions
    val forCombinations = for {
        n <- numbers
        c <- chars
        color <- colors
    } yield "" + c + n + "-" + color // This is compiled to the exact same thing as combinations2 but it is much more readable.

    val forCombinations2 = for {
        n <- numbers if n % 2 == 0 // This is called a guard, which filters out the numbers. Equivalent to "val combinations2 = numbers.filter(_ % 2 == 0).flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))" which is quite unwiedly, as is the length of this comment, which really demonstrates the point being made here.
        c <- chars
        color <- colors
    } yield "" + c + n + "-" + color
    println(forCombinations2)

    for {
        n <- numbers
    } println(n) // equivalent to numbers.foreach(println)

    // Syntax overload
    list.map { x =>
        x * 2
    }

    /* 
        Exercises:
            1. See if MyList supports for comprehensions.
                - map(f: A => B) => MyList[B]
                - filter(p: A => Boolean) => MyList[A]
                - flatMap(f: A => MyList[B]) => MyList[B]
            2. Implement a small collection of at most one element, call it Maybe[+T]. Only possible subtypes are an empty collection or an element with one element type T contained.
                - map, flatMap, filter
    */


}