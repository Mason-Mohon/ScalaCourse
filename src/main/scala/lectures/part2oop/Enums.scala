package lectures.part2oop

object Enums {

    enum Permissions { // Datatype, created in a similar way to a class
        case READ, WRITE, EXECUTE, NONE // Already enumerates all the possible cases or instances of this type
        // Sealed datatype, cannot be extended.

        // Can add fields/methods
        def openDocument(): Unit =
            if (this == READ) println("Opening Document")
            else println("No reading permissions")
        }

    val somePermissions: Permissions = Permissions.READ // Instantiates some permissions to a variable.

    // Constructor arguments
    enum PermissionsWithBits(bits: Int) {
        case READ extends PermissionsWithBits(4) // 100
        case WRITE extends PermissionsWithBits(2) // 010
        case EXECUTE extends PermissionsWithBits(1) // 001
        case NONE extends PermissionsWithBits(0) // 000
    }

    // Can have companion objects for enums
    object PermissionsWithBits {
        def fromBits(bits: Int): PermissionsWithBits = // code would go here
            PermissionsWithBits.NONE
    }

    // Standard API of enums built in with all enums
    val somePermissionsOrdinal = somePermissions.ordinal
    // Can iterate all the possible values of an enum, useful when trying to use an external library.
    val allPermissions = PermissionsWithBits.values
    val readPermission: Permissions = Permissions.valueOf("READ") // String is parsed and matched with enum case


    def main(args: Array[String]): Unit = {
        somePermissions.openDocument()
        println(somePermissions.ordinal)
    }
}