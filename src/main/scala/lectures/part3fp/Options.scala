package lectures.part3fp

import scala.util.Random

/* 
Null references are bad and call null pointer exceptions, so we use lots of spaghetti code to guard against them.

Options are wrappes for a value that might be present or not. Types are:
    - Some, which wraps a concrete value
    - None, a singleton for absent values

Options are present in many places, such as:
    - Map, which uses .get() and will return None if key is not present.
    - List, which uses .find to return something that might not be there.
*/

object Options extends App {

    val myFirstOption: Option[Int] = Some(4)
    val noOption: Option[Int] = None

    println(myFirstOption)

    // Options were invented to deal with unsafe APIs
    def unsafeMethod(): String = null
    // val result = Some(unsafeMethod()) // This is wrong because you might get null. Some should always have a valid value inside.
    val result = Option(unsafeMethod()) // Takes care to build a Some or None depending on if the value is safe or not
    println(result) // We should never do null checks ourselves. The Option type will do it for us.

    // Chained methods
    def backupMethod(): String = "A valid result"
    val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod())) // If something is null it will fall back to the .orElse() argument

    // Design unsafe APIs
    def betterUnsafeMethod(): Option[String] = None
    def betterBackupMethod(): Option[String] = Some("A valid result")

    val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

    // Functions on options
    println(myFirstOption.isEmpty)
    println(myFirstOption.get) // Tries to retrive the value, but that is unsafe because if there is a null in there we will get an exception.

    // map, flatmap, filter
    println(myFirstOption.map(_ * 2))
    println(myFirstOption.filter(x => x > 10))
    println(myFirstOption.flatMap(x => Option(x * 10)))

    // for-comprehensions
    val config: Map[String, String] = Map(
        // Fetched from elsewhere, but we do not have certainty that keys have values inside the map. (Hypothetically)
        "host" -> "111.22.33.4",
        "port" -> "80"
    )

    class Connection {
        def connect = "Connected" // In reality this would connect to a server
    }

    object Connection {
        val random = new Random(System.nanoTime())
        def apply(host: String, port: String): Option[Connection] =
            if (random.nextBoolean()) Some(new Connection)
            else None
    }

    // Try to establish a connection. If so, print the connect method
    val host = config.get("host")
    val port = config.get("port")
    /* 
        if (h != null)
            if (p != null)
                return Connection.apply(h, p)

        return null

        This is the imperative programming version of what is going on here.
     */
    val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
    /* 
        if (c != null)
            return c.connect
        return null
     */
    val connectionStatus = connection.map(c => c.connect)
    println(connectionStatus)
    connectionStatus.foreach(println)

    // Chained calls
    config.get("host")
        .flatMap(host => config.get("port")
            .flatMap(port => Connection(host, port))
            .map(connection => connection.connect)
        )
        .foreach(println)

    // For-comprehensions
    val forConnectionStatus = for {
        host <- config.get("host")
        port <- config.get("port")
        connection <- Connection(host, port)
    } yield connection.connect
    forConnectionStatus.foreach(println)
}