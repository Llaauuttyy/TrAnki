package tranki

enum Difficulty {
        EASY(20),
        NORMAL(15),
        HARD(10)

        int points

        Difficulty(int points) {
            this.points = points
        }
    }

class Card {

    Deck deck
    String front
    String back
    Difficulty difficulty

    static constraints = {
        // Validar que los parámetros estén como en la constraints.
    }

    static belongsTo = [
        deck: Deck
    ]

    Card(String front, String back, Deck deck) {
        // cards at first are set to NORMAL difficulty.
        this.deck = deck
        this.front = front
        this.back = back
        this.difficulty = Difficulty.NORMAL
    }

    def changeDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty
    }

    boolean isDifficulty(Difficulty difficulty) {
        this.difficulty == difficulty
    }

    int getPoints() {
        this.difficulty.points
    }
}
