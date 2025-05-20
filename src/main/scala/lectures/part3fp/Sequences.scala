package lectures.part3fp

import scala.util.Random

object Sequences extends App {

    // Seq - type def for sequence
    /* 
        Sequences are a very general interface for data structures that have:
            - A well-defined order
            - Can be indexed.
        Sequences support the following OOB:
            - apply, iterator, length, reverse - for indexing and iterating
            - concatenation, appending, prepending,
            - a lot of others: grouping, zorting, zipping, searching, slicing
    */

    val aSequence = Seq(1, 2, 3, 4)
    println(aSequence) // prints a list, because Seq constructs subclasses of the object.
    println(aSequence.reverse) // reverses order
    println(aSequence(2)) // Using .apply() - gets the value at index 2.
    println(aSequence ++ Seq(5, 6, 7)) // appends
    println(aSequence.sorted) // sorts
    
    // Ranges
    val aRange: Seq[Int] = 1 to 10 // Can use "until" in place of "to"
    aRange.foreach(println) // prints everything between one and ten

    (1 to 10).foreach(x => println("Hello")) // uses range to print hello ten times.

    /* 
        Lists - Extend LinearSeq and are immutable. Linked lists:
            - head, tail, isEmpty methods are fast: O(1)
            - Most options are O(n): length, reverse
        Sealed has two subtypes:
            - object Nil (empty)
            - class ::
    */

    val aList = List(1, 2, 3) // Lists by calling apply method from companion object
    val prepended = 42 :: aList // :: is syntactic sugar for applying
    println(prepended)
    val alsoPrepended = 42 +: prepended // another way to prepend
    println(alsoPrepended)
    val appended = alsoPrepended :+ 33
    println(appended)

    val apples5 = List.fill(5)("apple")
    println(apples5)
    // fill is a curried function that takes a number and a value and fills the list with the value that many times.
    println(aList.mkString("-|-")) // Concatenates all the elevements with the parameter.

    /* 
        Arrays - equivalent of simple Java arrays:
            - Can be manually constructed with predefined lengths
            - Can be mutated (updated in place)
            - Are interoperable with Java's T[] arrays
            - Indexing is fast, any element can be found by index in O(1) time.
    */

    // Arrays
    val numbers = Array(1, 2, 3, 4)
    val threeElements = Array.ofDim[Int](3) // Allocates space for three elements without needing to supply values for those elements.
    println(threeElements) // prints: [I@34ce8af7
    threeElements.foreach(println) // prints the default values (0, false, null)

    // Mutation of arrays
    numbers(2) = 0 // syntax sugar for numbers.update(2, 0). Update is a special method similar to apply.
    println(numbers.mkString(" "))

    // Arrays and sequences
    // val numbersSeq: Seq[Int] = numbers 
    // Implicit conversions
    // println(numbersSeq) 
    // Prints ArraySeq, but it is described as a WrappedArray in the course.

    /*  
        Vectors are the default implementation for immutable sequences:
            - Effectively constant indexed read and write: O(log32(n))
            - Fast element addition: append/prepend
            - Implemented as a fixed-branch trie (branch factor 32)
            - Good performance for large sizes.
    */

    val vector: Vector[Int] = Vector(1, 2, 3)
    println(vector)

    // Vectors vs. Lists

    val maxRuns = 1000
    val maxCapacity = 1000000

    
    def getWriteTime(collection: Seq[Int]): Double = {
        val r = new Random
        val times = for {
            it <- 1 to maxRuns 
            // For 1000 times
        } yield {
            val currentTime = System.nanoTime() 
            // We get the current time
            collection.updated(r.nextInt(maxCapacity), r.nextInt) 
            // Update random index to random value
            System.nanoTime() - currentTime 
            // Measure time after update was completed and return the max difference
        }

        times.sum * 1.0 / maxRuns 
        // Gets the average time for updating the sequence
    }

    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    // List is good at updating head and tail, but less efficient in the middle of the list.
    println(getWriteTime(numbersList))
    // Vector benefits from having a small tree, but needs to replace the whole 32-element chunk.
    println(getWriteTime(numbersVector))

    // numbersVector is significantly faster. List averaged 9 milliseconds (9,000,000 nanoseconds), whereas vectors averaged around 4000 nanoseconds
}