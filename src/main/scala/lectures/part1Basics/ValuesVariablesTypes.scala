package lectures.part1Basics

object ValuesVariablesTypes extends App {

    val x: Int = 42
    println(x)

    //val cannot be reassigned. They are set in stone.
    //VALS ARE IMMUTABLE
    //Types of vals are optional, because the compiler can infer that 42 is an int type.
    //If we say the type is Int and it is a string, the compiler will get upset

    val aString: String = "hello"; val anotherString = "goodbye"//Can use a semicolon to put multiple expressions on the same line, but that is not the recommended Scala style

    val aBoolean: Boolean = false
    val aChar: Char = 'a'//characters are single characters in between single quotes

    val anInt: Int = x//can be another val

    val aShort: Short = 44 //Short is int with half representation size, on two bytes instead of four. Number cannot be too big

    val aLong: Long = 387529038790658722L //Big number on eight bytes. Must include L at the end of the number

    val aFloat: Float = 2.0f
    val aDouble: Double = 3.14//doubles do not need the f at the end

    //variables
    var aVariable: Int = 4 //variables can be reassigned

    aVariable = 5 
    //side effects - functional programming thing that lets you see what your programs are doing. Like displaying something in the console or on screen. These are important to be mindful of
}