import java.lang.Math.*

/**
 * Created by ebonet on 12/3/17.
 */


fun main(args: Array<String>) {

    fun spiralMemory(step: Int): Int {


        val lastSide = Math.floor(Math.sqrt(step.toDouble()))

        if (step % lastSide.toInt() == 0) {
            return lastSide.toInt()-1
        }


        val x = Math.floor((lastSide+1) / 2)

        val lastSqrSize = lastSide * lastSide
        val nInLine = (lastSide+1) * (lastSide+1) - lastSqrSize

        val valPosition = step - lastSqrSize - 1

        val middle  = Math.floor(lastSide / 2)

        val y = min(
                abs(valPosition - middle),
                abs(valPosition - nInLine + middle +1)
        )

        val totalDistance = abs(x) + abs(y)

        return totalDistance.toInt()
    }

    /*
    Data from square 1 is carried 0 steps, since it's at the access port.
    Data from square 12 is carried 3 steps, such as: down, left, left.
    Data from square 23 is carried only 2 steps: up twice.
    Data from square 1024 must be carried 31 steps.
     */

    val testCases = mapOf( 1 to 0, 12 to 3, 23 to 2, 1024 to 31, 16 to 3)

    for ((input, expected) in testCases) {
        println("Spiral Memory of $input is ${spiralMemory(input)}, should be $expected")
    }

    println("Solution for challenge 1 is ${spiralMemory(347991)}")




}

fun spiralMemoryPart2() {

    fun spiralToLinear(x: Int,y: Int): Int {
        return -1
    }

    fun linearToSpiral(x:Int): Pair<Int, Int> {
        return Pair(-1, -1)
    }

    fun getNext(linearPosition: Int, computedGrid: List<Int>): Int {
        val (x0,y0) = linearToSpiral(linearPosition)
        var current = 0
        for (x in -1 until 0) {
            for (y in -1 until 0) {
                val k = spiralToLinear(x+x0, y+y0)
                current += if (k < computedGrid.size) computedGrid[k] else 0
            }
        }

        return current
    }

    var grid = listOf<Int>()
    for (i in 0..10) {
        val next = getNext(i, grid)
        println("Element $i is $next")
        grid += next
    }

}