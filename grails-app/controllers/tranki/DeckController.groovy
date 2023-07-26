package tranki

// import grails.gorm.transactions.*

// @Transactional
class DeckController {

    def deckService
    def cardService

    def scaffold = Deck

    def index() {
        // assert true == false
        println "H"
        println params.learnerName

        
        // Learner learner = new Learner("Juan12", [])
        // Deck deck = learner.createDeck("Mazo1Juan1")
        // learner.addToDecks(deck)
        // // deck.save()
        // learner.save()

        // Learner learner = new Learner("JuanFINAL2", [])
        // Deck deck = new Deck("MazoJuanFINAL2", learner)
        // // learner.addToDecks(deck)
        // learner.save() // Primero guardo al learner sino no puedo guardar 
        // // el deck porque no sabe que poner en learnerId.
        // deck.save()
        // Para las cards tambien tengo que asociarles el deck en el constructor IMPORTANTE
        
        // Ver lo de que no se guarda automáticamente.
        Learner learner = Learner.get(2)
        println learner.name
        println learner.decks[0].name //[0].name
        // learner.decks[0].name = "Cambiaaabre"
        // println learner.decks[0].name
        render(view: "/deck/index", model: [learnerDecks: learner.decks])

        // Estaria bueno que el usuario ponga su nombre y de ahi de pueda
        // cargas los mazos y cartas con ese nombre y de ahi bueno empezar la app.
    }

    def showCards() {
        println "//////// showCards ////////"
        Card card = deckService.getCard(params.deckId.toInteger())
        if (card) {
            println "Controller"
            println deckService.getDeck(params.deckId.toInteger()).cardsSlid
            render (view: "/deck/showCards", model: [learnerId: params.learnerId, deckId: params.deckId, card: card, difficulty: Difficulty])
        }

        else {
            println "Deck is empty at controller Deck"
            render "Deck is empty"
        }
        // println deck.name
        
        // Aca iria el render donde muestro las cartas
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
        // deckService.createDeck(params.id)
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
        // deckService.createDeck(params.id)
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
