package lectures.part1Basics

object Expressions extends App {

    val x = 1 + 2 // this is an expression, which means it is evaluated to a value. Expressions have a type.
    println(x)

    println(2 + 3 * 4) //follows PEMDAS
    // + - * / & | ^ << >> >>> (right shift with zero extension)

    println(1 == x) //this will say false
    // == != > >= < <=

    println(!(1==x)) //logical negation
    // ! && ||

    var aVariable = 2
    aVariable += 3
    println(aVariable) // this will print 5
    // -= *= /= += are all side effects

    //Instructions vs. Expressions
    //Instruction: something you tell the computer to do
    //Expression: something that has a value and/or a type

    //example: if expression.
    val aCondition = true
    val aConditionedValue = if(aCondition) 5 else 3 //Called an if EXPRESSION because it gives back a value.
    println(aConditionedValue)

    //loops exist but are discouraged bc of side effects.
    var i = 0
    while (i<10) {
        println(i)
        i += 1
    }
    //NEVER WRITE THIS AGAIN. NOT THE PREFERRED METHOD FOR ITERATION. This is very specific to imperative programming, NOT expressive programming.
    //Scala forces everything to be an expression.

    val aWeirdValue = (aVariable = 3)
    // This is type unit, which is equivalent to void in other languages.
    // The value of unit prints to (), which is the only value a unit type can hold.
    // Re-assigning a variable is a side effect. Side effects are expressions that return 'Unit'

    val aWhile = while (i<10) { // Type of aWhile is a unit
        println(i)
        i += 1
    }

    //Side effects include println(), whiles, reassinging variables

    //Code blocks are a special type of expressions with special properties.

    val aCodeBlock = {
        val y = 2
        val z = y + 1

        if (z > 2) "hello" else "goodbye"
    }
    // Code blocks are surrounded by curly braces. Inside the curly braces you can define values, write expressions, etc.
    // A code block is an expression. The value of the whole block is the value of the last expression.
    // So, the value of this code block is a string, since the last expression is the if statement returning a string.

    // val anotherValue = z + 1 
    // This will not work because z is defined in a code block, which is not visible to the outside.

    // Instructions mean do something, expressions mean give me the value of something

    //Questions
    //I. What is the difference between "hello world" and println("hello world"). Answer: the former is a string, the latter is a unit and has a side effect
    
    //II. What is the value of:
    val someValue = {
        2 < 3
    } //True, Boolean

    val someOtherValue = {
        if(someValue) 239 else 986
        42
    } // 42, Int
}