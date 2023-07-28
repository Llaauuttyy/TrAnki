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
        // deck(nullable: false)
        // front(nullable: false, blank: false)
        // back(nullable: false, blank: false)
        // difficulty(nullable: false)
        // <> null
        // string distinto de vacio.
        // Validar que los parámetros estén como en la constraints.
    }

    static belongsTo = [
        deck: Deck
    ]

    Card(String front, String back, Deck deck) {
        // deck, front, back cant be null
        // cards at first are set to HRAD difficulty.
        this.deck = deck
        this.front = front
        this.back = back
        this.difficulty = Difficulty.HARD
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
