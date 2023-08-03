package tranki

class Stats {
    int deckAmount
    int cardAmount
    int currentPoints
    int maxPoints

    static constraints = {
    }

    void calculate(Set<Deck> decks) {
        this.getLearnerDeckAmount(decks)
        this.getLearnerCardAmount(decks)
        this.getLearnerCurrentPoints(decks)
        this.getLearnerMaxPoints(decks)
    }

    void getLearnerMaxPoints(Set<Deck> decks) {
        this.maxPoints = 0

        if (this.deckAmount > 0) {
            this.maxPoints = decks.collect{deck -> deck.getSize() * Difficulty.EASY.points}.sum()
        }
    }

    void getLearnerCurrentPoints(Set<Deck> decks) {
        this.currentPoints = 0

        if (this.deckAmount > 0 && this.cardAmount > 0) {
            this.currentPoints = decks.collectMany{deck -> deck.getCards()}.collect{card -> card.getPoints()}.sum()
        }
    }

    void getLearnerDeckAmount(Set<Deck> decks) {
        this.deckAmount = decks.size()
    }

    void getLearnerCardAmount(Set<Deck> decks) {
        this.cardAmount = 0

        if (this.deckAmount > 0) {
            this.cardAmount = decks.collect{deck -> deck.getSize()}.sum()
        }
    }


}
