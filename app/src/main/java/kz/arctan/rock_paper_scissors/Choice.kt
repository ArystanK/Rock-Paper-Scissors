package kz.arctan.rock_paper_scissors

sealed class Choice : Comparable<Choice> {
    open val id: Int = 0

    object Rock : Choice(), Comparable<Choice> {
        override val id = R.drawable.rock
        override fun compareTo(other: Choice): Int {
            return when (other) {
                is Rock -> 0
                is Paper -> -1
                is Scissors -> 1
            }
        }
    }

    object Paper : Choice(), Comparable<Choice> {
        override val id = R.drawable.paper
        override fun compareTo(other: Choice): Int {
            return when (other) {
                is Rock -> 1
                is Paper -> 0
                is Scissors -> -1
            }
        }
    }

    object Scissors : Choice(), Comparable<Choice> {
        override val id = R.drawable.scissors
        override fun compareTo(other: Choice): Int {
            return when (other) {
                is Rock -> -1
                is Paper -> 1
                is Scissors -> 0
            }
        }
    }
}