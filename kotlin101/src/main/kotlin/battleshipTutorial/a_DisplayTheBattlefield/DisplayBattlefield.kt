package battleshipTutorial.a_DisplayTheBattlefield

fun main(args: Array<String>) {

    // Create battlefield matrix
    val battlefield: Array<Array<Int>> = arrayOf(
            arrayOf(0, 0, 0, 2, 2, 2 ,2, 0, 0, 0),      // ship hidden
            arrayOf(0, 0, 0, 0, 0, 0 ,0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0 ,0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0 ,0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 1, 0 ,0, 1, 0, 0),      // shot but nothing hit
            arrayOf(0, 0, 0, 0, 0, 0 ,0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0 ,0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0 ,3, 2, 2, 0),      // ship hit partially
            arrayOf(0, 0, 0, 0, 0, 0 ,0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0 ,0, 0, 0, 0)
    )




    //** Display Battlefield
    println("")
    println("= Display Battlefield =")
    println("")

    for(row in battlefield) {  // loop through rows

        for(column in row) {  // loop through columns
            print(" .")     // print a dot, add space for pretty formatting
        }

        println("") // go to next line after each row
    }






    //** Add row prefix
    println("")
    println("= Add row prefix= ")
    println("")


    // Method 1: Old school
    println("Method 1")
    println("")

    val rowPrefix_theBoringWay = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")

    val rowPrefix_theCoolWay = getCharacterList('a', 'j')  // Note single quote!

    var rowCounter = 0  // needed for looping through rowPrefixes

    for(row in battlefield) {
        print(rowPrefix_theCoolWay[rowCounter])    // print row name, based on counter

        for(column in row) {
            print(" .")
        }
        println("")

        rowCounter++    // update row counter
    }







    // Method 2: More with less
    println("")
    println("Method 2")
    println("")

    for ((index, value) in battlefield.iterator().withIndex()) {    // loop through battlefield (rows), expose value and index

        print(rowPrefix_theCoolWay[index])  // print row name

        value.forEach {     // loop through each column
            print(" .")
        }

        println("")
    }



    //** Add column prefix
    println("")
    println("= Add column name =")
    println("")

    for ((index, value) in battlefield.iterator().withIndex()) {
        print(rowPrefix_theCoolWay[index])
        value.forEach {
            print(" .")
        }
        println("")
    }

    print(" ")
    for(rowName in 1..10)   // print range from 1 till 10
        print(" $rowName")






    //** Display correct symbols
    println("")
    println("")
    println("= Display correct symbols =")
    println("")

    for ((index, value) in battlefield.iterator().withIndex()) {
        print(rowPrefix_theCoolWay[index])
        value.forEach {


            when(it) {      // print correct symbol for value in matrix
                0, 2 -> print(" .")
                1 -> print(" _")
                3 -> print(" #")
            }


        }
        println("")
    }

    print(" ")
    for(rowName in 1..10)
        print(" $rowName")




}


// Cool trick with characters
fun getCharacterList(startCharacter: Char, endCharacter: Char) : List<String> {
    var c = startCharacter  // make a copy of input variable -> startCharacter is read-only
    val characterList: MutableList<String> = mutableListOf()    // create empty list

    while (c <= endCharacter) {     // increment character until "end character" is reached
        characterList.add(c++.toString())   // add character to list, then increment
    }

    return characterList
}