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

    Card getNextCard() {
        if (this.cards.size() == 0) {
            throw new RuntimeException("There's no cards on this deck")
        }

        this.setCardsSlid(this.cardsSlid + 1)

        Card nextCard

        Set<Card> easyCards = this.cards.findAll{card -> card.difficulty == Difficulty.EASY}
        Set<Card> normalCards = this.cards.findAll{card -> card.difficulty == Difficulty.NORMAL}
        Set<Card> hardCards = this.cards.findAll{card -> card.difficulty == Difficulty.HARD}

        if ((this.cardsSlid % MAX_CARDS_TO_NORMAL == 0 && !normalCards.isEmpty())) {
            int randomCardPosNormal = new Random().nextInt(normalCards.size())
            println "normal"
            nextCard = normalCards[randomCardPosNormal]
        }

        else if ((this.cardsSlid % MAX_CARDS_TO_EASY == 0 && !easyCards.isEmpty())) { 
            int randomCardPos = new Random().nextInt(easyCards.size())

            println "easy"
            nextCard = easyCards[randomCardPos]
        }

        else if (!hardCards.isEmpty()) {
            int randomCardPosHard = new Random().nextInt(hardCards.size())
            println "hard"
            nextCard = hardCards[randomCardPosHard]
        }

        else {
            List<Card> mergedCards = []
            mergedCards.addAll(easyCards)
            mergedCards.addAll(normalCards)

            int randomCardPosMerged = new Random().nextInt(mergedCards.size())

            println "merged"
            nextCard = mergedCards[randomCardPosMerged]
        }

        return nextCard
    }
}
