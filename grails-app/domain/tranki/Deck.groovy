package tranki

class Deck {

    Learner learner
    String name

    List<Card> cards
    int deckPos

    int cardsSlid

    static constraints = {
    }

    static belongsTo = Learner

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
        this.deckPos = 0
        this.cardsSlid = 0
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
        // migrado al service.
        if (this.cards.size() == 0) {
            return null
        }

        println "INICIO"
        println deckPos

        Card card = this.cards[this.deckPos]
        this.deckPos += 1

        if (this.deckPos == this.cards.size()) {
            this.deckPos = 0
        }
        println deckPos
        return card
    }

    List<Card> getCards() {
        this.cards
    }


    // aca tiene que estar la logica de pasar las cartas, calcular puntos, etc.
    // asi dejar de ser una clase anémica.
}
