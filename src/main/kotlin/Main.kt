import Door.BehindDoor.GOAT
import Door.BehindDoor.PRIZE
import kotlin.random.Random

fun main() {
    val totalPlays = Random.nextLong(1, 99999999)
    println(totalPlays)
    LongRange(1, totalPlays).sumOf {
        val doorPrize = Door(PRIZE)

        val choosedDoor = MontyHall(doorPrize, Door(GOAT), Door(GOAT))
            .chooseDoorRandomly()
            .giveWrongDoor()
            .changeChooseDoor()
            .getChoosedDoor()

        if (choosedDoor == doorPrize) 1L else 0L
    }.let { println("Total $it hits of $totalPlays (${it * 100 / totalPlays}%)") }
}

class MontyHall(private val doorA: Door, private val doorB: Door, private val doorC: Door) {

    private lateinit var choosed: Door
    private lateinit var wrong: Door

    fun chooseDoorRandomly() = apply { choosed = listOf(doorA, doorB, doorC).random() }

    fun giveWrongDoor() =
        apply { wrong = listOf(doorA, doorB, doorC).first { it != choosed && it.behindDoor != PRIZE } }

    fun changeChooseDoor() = apply { choosed = listOf(doorA, doorB, doorC).first { it != choosed && it != wrong } }

    fun getChoosedDoor() = choosed

}

class Door(val behindDoor: BehindDoor) {

    enum class BehindDoor {
        PRIZE,
        GOAT
    }

}
