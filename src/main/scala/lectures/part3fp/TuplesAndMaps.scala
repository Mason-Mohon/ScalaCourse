package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {

    // Tuples = finite ordered "lists"
    val aTuple = new Tuple2(2, "hello, Scala") // Type is Tuple2 = [Int, String] = (Int, String). The parenthesis alone is syntactic sugar.
    val sugarTuple = (2, "hello, Scala") // Does the same thing, in the same way that Python does.

    println(aTuple._1) // This is how we access the members. NOT zero-indexed
    println(aTuple.copy(_2 = "goodbye Java")) // Makes a copy.
    println(aTuple.swap) // Swaps the elements in place.

    // Maps associated keys to values.
    val aMap: Map[String, Int] = Map()
    // String is key, Int is value.

    val phonebook = Map(("Jim", 555), "Daniel" -> 789, ("JIM", 9000)).withDefaultValue(-1) // Method provides a default value for non-existent keys.
    // Can do Tuple with () or with ->
    println(phonebook)

    // Map operations - can query it if it has a key.
    println(phonebook.contains("Jim"))
    println(phonebook("Jim")) // .apply() will return value associated with key
    
    // Add a pairing
    val newPairing = "Mary" -> 678
    val newPhonebook = phonebook + newPairing
    println(newPhonebook)

    // Functionals on maps: map, flatMap, and filter
    println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) 
    // map method takes a pairing when working with Maps

    // filterKeys
    println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap) 
    // Prints mapping where keys are filtered by the J predicate.

    // mapValues
    println(phonebook.view.mapValues(number => "0245-" + number).toMap)
    // Adds a prefix to each value.

    // Conversions to other collections
    println(phonebook.toList)
    println(List(("Daniel", 555)).toMap) 
    // Converts a list of pairings to a Map

    val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
    println(names.groupBy(name => name.charAt(0))) // Makes lists of names with the same character at index 0.

    /* 
        Exercises:
            1. What would happen if on "println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) " if there were two original entries instead of one?
                - Make sure when mapping keys that resulting keys will not overlap
                !!! - like with Jim and JIM when becoming lowercase.
            2. Oversimplied social network based on maps
                Person = String
                Network keeps association between each name and list of friends.
                - Add person to network
                - Remove person from network
                - Friend a person in the network (mutual)
                - Unfriend a person in the network

                Stats:
                - Number of friends of a person
                - Person with most firends
                - How many people have no friends
                - If there is a social connection between two people (direct or indirect)
     */

    // Maps can be used to create a graph data structure
    
    def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
        network + (person -> Set()) // Adds a new pairing of a person and an empty set

    // Using Set prevents duplicate values in each friends list.

    def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
        val friendsA = network(a)
        val friendsB = network(b)

        network + (a -> (friendsA + b)) + (b -> (friendsB + a)) // New friend gets added to friend set
    }

    def unfreind(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
        val friendsA = network(a)
        val friendsB = network(b)

        network + (a -> (friendsA - b)) + (b -> (friendsB - a))
    }

    def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
        // Cannot remove person without removing their friends first, so we need an auxillary function.
        def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
            if (friends.isEmpty) networkAcc
            // If all friends have been removed, return the network accumulator.
            else removeAux(friends.tail, unfreind(networkAcc, person, friends.head))
            // This takes the first friend and unfriends them, and then passes the rest of the friends list into the function.

        val unfriended = removeAux(network(person), network)
        // Removes all friendships from person to be deleted
        unfriended - person
        // Removes the person to be deleted
    }

    val empty: Map[String, Set[String]] = Map()
    val network = add(add(empty, "Mary"), "Bob")
    println(network)
    println(friend(network, "Bob", "Mary"))
    println(remove(friend(network, "Bob", "Mary"), "Bob"))

    // Build small network with Jim Bob and Mary
    val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
    val jimBob = friend(people, "Bob", "Jim")
    val testNet = friend(jimBob, "Bob", "Mary")

    println(testNet)

    // Count how many friends someone has
    def nFriends(network: Map[String, Set[String]], person: String): Int = 
        if (!network.contains(person)) 0
        else network(person).size

    println(nFriends(testNet, "Bob"))

    // Find who has the most friends
    def mostFriends(network: Map[String, Set[String]]): String =
        network.maxBy(pair => pair._2.size)._1

    println(mostFriends(testNet))

    // Find people with no friends
    def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
        network.count(_._2.isEmpty) // Can also be written as "network.view.filterKeys(k => network(k).isEmpty).size"
    
    println(nPeopleWithNoFriends(testNet))

    // Find if someone is connected to someone else
    def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {

        // Using a breadth-first search method on the graph
        @tailrec
        def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = { 
            // Can I find my target somewhere in discoveredPeople having already seen the consideredPeople

            if (discoveredPeople.isEmpty) false
            // If there is nobody to search, return false

            else {

                val person = discoveredPeople.head
                // Consider a person in discoveredPeiple

                if (person == target) true
                // If person is the target, found them!

                else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
                // If the person is already within considered people, return to the breadth first search and run it recursively

                else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
                // Add the person to consideredPeople, then the rest of discoveredPeople will be the tail and also append the person's direct friends to expand the network of people that can be searched.
            }
        }

        bfs(b, Set(), network(a) + a)
        // Target is b, considered people is an empty set, and the network is the friends of a
    }

    println(socialConnection(testNet, "Mary", "Jim"))
}