package tranki

import grails.gorm.transactions.Transactional

@Transactional
class DeckService {

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
        println deck.cardsSlidCounter
        Card card = deck.getNextCard()
        // deck.cardsSlidCounter = 0
        // println deck.cardsSlidCounter
        deck.save()

        Deck deck2 = Deck.get(deckId)
        println deck2.cardsSlidCounter

        if (card) {
            return card
        }

        println "Deck is empty"
        return null

    }

}
