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
    // int MAX_DECKS = 5 BORRAR ya que uso el de Level.

    String name
    List<Deck> decks
    // Stats stats
    Level level

    static constraints = {
    }

    Learner(String name, List<Deck> decks) {
        this.name = name
        this.decks = decks
        this.level = Level.NOOB
    }

    Deck createDeck(String name) {
        if (decks.size() < this.level.decks) {
            Deck newDeck = new Deck(name)
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
            Card newCard = new Card(front, back)

            // check update level
            deck.addCard(newCard)
            updateLevel()

            newCard
        }
        else {
            // no se puede crear la carta.
            // tirar error
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

        // println "Total cards: " + cardsAmount

        if (this.level != Level.ADVANCED && cardsAmount == Level.values()[this.level.ordinal() + 1].cardsRequired) {
            this.level = Level.values()[this.level.ordinal() + 1]
            // println "Cambio de nivel!"
        }
    }

    def changeCardDifficulty(Deck deck, Card card, Difficulty difficulty) {
        if (this.level != Level.NOOB) {
            deck.changeCardDifficulty(card, difficulty)
        }
        else {
            println "No se puede cambiar la dificultad de la carta"
        }
    }
}
