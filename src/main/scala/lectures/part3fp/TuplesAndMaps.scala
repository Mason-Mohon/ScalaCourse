package lectures.part3fp

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

    val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1) // Method provides a default value for non-existent keys.
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
}