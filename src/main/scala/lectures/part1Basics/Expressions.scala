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
}