package tranki

import grails.gorm.transactions.Transactional

@Transactional
class DeckService {

    Deck getDeck(int deckId) {
        Deck deck = Deck.get(deckId)
        if (!deck) {
            throw new RuntimeException("There is no deck by that ID.")
        }

        return deck
    }
    
    void create(int learnerId, String newDeckName) {
        if (Deck.findByName(newDeckName)) {
            throw new RuntimeException("A deck with this name is already created.")
        }

        Learner learner = Learner.get(learnerId)
        learner.createDeck(newDeckName)
    }

    void remove(int deckId) {
        Deck deck = getDeck(deckId)

        Card.deleteAll(deck.cards)
        deck.delete()
    }

    Card getNextCard(int deckId) {
        Deck deck = getDeck(deckId)

        return deck.getNextCard()
    }

    void changeCardDifficulty(int learnerId, int deckId, int cardId, Difficulty difficulty) {
        Learner learner = Learner.get(learnerId)
        Deck deck = getDeck(deckId)
        Card card = Card.get(cardId)

        learner.changeCardDifficulty(deck, card, difficulty)
    }

    long getLearnerId(int deckId) {
        Deck deck = getDeck(deckId)

        return deck.learner.id
    }

}
