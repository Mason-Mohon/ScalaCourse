package lectures.part2oop
// Packages are in hierarchy, which matches folder structure in file system. That is what "lectures.part2oop" indicates.
import playground.{Raskolnikov => Criminal, Alyosha} // In order to use a class, trait, or top level definition by simple name, you have to import the class.
// Imports can be aliased.
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

    // A package is a bunch of definitions grouped under the same name.
    // Members within a package are visible by the their simple name.

    val writer = new Writer("Daniel","RockTheJVM",2018)
    // Package members are accessible by their simple name. The writer class is in OOBasics.scala so we can access it.
    // If an object is not in the same package you have to import it.

    val criminal = new Criminal // Import statement automatically added by the compiler.
    // Alternatively, could use fully qualified class name:
        // val criminal = new playground.Raskolnikov ... This would not require an import.

    // Package objects - we might need some universal constants or methods that are outside of classes.
    // A package can only contain one package object, called package.scala.
    sayHello    // This can be called by its simple name from the package.scala.
    println(SPEED_OF_LIGHT)
    // Package objects are used rarely.

    // imports
    val priest = new Alyosha

    val d = new java.util.Date // Cannot just say date because it is ambiguous. Either use fully qualified name or aliasing.

    /* 
    Defauly imports:
        - java.lang - String, Object, Exception
        - scala - Int, Nothing, Function
        - scala.Predef - println, ???
    */
}