package tranki

import grails.gorm.transactions.Transactional

@Transactional
class DeckService {

    def cardService

    def getDeck(Long deckId) {
        Deck deck = Deck.get(deckId)
        println deck.name

        return deck
    }
    
    // le dejo create
    // void si no devuelve nada
    boolean createDeck(int learnerId, String newDeckName) {
        if (!Deck.findByName(newDeckName)) {
            Learner learner = Learner.get(learnerId)
            Deck deck = learner.createDeck(newDeckName)
            return true
        }

        println "A deck with this name is already created!"
        return false
    }

    boolean removeDeck(int deckId) {
        Deck deck = Deck.get(deckId)
        if (deck) {
            // Aca debería eliminar todas las cartas asociadas al mazo.
            Card.deleteAll(deck.cards)
            deck.delete()
            println "Deck has been deleted!"
            return true
        }

        println "There's no deck by that name"
        return false
    }

    Card getCard(Long deckId) {
        Deck deck = Deck.get(deckId)
        // Card card = deck.getNextCard()
        // // println deck.cardsSlidCounter
        // if (card) {
        //     return card
        // }

        // // deck.cardsSlidCounter = 0

        // println "Deck is empty"
        // return null

        // Esto debería ser el método getNextCard de deck, pero no se guarda
        // el cambio del atributo

        if (deck.cards.size() == 0) {
            println "there's no cards!!!"
            // tirar error excepcion en la clase de dominio va esto
            return null
        }

        println "INICIO"
        println deck.cardsSlid

        deck.cardsSlid += 1

        Card nextCard

        Set<Card> easyCards = deck.cards.findAll{card -> card.difficulty == Difficulty.EASY}
        Set<Card> normalCards = deck.cards.findAll{card -> card.difficulty == Difficulty.NORMAL}
        Set<Card> hardCards = deck.cards.findAll{card -> card.difficulty == Difficulty.HARD}
        // agregar condicion en caso de q todas las cartas sean easy. Seria q el array de normales es 0 y el cards.size es mayor a 0.
        if ((deck.cardsSlid % 5 == 0 && !normalCards.isEmpty())) {
            int randomCardPosNormal = new Random().nextInt(normalCards.size())
            // nextCard = normalCards[deck.deckPos]
            // deck.deckPos += 1
            println "normal"
            nextCard = normalCards[randomCardPosNormal]

            // if (deck.deckPos == normalCards.size()) {
            //     deck.deckPos = 0
            // }
        }

        else if ((deck.cardsSlid % 3 == 0 && !easyCards.isEmpty())) { 
            int randomCardPos = new Random().nextInt(easyCards.size())

            println "easy"
            nextCard = easyCards[randomCardPos]
        }

        else if (!hardCards.isEmpty()) {
            int randomCardPosHard = new Random().nextInt(hardCards.size())
            // println "Random card pos: " + randomCardPosHard
            println "hard"
            nextCard = hardCards[randomCardPosHard]
        }

        else {
            List<Card> mergedCards = []
            mergedCards.addAll(easyCards)
            mergedCards.addAll(normalCards)

            int randomCardPosMerged = new Random().nextInt(mergedCards.size())
            // println "Random card pos: " + randomCardPosHard

            println "merged"
            nextCard = mergedCards[randomCardPosMerged]
        }


        // println deck.deckPos
        println nextCard
        return nextCard
    }

    boolean changeCardDifficulty(int learnerId, int deckId, int cardId, Difficulty difficulty) {
        boolean result = false
        
        Learner learner = Learner.get(learnerId)
        Deck deck = Deck.get(deckId)
        Card card = Card.get(cardId)

        // el if no va, si rompe rompe y listo lo agarra el controller.

        // en la condicion del if se cambia la dificultad en el dominio, cuando el learner es Intermediate. Que eso pasa al crear una carta porque esta hardcodeado ahora
        if(learner.changeCardDifficulty(deck, card, difficulty)) {
            // Esto deberia estar solo en la clase de dominio. (Pero no se guarda :( )
            println difficulty
            // deck.name = "saparati"
            // deck.save()
            // card.difficulty = difficulty
            // card.setBack(card.back + "s") // si no cambio esto no sirve
            // card.changeDifficulty(difficulty)

            // aca la cambio de nuevo la dificultad pero desde el service.
            cardService.changeCardDifficulty(deck, card.id.toInteger(), difficulty)
            println "Card difficulty was changed."
            result = true
        }

        return result
    }

}
