package lectures.part3fp

import scala.util.Failure
import scala.util.Success
import scala.util.Try
import scala.util.Random
import lectures.part3fp.HandlingFailure.HttpService.getConnection

// Try is a wrapper for a computation that may or may not fail.

object HandlingFailure extends App {


    // Create success and failure explicitly
    val aSuccess = Success(3)
    val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

    println(aSuccess)
    println(aFailure)

    // Most of the time we do not need to contstruct success and failure ourselves. Try will handle it for us.

    def unsafeMethod(): String = throw new RuntimeException("No string for you")

    // Construct Try objects using apply() method
    val potentialFailure = Try(unsafeMethod()) // Program did not crash because Try handled the exception.
    println(potentialFailure)

    // Synatactic sugar for Try
    val anotherPotentialFailure = Try {
        // Code that might throw
    }

    // Utilities
    println(potentialFailure.isSuccess) // Boolean that tells if the code has failed or not.
    println(potentialFailure.isFailure)

    // orElse
    def backupMethod(): String = "Valid result" // This can be chained with the unsafe method
    val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod())) // Done in the same way as Options
    println(fallbackTry)

    // If you design an API, wrap your code in a Try
    def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
    def betterBackupMethod(): Try[String] = Success("Valid result")
    val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

    // Use Try to protect from exceptions, use Option to guard against null

    // map, flatMap, filter
    println(aSuccess.map(_ * 2))
    println(aSuccess.flatMap(x => Success(x * 10)))
    println(aSuccess.filter(_ > 10)) // filter could possibly turn a success into a failure which will contain an exception

    // We can also use for-comprehensions

    val hostname = "localhost"
    val port = "8080"
    def renderHTML(page: String) = println(page)

    class Connection {
        def get(url: String): String = {
            val random = new Random(System.nanoTime)
            if (random.nextBoolean()) "<html>...</html>"
            else throw new RuntimeException("Connection interrupted")
        }

        def getSafe(url: String): Try[String] = Try(get(url))
    }

    object HttpService {
        val random = new Random(System.nanoTime)

        def getConnection(host: String, port: String): Connection = {
            if (random.nextBoolean()) new Connection
            else throw RuntimeException("Port unavailable")
        }

        def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
    }

    // if you get the html page from connection, print it to the console.
    val possibleConnection = HttpService.getSafeConnection(hostname, port)
    val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
    possibleHTML.foreach(renderHTML)

    // Shorthand version
    HttpService.getSafeConnection(hostname, port)
        .flatMap(connection => connection.getSafe("/home"))
        .foreach(renderHTML)

    // For comprehension version
    for {
        connection <- HttpService.getSafeConnection(hostname, port)
        html <- connection.getSafe("/home")
    } renderHTML(html)
}