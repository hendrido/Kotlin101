import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    println("Loading missiles...")
    val battleField = createBattleField()
    displayBattlefield(battleField)
    var giveUp = false

    while(!giveUp){
        print("What are your orders captain? : ")
        val userInput = readLine()
        userInput?.let {
            when(it.toLowerCase()){
                "quit" ->{
                    println("Farewell, captain.")
                    giveUp = true
                }
                "help" -> println("Print help...")
                "show battlefield" -> displayBattlefield(battleField)
                else -> {
                    executeAttackOrder(it, battleField)
                }
            }
        }
    }
}

fun executeAttackOrder(coordinates: String, battleField: Array<Array<Int?>>){
    println("Sending missile to $coordinates... ")
    val y = coordinates.elementAt(0).toString()
    val tmp = coordinates.removeRange(0,1)
    val x = tmp.toInt()

    val newBattleField = updateBattleField(y, x, battleField)
    displayBattlefield(newBattleField)
}

fun createBattleField(): Array<Array<Int?>> {
    val battleField = Array(10) { arrayOfNulls<Int>(10) }
    battleField.set(0, arrayOf(0,0,0,0,2,2,2,0,0,0))
    battleField.set(1, arrayOf(0,0,0,0,0,0,0,0,0,0))
    battleField.set(2, arrayOf(0,0,0,0,0,0,0,0,0,0))
    battleField.set(3, arrayOf(0,0,0,0,0,0,0,0,0,0))
    battleField.set(4, arrayOf(0,0,0,0,0,0,0,0,0,0))
    battleField.set(5, arrayOf(0,0,0,0,0,0,0,0,0,0))
    battleField.set(6, arrayOf(0,0,0,0,0,0,0,0,0,0))
    battleField.set(7, arrayOf(0,0,0,0,0,0,0,0,0,0))
    battleField.set(8, arrayOf(0,0,0,0,0,0,0,0,0,0))
    battleField.set(9, arrayOf(0,0,0,0,0,0,0,0,0,0))
    return battleField
}

// 0 = nothing => .
// 1 = guessed => _
// 2 = ship (invisible) -> .
// 3 = hit  => #
// 4 = destroyed => @

fun updateBattleField(y: String, x: Int, currentField: Array<Array<Int?>>) : Array<Array<Int?>> {
    val rowNumber = getPositionInAlphabeth(y.toLowerCase()) - 1

    if((rowNumber >= currentField.count()) or (x > currentField.count())) {
        println("Unknown coordinates!")
        return currentField
    }

    val row = currentField.get(rowNumber)
    when(row.get(x-1)){
        0,1 -> {
            row.set(x-1, 1)
            println("Missed target.")
        }
        2,3 -> {
            row.set(x-1, 3)
            println("Hit enemy ship!")
        }
    }

    currentField.set(rowNumber, row)
    return currentField
}

fun getPositionInAlphabeth(character: String): Int{
    val ch = character.toCharArray()
    val temp = ch[0].toInt()
    val tempInteger = 96 //for lower case
    return if ((temp <= 122) and (temp >= 97))
        temp - tempInteger
    else
        -1
}

fun displayBattlefield(battleField: Array<Array<Int?>>){
    val rowPrefix = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")
    var rowIterator = 0

    for(row in battleField){
        // print y-coordinates
        print(rowPrefix[rowIterator])

        // print row of battlefield
        for(location in row) {
            when(location){
                0,2 -> print(" .")
                1 -> print(" _")
                3 -> print(" #")
            }
        }

        rowIterator += 1

        println()
    }

    print(" ")

    // print x-coordinates
    for(i in 1..10) {
        print(" $i")
    }

    println()
    println()
}
