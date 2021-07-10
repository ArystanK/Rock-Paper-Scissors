package kz.arctan.rock_paper_scissors

class Character {
    private var _health = 100
    val health
        get() = _health
    lateinit var choice: Choice

    fun reduceHealth() {
        _health -= 10
    }
}