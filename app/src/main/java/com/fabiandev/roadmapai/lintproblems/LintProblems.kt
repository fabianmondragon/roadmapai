package com.fabiandev.roadmapai.lintproblems

class LintProblems {

    // Lint issue: The method name should be in camelCase, not snake_case.
    fun example_method() {
        // Lint issue: 'var' should be used for variable declarations instead of 'val' where reassignment is needed.
        val a = 10
        var b = 20
        val result = a + b
        println("The result is: $result")

        // Lint issue: Missing trailing comma in the list
        val list = listOf(
            "apple",
            "banana"
        )

        // Lint issue: Missing spaces around the operator
        if(a+b == result){
            println("Lint check passed!")
        }
    }

    // Lint issue: Function name should be in camelCase
    fun AnotherMethod() {
        // Lint issue: Variable 'a' should be private
        val a = 5
        println("Value of a: $a")
    }
}