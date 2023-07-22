package tranki

class Deck {

    Learner learner
    String name
    // List<Card> easyCards
    // List<Card> normalCards
    // List<Card> hardCards

    List<Card> cards
    int cardsSlidCounter

    static constraints = {
    }

    static belongsTo = [
        learner: Learner
    ]

    static hasMany = [
        cards: Card
    ]

    Deck(String name, Learner learner) {
        // Validar que los parámetros estén como en la constraints.

        this.name = name
        this.learner = learner

        // Dejar un solo array y cuando se pasa una carta va a otro 
        // array (array de cartas pasadas) y luego cuando se quedan sin cartas de un cierto tipo,
        // se vuelven a agregar todas las de ese tipo al array.
        // Un array vendría directo de la base. Usando hasMany.

        // Estos traen error de duplicated column.
        // this.easyCards = []
        // this.normalCards = []
        // this.hardCards = []

        this.cards = []
        this.cardsSlidCounter = 0
    }

    def addCard(Card card) {
        this.cards.add(card)
    }

    boolean isEmpty() {
        this.getSize() == 0
    }

    int getSize() {
        // cards.size() + cardsSlid.size()
        // easyCards.size() + normalCards.size() + hardCards.size()
        this.cards.size()
    }

    def changeCardDifficulty(Card card, Difficulty difficulty) {
        // cambio de normal a easy y de easy a normal
        card.changeDifficulty(difficulty)
        // if (card.isDifficulty(Difficulty.NORMAL)) {
        //     card.changeDifficulty(difficulty)
        //     normalCards.remove(card)
        //     easyCards.add(card)
        // }
        // else {
        //     card.changeDifficulty(difficulty)
        //     easyCards.remove(card)
        //     normalCards.add(card)
        // }
    }

    Card getNextCard() {
        if (this.cards.size() == 0) {
            return null
        }

        println "INICIO"
        println cardsSlidCounter

        Card card = this.cards[this.cardsSlidCounter]
        this.cardsSlidCounter += 1

        if (this.cardsSlidCounter == this.cards.size()) {
            this.cardsSlidCounter = 0
        }
        println cardsSlidCounter
        return card
    }


    // aca tiene que estar la logica de pasar las cartas, calcular puntos, etc.
    // asi dejar de ser una clase anémica.
}
