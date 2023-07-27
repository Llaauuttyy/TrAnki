package tranki

class Stats {
    int maxPoints
    int currentPoints
    int deckAmount
    int cardAmount

    static constraints = {
    }

    def getLearnerMaxPoints(List<Deck> decks) {
        this.maxPoints = 0

        if (!decks.isEmpty()) { // creo que no hace falta porq si el array es vacio no hace nada
            for (deck in decks) {
                this.maxPoints += deck.getSize() * Difficulty.EASY.points
            }
        }
    }

    // cambiar el actual a current en el diagrama de clases.
    def getLearnerCurrentPoints(List<Deck> decks) {
        this.currentPoints = 0

        if (!decks.isEmpty()) {
            for (deck in decks) {
                for (card in deck.getCards()) {
                    this.currentPoints += card.getPoints()
                }
            }
        }

    }

    def getLearnerDeckAmount(List<Deck> decks) {
        this.deckAmount = decks.size()

    }

    def getLearnerCardAmount(List<Deck> decks) {
        this.cardAmount = 0

        if (!decks.isEmpty()) {
            for (deck in decks) {
                this.cardAmount += deck.getSize()
            }
        }
    }


}
