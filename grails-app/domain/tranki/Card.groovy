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
        deck(nullable: false)
        front(nullable: false, blank: false)
        back(nullable: false, blank: false)
        difficulty(nullable: false)
    }

    static belongsTo = [
        deck: Deck
    ]

    Card(String front, String back, Deck deck) {
        if (!front || front.trim().isEmpty() || !back || back.trim().isEmpty() || !deck) {
            throw new IllegalArgumentException("Arguments cant be null or empty")
        }

        this.deck = deck
        this.front = front
        this.back = back
        this.difficulty = Difficulty.HARD
    }

    def changeDifficulty(Difficulty difficulty) {
        this.setDifficulty(difficulty)
    }

    boolean isDifficulty(Difficulty difficulty) {
        this.difficulty == difficulty
    }

    int getPoints() {
        this.difficulty.points
    }
}
