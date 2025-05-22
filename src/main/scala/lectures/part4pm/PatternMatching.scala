package lectures.part4pm

import scala.util.Random
import lectures.part2oop.Generics.Animal
import scala.quoted.Expr

object PatternMatching extends App {

    // Switch on steroids
    val random = new Random
    val x = random.nextInt(10)

    // This is pattern matching which matches a value against multiple patterns. Each pattern is written in a case statement.
    val description = x match {
        case 1 => "the one"
        case 2 => "double of one"
        case 3 => "three!"
        case _ => "default case wild card"
    }

    println(x)
    println(description)

    // Use cases for pattern matching:
    
    // Use Case 1. Decompose values
    case class Person(name: String, age: Int)
    val bob = Person("Bob", 20)

    val greeting = bob match {
        case Person(n, a) if a < 21 => s"Hi, my name is $n and I am cannot drink in the US." // If statement works as a guard.
        case Person(n, a) => s"Hi, my name is $n and I am $a years old." // Deconstructs Bob into his constituent parts
        case _ => "I do not know who I am."
    }
    println(greeting)

    /* 
        1. Cases are matched in order
        2. If no cases match, you will get a scala.MatchError with the value that did not match the pattern. 
           That is why wild cards are important.
        3. The type of the pattern match expression is the unification of all of the return types of all of the cases. 
           Will return the lowest common ancestor of all types returned by all cases.
        4. Pattern matching works extremely well with case classes.
    */

    // Use Case 2. PM on sealed hierarchies
    sealed class Animal
    case class Dog(breed: String) extends Animal
    case class Parrot(greeting: String) extends Animal

    val animal: Animal = Dog("Newfoundland")
    animal match {
        // Because animal is a sealed class, the compiler gives you warning if you have not exhausted all possible cases.
        case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
    }

    // Match everything
    val isEven = x match {
        case n if n % 2 == 0 => true
        case _ => false
    } // This is overkill.
    val isEvenCond = if (x % 2 == 0) true else false // Also overkill
    val useThis = x % 2 == 0 // Does the same thing as above. Just because PM exists and so do other features, be simple.

    /* 
        Exercise
        Simple function that uses pattern matching
        takes the expression and returns the readable form
    */

    trait Expr
    case class Number(n: Int) extends Expr
    case class Sum(e1: Expr, e2: Expr) extends Expr
    case class Prod(e1: Expr, e2: Expr) extends Expr

    val n1: Expr = Number(1)
    val n2: Expr = Number(2)
    val n3: Expr = Number(3)
    val n4: Expr = Number(4)
    val sumExpression: Expr = Sum(n1, n3)
    val prodExpression: Expr = Prod(n2, n4)

    val readable = sumExpression match {
        case Sum(Number(i1), Number(i2)) => println(s"$i1 + $i2")
        case Sum(Sum(Number(i1), Number(i2)), Number(i3)) => println(s"$i1 + $i2 + $i3")
        case Prod(Sum(Number(i1), Number(i2)), Number(i3)) => println(s"($i1 + $i2) * $i3")
        case Sum(Prod(Number(i1), Number(i2)), Number(i3)) => println(s"$i1 * $i2 + $i3")
        case _ => println("Invalid Computation")
    }

    def show(e: Expr): String = e match {
        case Number(n) => s"$n"
        case Sum(e1, e2) => show(e1) + " + " + show(e2)
        case Prod(e1, e2) => {
            def maybeShowParentheses(exp: Expr) = exp match {
                case Prod(_, _) => show(exp)
                case Number(_) => show(exp)
                case _ => "(" + show(exp) + ")"
            }

            maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
        }
    }

    println(show(Sum(Number(2), Number(3))))
    println(show(Sum(Sum(Number(4), Number(3)), Number(1))))
    println(show(Prod(Sum(Number(3), Number(2)), Number(9))))
    println(show(Sum(Prod(Number(1), Number(4)), Number(5))))
}