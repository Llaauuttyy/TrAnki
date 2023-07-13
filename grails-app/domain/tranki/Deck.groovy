package tranki

class Deck {

    String name
    List<Card> easyCards
    List<Card> normalCards
    List<Card> hardCards

    // List<Card> cards
    // List<Card> cardsSlid

    int cardsSlidCounter = 0

    static constraints = {
    }

    Deck(String name) {
        this.name = name

        // Dejar un solo array y cuando se pasa una carta va a otro 
        // array (array de cartas pasadas) y luego cuando se quedan sin cartas de un cierto tipo,
        // se vuelven a agregar todas las de ese tipo al array.

        this.easyCards = []
        this.normalCards = []
        this.hardCards = []

        this.cardsSlidCounter = 0
    }

    def addCard(Card card) {
        normalCards.add(card)
    }

    boolean isEmpty() {
        getSize() == 0
    }

    int getSize() {
        // cards.size() + cardsSlid.size()
        easyCards.size() + normalCards.size() + hardCards.size()
    }

    def changeCardDifficulty(Card card, Difficulty difficulty) {
        // cambio de normal a easy y de easy a normal
        if (card.isDifficulty(Difficulty.NORMAL)) {
            card.changeDifficulty(difficulty)
            normalCards.remove(card)
            easyCards.add(card)
        }
        else {
            card.changeDifficulty(difficulty)
            easyCards.remove(card)
            normalCards.add(card)
        }
    }

}
