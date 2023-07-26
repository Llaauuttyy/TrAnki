package tranki

enum Level {
    NOOB(5, 10, 0),
    INTERMEDIATE(10, 10, 10),
    ADVANCED(20, 20, 30)

    int decks
    int cards
    int cardsRequired

    Level(int decks, int cards, int cardsRequired) {
        this.decks = decks
        this.cards = cards
        this.cardsRequired = cardsRequired
    }
}

class Learner {

    String name
    // Stats stats
    Level level
    List<Deck> decks

    static constraints = {
    }

    static hasMany = [
        decks: Deck
    ]

    Learner(String name) {
        // Validar que los parámetros estén como en la constraints.
        
        this.name = name
        this.decks = []
        this.level = Level.NOOB
    }

    Deck createDeck(String name) {
        if (decks.size() < this.level.decks) {
            Deck newDeck = new Deck(name, this)
            addDeck(newDeck)

            newDeck
        }
        else {
            // no se puede crear el mazo.
            // tirar error
            println "No se puede crear el mazo"
        }
    }

    Card createCard(String front, String back, Deck deck) {
        if (deck.getSize() < this.level.cards) {
            Card newCard = new Card(front, back, deck)

            // check update level
            deck.addCard(newCard)
            updateLevel()

            newCard
        }
        else {
            // no se puede crear la carta.
            // tirar error
            // puedo devolver null para agarrarlo en el service.
            println "No se puede crear la card"
        }
    }

    def addDeck(Deck deck) {
        decks.add(deck)
    }

    boolean isLevel(Level level) {
        this.level == level
    }

    def setLevel(Level level) {
        this.level = level
    }

    def updateLevel() {
        int cardsAmount = 0
        for(int i = 0; i < decks.size(); i++) {
            cardsAmount += decks[i].getSize()
        }

        println "Total cards: " + cardsAmount
        // println cardsAmount == Level.values()[this.level.ordinal() + 1].cardsRequired

        if (this.level != Level.ADVANCED && cardsAmount == Level.values()[this.level.ordinal() + 1].cardsRequired) {
            this.level = Level.values()[this.level.ordinal() + 1]
            println "Cambio de nivel!"
        }
    }

    boolean changeCardDifficulty(Deck deck, Card card, Difficulty difficulty) {
        if (this.level != Level.NOOB) {
            deck.changeCardDifficulty(card, difficulty)
            return true
        }
        else {
            println "No se puede cambiar la dificultad de la carta"
            return false
        }
    }
}
