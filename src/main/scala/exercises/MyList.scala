package exercises
/* 
    List will be a singly linked list which holds integers with the following methods:
        1. Head - first element of the list
        2. Tail - remainder of the list (pointer)
        3. isEmpty - returns a Boolean to tell if the list is empty.
        4. add - receives an integer, returns a new list with the element added.
        5. toString - returns a string representation of the list.
*/

abstract class MyList[+A] {

    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]
    def printElements: String
    // Polymorphic call
    override def toString: String = "["+printElements+"]"
}
// Nothing type is a proper substitute for any type. Empty should be a proper substitute for a list of any type.
object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException // Throw exceptions return Nothing.
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
    def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new Cons(element, this)
    def printElements: String = 
        if(t.isEmpty) "" + h
        else h.toString + " " + t.printElements
}

object ListTest extends App {
    val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
    val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

    println(listOfIntegers.toString)
    println(listOfStrings.toString)
    // val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
    // println(list.tail.head)
    // println(list.add(4).head)
    // println(list.isEmpty)
    // println(list.toString)
}