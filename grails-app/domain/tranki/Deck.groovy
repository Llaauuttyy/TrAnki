package tranki

class Deck {

    // tirar excepciones en clase de dominio, en el service no hacer nada y en el controller try catch.
    // cambiar url accediendo a las clases rompiendo encap.
    // no hacer getAttributo en la clase porque grails ya lo tiene y sino se bugguea.
    
    Learner learner
    String name
    int cardsSlid

    Set<Card> cards

    // static constraints = {
    //     learner(nullable: false)
    //     name(nullable: false, blank: false)
    //     cardsSlid(min: 0)
    // }

    static belongsTo = Learner

    static hasMany = [
        cards: Card
    ]

    Deck(String name, Learner learner) {
        // if (!learner)
        // if (!name || name.trim().isEmpty()) 
        // Validar que los parámetros estén como en la constraints.
        // name and learner cant be null.

        this.name = name
        this.learner = learner

        this.cards = []
        this.cardsSlid = 0
    }

    def addCard(Card card) {
        this.cards.add(card)
    }

    boolean isEmpty() {
        this.getSize() == 0
    }

    int getSize() {
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

//     List<Card> getCards() {
//         this.cards
// }


    // aca tiene que estar la logica de pasar las cartas, calcular puntos, etc.
    // asi dejar de ser una clase anémica.
}
