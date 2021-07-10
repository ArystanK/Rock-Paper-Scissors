package kz.arctan.rock_paper_scissors

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var rockButton: ImageButton
    private lateinit var paperButton: ImageButton
    private lateinit var scissorsButton: ImageButton
    private lateinit var enemyChoiceImageView: ImageView
    private lateinit var playerHealthBarView: HealthBarView
    private lateinit var enemyHealthBarView: HealthBarView
    private lateinit var playerHealthTextView: TextView
    private lateinit var enemyHealthTextView: TextView
    private lateinit var restartButton: Button

    private val gameViewModel: GameViewModel by lazy {
        ViewModelProvider(this).get(GameViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rockButton = findViewById(R.id.rock_button)
        paperButton = findViewById(R.id.paper_button)
        scissorsButton = findViewById(R.id.scissors_button)
        enemyChoiceImageView = findViewById(R.id.enemy_choice_image_view)
        playerHealthBarView = findViewById(R.id.player_health_bar_view)
        enemyHealthBarView = findViewById(R.id.enemy_health_bar_view)
        playerHealthTextView = findViewById(R.id.player_health_text_view)
        enemyHealthTextView = findViewById(R.id.enemy_health_text_view)
        restartButton = findViewById(R.id.restart_button)

        rockButton.setOnClickListener { play(0) }
        paperButton.setOnClickListener { play(1) }
        scissorsButton.setOnClickListener { play(2) }
        restartButton.setOnClickListener {
            restart()
        }
    }

    private fun play(choice: Int) {
        gameViewModel.makePlayerChoice(choice)
        gameViewModel.makeEnemyChoice()
        enemyChoiceImageView.setImageResource(gameViewModel.enemy.choice.id)
        when (gameViewModel.player.choice.compareTo(gameViewModel.enemy.choice)) {
            1 -> {
                gameViewModel.enemy.reduceHealth()
                enemyHealthBarView.reduceHealth()
                enemyHealthTextView.text = gameViewModel.enemy.health.toString()
            }
            -1 -> {
                gameViewModel.player.reduceHealth()
                playerHealthBarView.reduceHealth()
                playerHealthTextView.text = gameViewModel.player.health.toString()
            }
        }
        if (gameViewModel.enemy.health <= 0)
            endGame(true)

        if (gameViewModel.player.health <= 0)
            endGame(false)
    }

    private fun endGame(hasWon: Boolean) {
        unableEverything()
        val alertDialogBuilder = AlertDialog.Builder(this)
            .setTitle("End game")
            .setPositiveButton("Yes") { _, _ -> restart() }
            .setNegativeButton("No") { dialog, _ -> dialog.cancel() }
        if (hasWon)
            alertDialogBuilder.setMessage("You won. Start again?")
        else
            alertDialogBuilder.setMessage("You lost. Start again?")
        alertDialogBuilder.show()
    }

    private fun restart() {
        rockButton.isEnabled = true
        paperButton.isEnabled = true
        scissorsButton.isEnabled = true
        restartButton.visibility = View.GONE
        gameViewModel.restart()
        playerHealthBarView.reset()
        enemyHealthBarView.reset()
        playerHealthTextView.text = getString(R.string.initial_health)
        enemyHealthTextView.text = getString(R.string.initial_health)
    }

    private fun unableEverything() {
        restartButton.visibility = View.VISIBLE
        rockButton.isEnabled = false
        paperButton.isEnabled = false
        scissorsButton.isEnabled = false
    }
}
