package tranki

class InvalidActionException extends Exception {
    InvalidActionException(String message) {
        super(message)
    }
}

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
    Stats stats
    Level level

    Set<Deck> decks

    static constraints = {
        name(nullable: false, blank: false)
        stats(nullable: false)
        level(nullable: false)
    }

    static hasMany = [
        decks: Deck
    ]

    Learner(String name) {
        if (!name || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Arguments cant be null or empty")
        }

        this.name = name
        this.decks = []
        this.level = Level.NOOB
        this.stats = new Stats()
    }

    Deck createDeck(String name) {
        if (decks.size() < this.level.decks) {
            Deck newDeck = new Deck(name, this)
            addDeck(newDeck)

            newDeck
        }
        else {
            throw new InvalidActionException("Deck cannot be created due max deck amount has been reached for this level.")
        }
    }

    Card createCard(String front, String back, Deck deck) {
        if (deck.getSize() < this.level.cards) {
            Card newCard = new Card(front, back, deck)

            deck.addCard(newCard)
            updateLevel()

            newCard
        }
        else {
            throw new InvalidActionException("Card cannot be created due max card amount has been reached for this deck.")
        }
    }

    void addDeck(Deck deck) {
        decks.add(deck)
    }

    boolean isLevel(Level level) {
        this.level == level
    }

    void setLevel(Level level) {
        this.level = level
    }

    void updateLevel() {
        int cardsAmount = 0
        for(int i = 0; i < decks.size(); i++) {
            cardsAmount += decks[i].getSize()
        }

        if (this.level != Level.ADVANCED && cardsAmount == Level.values()[this.level.ordinal() + 1].cardsRequired) {
            this.setLevel(Level.values()[this.level.ordinal() + 1])
        }
    }

    void changeCardDifficulty(Deck deck, Card card, Difficulty difficulty) {
        if (this.level != Level.NOOB) {
            deck.changeCardDifficulty(card, difficulty)
        }
        else {
            throw new InvalidActionException("Card difficulty cannot be changed. Available for Intermediate level and higher.")
        }
    }

    Stats getStats() {
        this.stats.getLearnerMaxPoints(this.decks)
        this.stats.getLearnerCurrentPoints(this.decks)
        this.stats.getLearnerDeckAmount(this.decks)
        this.stats.getLearnerCardAmount(this.decks)

        return this.stats
    }
}
