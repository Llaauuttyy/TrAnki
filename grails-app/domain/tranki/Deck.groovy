package tranki

class Deck {

    static final int MAX_CARDS_TO_EASY = 3
    static final int MAX_CARDS_TO_NORMAL = 5

    Learner learner
    String name
    int cardsSlid

    Set<Card> cards

    static constraints = {
        learner(nullable: false)
        name(nullable: false, blank: false)
        cardsSlid(min: 0)
    }

    static belongsTo = Learner

    static hasMany = [
        cards: Card
    ]

    Deck(String name, Learner learner) { 
        if (!learner || !name || name.trim().isEmpty()) throw new IllegalArgumentException("Arguments cant be null or empty")

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

    void changeCardDifficulty(Card card, Difficulty difficulty) {
        card.changeDifficulty(difficulty)
    }

    Card slideCard() {
        if (this.cards.size() == 0) {
            throw new RuntimeException("There's no cards on this deck")
        }

        this.setCardsSlid(this.cardsSlid + 1)

        println this.cardsSlid
        Card nextCard

        Set<Card> easyCards = this.cards.findAll{card -> card.difficulty == Difficulty.EASY}
        Set<Card> normalCards = this.cards.findAll{card -> card.difficulty == Difficulty.NORMAL}
        Set<Card> hardCards = this.cards.findAll{card -> card.difficulty == Difficulty.HARD}

        if ((this.cardsSlid % MAX_CARDS_TO_NORMAL == 0 && !normalCards.isEmpty())) {
            nextCard = this.getNextCard(normalCards)
            println "normal"
        }

        else if ((this.cardsSlid % MAX_CARDS_TO_EASY == 0 && !easyCards.isEmpty())) { 
            nextCard = this.getNextCard(easyCards)
            println "easy"
        }

        else if (!hardCards.isEmpty()) {
            nextCard = this.getNextCard(hardCards)
            println "hard"
        }

        else {
            Set<Card> mergedCards = (easyCards + normalCards) as Set
            nextCard = this.getNextCard(mergedCards)
            println "merged"
        }

        return nextCard
    }

    Card getNextCard(Set<Card> cards) {
        return cards[new Random().nextInt(cards.size())]
    }
}
