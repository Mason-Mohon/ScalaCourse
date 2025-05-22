package lectures.part4pm

import exercises.MyList
import exercises.Cons
import exercises.Empty

object AllThePatterns extends App {

    // 1 - Constants
    val x: Any = "Scala"
    val constants = x match {
        case 1 => "a number"
        case "Scala" => "THE Scala"
        case true => "The Truth"
        case AllThePatterns => "A singleton objects"
    }

    // 2 - Match anything
    // 2.1 - Wildcard
    val matchAnything = x match {
        case _ =>
    }

    // 2.2 - Variable
    val matchAVariable = x match {
        case something => s"I've found $something"
    }

    // 3 - Tuples
    val aTuple = (1, 2)
    val matchATuple = aTuple match {
        case (1, 1) =>
        case (something, 2) => s"I have found $something"
        // "something" will try to extract if it matches the rest of the pattern
    }

    val nestedTuple = (1, (2, 3))
    val matchANestedTuple = nestedTuple match {
        case (_, (2, v)) => // Variable will still match even though it is nested
    }

    // 4 - Case classes, constructor pattern
    val aList: MyList[Int] = Cons(1, Cons(2, Empty))
    val matchAList = aList match {
        case Empty => 
        case Cons(head, tail) => // Will bind the variables extracted by a list
        case Cons(head, Cons(subhead, subtail)) =>
    }

    // 5 - List patterns
    val aStandardList = List(1, 2, 3, 42)
    val standardListMatching = aStandardList match {
        case List(1, _, _, _) => // This is an extractor
        case List(1, _*) => // List of arbitrary length
        case 1 :: List(_) => // Infix pattern
        case List(1,2,3) :+ 42 => // Also an infix pattern
    }

    // 6 - Type specifiers
    val unknown: Any = 2
    val unknownMatch = unknown match {
        case list: List[Int] => // Explicit type specifier
        case _ =>
    }

    // 7 - Name binding
    val nameBindingMatch = aList match {
        case nonEmptyList @ Cons(_, _) => // name @ will name a pattern. Makes you use the name in the return expression.
        case Cons(1, rest @ Cons(2, _)) => // Name binding works inside nested patterns
    }

    // 8 - Multi-patterns
    val multipattern = aList match {
        case Empty | Cons(0, _) => // Compound pattern using pipe operator (or). Used to express the same values for multiple patterns
    }

    // 9 - If guards
    val secondElementSpecial = aList match {
        case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
    }

    // Exercise

    val numbers = List(1, 2, 3)
    val numbersMatch = numbers match {
        case listOfStrings: List[String] => "A list of strings"
        case listOfNumbers: List[Int] => "A list of numbers"
        case _ => ""
    }

    // println(numbersMatch) 
    // Prints a list of strings. 
    // It is the JVM's fault for having backwards compatibility, in which generic parameters did not exist in early versions.
    // The JVM erases all generic types after type checking. So, List[String] and List[Int] both end up as List at runtime.
    // This is called type erasure. Editor warns:
        // "the type test for List[String] cannot be checked at runtime because its type arguments can't be determined from List[Int]bloop"
}