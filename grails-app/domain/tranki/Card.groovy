package tranki

class Card {

    String front
    String back
    int points
    Difficulty difficulty

    def difficultyPoints = [20, 15, 10]

    enum Difficulty {
        EASY,
        NORMAL,
        HARD
    }

    static constraints = {
    }

    Card(String front, String back) {
        // cards at first are set to NORMAL difficulty.

        this.front = front
        this.back = back
        this.points = difficultyPoints[Difficulty.NORMAL.ordinal()]
        this.difficulty = Difficulty.NORMAL
    }
}
