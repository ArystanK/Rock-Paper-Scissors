package kz.arctan.rock_paper_scissors

import androidx.lifecycle.ViewModel
import java.util.*

class GameViewModel : ViewModel() {
    var player = Character()
    var enemy = Character()

    fun makePlayerChoice(choice: Int) {
        when (choice) {
            0 -> player.choice = Choice.Rock
            1 -> player.choice = Choice.Paper
            2 -> player.choice = Choice.Scissors
        }
    }

    fun makeEnemyChoice() {
        when (Random().nextInt(3)) {
            0 -> enemy.choice = Choice.Rock
            1 -> enemy.choice = Choice.Paper
            2 -> enemy.choice = Choice.Scissors
        }
    }

    fun restart() {
        player = Character()
        enemy = Character()
    }
}