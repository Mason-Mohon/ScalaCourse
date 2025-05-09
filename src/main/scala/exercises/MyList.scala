package exercises
/* 
    List will be a singly linked list which holds integers with the following methods:
        1. Head - first element of the list
        2. Tail - remainder of the list (pointer)
        3. isEmpty - returns a Boolean to tell if the list is empty.
        4. add - receives an integer, returns a new list with the element added.
        5. toString - returns a string representation of the list.
*/

abstract class MyList {

    def head: Int
    def tail: MyList
    def isEmpty: Boolean
    def add(element: Int): MyList
    def printElements: String
    // Polymorphic call
    override def toString: String = "["+printElements+"]"
}

object Empty extends MyList {
    def head: Int = throw new NoSuchElementException // Throw exceptions return Nothing.
    def tail: MyList = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add(element: Int): MyList = new Cons(element, Empty)
    def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
    def head: Int = h
    def tail: MyList = t
    def isEmpty: Boolean = false
    def add(element: Int): MyList = new Cons(element, this)
    def printElements: String = 
        if(t.isEmpty) "" + h
        else h + " " + t.printElements
}

object ListTest extends App {
    val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
    println(list.tail.head)
    println(list.add(4).head)
    println(list.isEmpty)
    println(list.toString)
}