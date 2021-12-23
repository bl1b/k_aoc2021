fun main() {
    val result = runDay2("day2.txt", Submarine())
    println(result)
    val result2 = runDay2("day2.txt", AimedSubmarine())
    println(result2)
}

open class Submarine(var xPos: Int = 0, var yPos: Int = 0) {

    open fun applyCommand(command: String, value: Int) {
        if(command == "forward") {
            xPos += value
        } else if(command == "down") {
            yPos += value
        } else if(command == "up") {
            yPos -= value
        }
    }

    fun coordinateProduct(): Int = xPos * yPos
}

class AimedSubmarine(var aim: Int = 0) : Submarine(0, 0) {
    override fun applyCommand(command: String, value: Int) {
        if(command == "forward") {
            xPos += value
            yPos += aim * value
        } else if(command == "down") {
            aim += value
        } else if(command == "up") {
            aim -= value
        }
    }
}

fun runDay2(filePath: String, sub: Submarine): Int {
    fileAsString(filePath)
        .split("\n")
        .forEach({line ->
            val parts = line.split(" ")
            sub.applyCommand(parts[0].trim(), parts[1].trim().toInt())
        })
    return sub.coordinateProduct()
}
