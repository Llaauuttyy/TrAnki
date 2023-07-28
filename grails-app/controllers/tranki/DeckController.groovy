package tranki

// import grails.gorm.transactions.*

// @Transactional
class DeckController {

    def deckService
    def cardService

    def scaffold = Deck

    def index() {
        println params.learnerName

        
        
        // Ver lo de que no se guarda automáticamente.
        Learner learner = Learner.get(2)
        println learner.name
        render(view: "/deck/index", model: [learnerDecks: learner.decks])

        // Estaria bueno que el usuario ponga su nombre y de ahi de pueda
        // cargas los mazos y cartas con ese nombre y de ahi bueno empezar la app.
    }

    def showCards(Long deckId, Long learnerId) {
        println "//////// showCards ////////"
        Card card = deckService.getCard(deckId)
        if (card) {
            println "Controller"
            println deckService.getDeck(deckId).cardsSlid
            // render (view: "/deck/showCards", model: [learnerId: params.learnerId, deckId: params.deckId, card: card, difficulty: Difficulty])
            render (view: "/deck/showCards", model: [learnerId: learnerId, deckId: deckId, card: card, difficulty: Difficulty])

        }

        else {
            println "Deck is empty at controller Deck"
            render "Deck is empty"
        }
        
        // en vez de pasar el id del mazo puedo usar card.getDeckId()
    }

    def showBack() {
        println "//////// showBack ////////"

        Card card = cardService.getCard(params.cardId.toInteger())

        render (view: "/deck/showBack", model: [learnerId: params.learnerId, deckId: params.deckId, card: card, difficulty: Difficulty])
    }

    def changeCardDifficulty() {
        println "//////// changeCardDifficulty ////////"
        println params
        Card card = cardService.getCard(params.cardId.toInteger())
        if (deckService.changeCardDifficulty(params.learnerId.toInteger(), params.deckId.toInteger(), params.cardId.toInteger(), Difficulty.valueOf(params.difficulty))) {
            render(view: "/deck/showCards", model: [learnerId: params.learnerId, deckId: params.deckId, card: card, difficulty: Difficulty])
        }

        else {
            render "card difficulty change failed"

        }

    }

    def newDeck() {
        println "//////// newDeck ////////"
        render(view: "/deck/addDeck", model: [learnerId: params.id])
    }

    def addDeck() {
        println "//////// addDeck ////////"
        if(deckService.createDeck(params.learnerId.toInteger(), params.newDeckName)) {
            render(view: "/deck/success", model: [learnerId: params.learnerId])
        }

        else {
            render "failed"
        }
    }

    def removeDeck() {
        println "//////// removeDeck ////////"
        if(deckService.removeDeck(params.deckId.toInteger())) {
            render(view: "/deck/success", model: [learnerId: params.learnerId])
        }

        else {
            render "failed"
        }
    }

    def newCard() {
        println "//////// newCard ////////"
        println params
        render(view: "/deck/addCard", model: [learnerId: params.learnerId, deckId: params.deckId])
    }

    def addCard() {
        println "//////// addCard ////////"
        if(cardService.createCard(params.learnerId.toInteger(), params.deckId.toInteger(), params.newCardFront, params.newCardBack)) {
            render(view: "/deck/success", model: [learnerId: params.learnerId])
        }

        else {
            render "failed"
        }
    }
}
