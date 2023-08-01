package tranki

class DeckController {

    def learnerService
    def deckService
    def cardService

    def scaffold = Deck

    def index() {
    }

    def show() {
        println "//////// show(Deck) ////////"

        try {
            Deck deck = deckService.getDeck(params.deckId.toInteger())
            Set<Card> cards = deckService.getDeck(params.deckId.toInteger()).getCards()
            Learner learner = learnerService.getLearner(deck.getLearner().id.toInteger())

            render(view: "/deck/show", model: [learner: learner, cards: deck.getCards()])
        }

        catch (Exception e) {
            render (view: "/errorDomain", model: [message: "An error has ocurred.\n${e.message}"])
        }
    }

    def showCards(int deckId) {
        println "//////// showCards ////////"

        try {
            Card card = deckService.getNextCard(deckId)
            render (view: "/deck/showCards", model: [deckId: deckId, card: card, difficulty: Difficulty])
        }

        catch (Exception e) {
            render (view: "/errorDomain", model: [message: "An error has ocurred.\n${e.message}"])
        }
    }

    def showBack() {
        println "//////// showBack ////////"

        try {
            Card card = cardService.getCard(params.cardId.toInteger())
            int deckId= cardService.getDeckId(params.cardId.toInteger())
            render (view: "/deck/showBack", model: [deckId: deckId, card: card, difficulty: Difficulty])
        }

        catch (Exception e) {
            render (view: "/errorDomain", model: [message: "An error has ocurred.\n${e.message}"])
        }
    }

    def changeCardDifficulty() {
        println "//////// changeCardDifficulty ////////"

        try {
            Card card = cardService.getCard(params.cardId.toInteger())
            int learnerId = cardService.getLearnerId(params.cardId.toInteger())
            int deckId = cardService.getDeckId(params.cardId.toInteger())

            deckService.changeCardDifficulty(learnerId, deckId, params.cardId.toInteger(), Difficulty.valueOf(params.difficulty))
            render (view: "/deck/showCards", model: [deckId: deckId, card: card, difficulty: Difficulty])
        }

        catch (Exception e) {
            render (view: "/errorDomain", model: [message: "An error has ocurred.\n${e.message}"])
        }
    }

    def newDeck() {
        println "//////// newDeck ////////"
        render (view: "/deck/addDeck", model: [learnerId: params.id])
    }

    def addDeck() {
        println "//////// addDeck ////////"

        try {
            deckService.create(params.learnerId.toInteger(), params.newDeckName)
            render (view: "/deck/success", model: [learnerId: params.learnerId, message: "The deck was added successfully"])
        }

        catch (Exception e) {
            render (view: "/errorDomain", model: [message: "An error has ocurred.\n${e.message}"])
        }
    }

    def removeDeck() {
        println "//////// removeDeck ////////"

        try {
            int learnerId = deckService.getLearnerId(params.deckId.toInteger())
            deckService.remove(params.deckId.toInteger())
            render (view: "/deck/success", model: [learnerId: learnerId, message: "The deck was removed successfully"])
        }

        catch (Exception e) {
            render (view: "/errorDomain", model: [message: "An error has ocurred.\n${e.message}"])
        }
    }

    def newCard() {
        println "//////// newCard ////////"

        render (view: "/deck/addCard", model: [deckId: params.deckId])
    }

    def addCard() {
        println "//////// addCard ////////"

        try {
            int learnerId = deckService.getLearnerId(params.deckId.toInteger())
            cardService.create(learnerId, params.deckId.toInteger(), params.newCardFront, params.newCardBack)
            render (view: "/deck/success", model: [learnerId: learnerId, message: "The card was added successfully"])
        }

        catch (Exception e) {
            render (view: "/errorDomain", model: [message: "An error has ocurred.\n${e.message}"])
        }
    }

    def removeCard(int cardId) {
        println "//////// removeCard ////////"

        try {
            int deckId = cardService.getDeckId(cardId)
            cardService.remove(cardId)

            redirect (action: "show", params: [deckId: deckId])
        }

        catch (Exception e) {
            render (view: "/errorDomain", model: [message: "An error has ocurred.\n${e.message}"])
        }
    }
}
