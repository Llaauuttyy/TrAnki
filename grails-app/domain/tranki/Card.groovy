package tranki

class Card {

    String front
    String back
    int points
    Difficulty difficulty

    enum Difficulty {
        EASY,
        NORMAL,
        HARD
    }

    static constraints = {
    }
}
