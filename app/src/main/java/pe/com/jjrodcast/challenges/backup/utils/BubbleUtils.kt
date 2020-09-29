package pe.com.jjrodcast.challenges.backup.utils

import pe.com.jjrodcast.challenges.backup.models.Bubble
import pe.com.jjrodcast.challenges.ui.pinkPrimary
import pe.com.jjrodcast.challenges.ui.pinkPrimaryVariant
import pe.com.jjrodcast.challenges.ui.pinkSecondary
import kotlin.random.Random


object BubbleUtils {

    fun createBubbles(total: Int): List<Bubble> {
        val bubbles = arrayListOf<Bubble>()
        for (i in 0 until total) {
            val bubble = Bubble(
                radius = Random.nextInt(30, 60).toFloat(),
                verticalSpeed = Random.nextInt(12, 18),
                horizontalSpeed = Random.nextInt(1, 10),
                direction = if (Random.nextBoolean()) 1 else -1,
                color = createColor(),
                initialPos = Random.nextInt(6, 10).toFloat()
            )
            bubbles.add(bubble)
        }
        return bubbles
    }

    private fun createColor() = when (Random.nextInt(3)) {
        0 -> pinkPrimary
        1 -> pinkPrimaryVariant
        else -> pinkSecondary
    }
}