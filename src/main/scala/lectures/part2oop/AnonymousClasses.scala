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
}