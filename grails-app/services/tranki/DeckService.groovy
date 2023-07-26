package tranki

import grails.gorm.transactions.Transactional

@Transactional
class DeckService {

    def cardService = new CardService()

    def getDeck(int deckId) {
        Deck deck = Deck.get(deckId)
        println deck.name

        return deck
    }

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

    @Transactional
    Card getCard(int deckId) {
        Deck deck = Deck.get(deckId)
        // Card card = deck.getNextCard()
        // // println deck.cardsSlidCounter
        // if (card) {
        //     return card
        // }

        // // deck.cardsSlidCounter =0

        // println "Deck is empty"
        // return null

        // Esto debería ser el método getNextCard de deck, pero no se guarda
        // el cambio del atributo

        if (deck.cards.size() == 0) {
            println "there's no cards!!!"
            return null
        }

        println "INICIO"
        println deck.deckPos

        deck.cardsSlid += 1

        Card nextCard

        List<Card> easyCards = deck.cards.findAll{card -> card.difficulty == Difficulty.EASY}
        // agregar condicion en caso de q todas las cartas sean easy. Seria q el array de normales es 0 y el cards.size es mayor a 0.
        if (deck.cardsSlid % 3 == 0 && !easyCards.isEmpty()) { 
            int randomCardPos = new Random().nextInt(easyCards.size())
            println "Random card pos: " + randomCardPos

            nextCard = easyCards[randomCardPos]
        }
        else {
            List<Card> normalCards = deck.cards.findAll{card -> card.difficulty == Difficulty.NORMAL}
            int randomCardPosNormal = new Random().nextInt(normalCards.size())
            // nextCard = normalCards[deck.deckPos]
            deck.deckPos += 1

            nextCard = normalCards[randomCardPosNormal]

            if (deck.deckPos == normalCards.size()) {
                deck.deckPos = 0
            }
        }


        // println deck.deckPos
        println nextCard
        return nextCard
    }

    @Transactional
    boolean changeCardDifficulty(int learnerId, int deckId, int cardId, Difficulty difficulty) {
        boolean result = false
        
        Learner learner = Learner.get(learnerId)
        Deck deck = Deck.get(deckId)
        Card card = Card.get(cardId)
        // deck.cardsSlid += 100
        // aca funciono
        // deck.setName(deck.name)

        if(learner.changeCardDifficulty(deck, card, difficulty)) {
            // Esto deberia estar solo en la clase de dominio. (Pero no se guarda :( )
            println difficulty
            // deck.name = "saparati"
            // deck.save()
            // card.difficulty = Difficulty.EASY
            card.setDifficulty(difficulty)
            card.setBack(card.back + "s") // si no cambio esto no sirve
            cardService.changeCardDifficulty(deck, card.id.toInteger(), difficulty)
            println "Card difficulty was changed."
            result = true
        }

        return result
    }

}
