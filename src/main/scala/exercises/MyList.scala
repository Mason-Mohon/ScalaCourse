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

    // These are now higher-order functions, which either receive functions as parameters or return functions as results.
    // This uses functions as first class values.
    def map[B](transformer: A => B): MyList[B]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]

    def ++[B >: A](list: MyList[B]): MyList[B]
}
// Nothing type is a proper substitute for any type. Empty should be a proper substitute for a list of any type.
case object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException // Throw exceptions return Nothing.
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
    def printElements: String = ""

    def map[B](transformer: Nothing => B): MyList[B] = Empty
    def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
    def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new Cons(element, this)
    def printElements: String = 
        if(t.isEmpty) "" + h
        else h.toString + " " + t.printElements

    /* 
        [1,2,3].filter(n % 2 == 0) = 
        [2,3].filter(n % 2 == 0) = 
        = new Cons(2, [3].filter(n % 2 == 0))
        = new Cons(2, Empty.filter(n % 2 == 0))
        = new Cons(2, Empty)
    */
    def filter(predicate: A => Boolean): MyList[A] =
        if (predicate(h)) new Cons(h, t.filter(predicate))
        else t.filter(predicate)

    /* 
        [1,2,3].map(n * 2) - the actual syntax is a new transformer.
            = new Cons(2, [2,3].map(n * 2))
            = new Cons(2, new Cons (4, [3].map(n * 2)))
            = new Cons(2, new Cons(4, new Cons (6, Empty.map(n * 2))))
            = new Cons(2, new Cons(4, new Cons (6, Empty)))
    */
    def map[B](transformer: A => B): MyList[B] = 
        new Cons(transformer(h), t.map(transformer))

    /*
        [1,2] ++ [3,4,5]
        = new Cons(1, [2] ++ [3,4,5])
        = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
        = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
    */
    def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

    /* 
        [1,2].flatMap(n => [n, n+1])
        = [1,2] ++ [2].flatMap(n => [n, n+1])
        = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
        = [1,2] ++ [2,3] ++ Empty)
        = [1,2,2,3]
    */
    def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
        transformer(h) ++ t.flatMap(transformer)
    }
}

// trait MyPredicate[-T] { // Function type T => Boolean
//     def test(elem: T): Boolean
// }

// trait MyTransformer[-A, B] { // A => B
//     def transform(elem: A): B
// }

// class EvenPredicate extends MyPredicate[-T] {

// }

// class StringToIntTransformer extends MyTransformer[A, B] {

// }

object ListTest extends App {
    val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
    val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
    val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, Empty)))
    val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

    println(listOfIntegers.toString)
    println(listOfStrings.toString)
    // val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
    // println(list.tail.head)
    // println(list.add(4).head)
    // println(list.isEmpty)
    // println(list.toString)

    println(listOfIntegers.map(_ * 2).toString)

    println(listOfIntegers.filter(_ % 2 == 0).toString)

    println((listOfIntegers ++ anotherListOfIntegers).toString)

    println(listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))).toString)

    println(listOfIntegers == cloneListOfIntegers) // Evaluates to true because of case classes
}