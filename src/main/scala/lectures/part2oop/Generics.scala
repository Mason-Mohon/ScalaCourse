package lectures.part2oop

import lectures.part2oop.InheritanceAndTraits.Animal
import javax.smartcardio.Card

object Generics extends App {

    class MyList[+A] {// Type A denotes a generic type
        // Can use the type A inside the class
        // def add(element: A): MyList[A] = ??? ... 
        // This gives this error: "covariant type A occurs in contravariant position in type A of parameter element"
        // This error is the same question as: "Can you do animalList.add(new Dog)?"
        def add[B >: A](element: B): MyList[B] = ???
        /* 
            A = Cat
            B = Dog = Animal, because B is a supertype of A, which means B is not necessarily dog but actually animals generally, I think.
         */
    }
    // This can also work for traits.
    val listOfIntegers = new MyList[Int] // In the instantiation of the class you can set the type parameter
    val listOfStrings = new MyList[String]
    class MyMap[Key, Value] // Can have multiple type parameters.

    // Generic methods

    object MyList { // Objects cannot be type parameterized.
        def empty[A]: MyList[A] = ??? // Can define generic types in methods within objects.
    }

    val emptyListOfIntegers = MyList.empty[Int] // Uses Int for the empty method in the MyList object.

    // Variance problem
    class Animal
    class Cat extends Animal
    class Dog extends Animal

    //If cat extends animal, does List[Cat] extend List[Animal]? 
    // Option 1 - Yes, List[Cat] extends List[Animal]. This behavior is called covariance.
    class CovariantList[+A]
    val animal: Animal = new Cat
    val animalList: CovariantList[Animal] = new CovariantList[Cat]
    // Can you do animalList.add(new Dog)?
    // Going from list of animals, which works, becasue cats are a type of animal.
    // If you add a dog to the list of cats, it will become a list of general animals.

    // Option 2 - No, List[Cat] does NOT extend List[Animal]. This behavior is called invariance.
    class InvariantList[A]
    val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // Cannot be cat in this case.
    // Going from animal to cat does not work invariant wise because there IS variation between the cats and animals.

    // Option 3 - Hell no! Contravariance
    class ContravariantList[-A]
    val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]
    // Contravariance - this works because you are going from cats to all animals, which is exactly the opposite as in covariance.

    class Trainer[-A]
    val trainer: Trainer[Cat] = new Trainer[Animal] // Someone who can train animals can train cats. So you can use a new trainer of animals to be a trainer of cat.

    // Bounded types. Using generic classes only for types that are a subclass or superclass of a different type

    class Cage[A <: Animal] (animal: A)// This restricts type parameters A which are sub-types of animal.
    val cage = new Cage(new Dog) // This expression evaluates to an animal which makes it acceptable for the cage.
    // Upper bounded type. Lower bounded type (>:) means it must be a supertype of whatever type bounding.

    class Car
    //val newCage = new Cage(new Car)... This does not wwork because a car is not an animal and cannot fit into a cage.

    // Exercise: expand MyList to be generic:
    
    
}