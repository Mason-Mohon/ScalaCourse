package lectures.part2oop

object AnonymousClasses extends App {

    abstract class Animal {
        def eat: Unit
    }

    val funnyAnimal: Animal = new Animal {
        override def eat: Unit = {
            println("hahahah funny")
        }
    }
    // What the compiler does here is equivalent to:
/*
    class AnonymousClasses$$anon$1 extends Animal {
        override def eat: Unit = {
            println("hahahah funny")
        }
    }
    val funnyAnimal: Animal = new AnonymousClasses$$anon$1
*/

    class Person(name: String) {
        def sayHi: Unit = println(s"Hi, my name is $name.")
    }

    val jim = new Person("Jim") {
        override def sayHi: Unit = println("Hi, my name is Jim. Nice to meet you!")
    }
    // Anonymous classes work for abstract and non-abstract data types.

    /*  
    Add to myList:
        1. Generic trait MyPredicate[-T] with a test(T) method => Boolean
        2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
        3. Create:
            - a map function that takes the transformer and creates a new list
            - filter(predicate) => myList
            - flatMap(transformer from A to MyList[B]) => MyList[B]

            class EvenPredicate extends MyPredicate[Int]
            class StringToIntTransformer extends MyTransformer[String, Int]

            They should work as followers:
                [1,2,3].map(n*2) => [2,4,6]
                [1,2,3,4].filter(n%2=0) => [2,4]
                [1,2,3].flatMap(n=> [n, n+1]) => [1,2,2,3,3,4]
    */
}