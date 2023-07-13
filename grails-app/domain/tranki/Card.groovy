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

    String front
    String back
    Difficulty difficulty

    static constraints = {
    }

    Card(String front, String back) {
        // cards at first are set to NORMAL difficulty.
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
