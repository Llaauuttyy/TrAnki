package tranki

import grails.gorm.transactions.Transactional

@Transactional
class CardService {

    void create(int learnerId, int deckId, String front, String back) {
        Learner learner = Learner.get(learnerId)
        Deck deck = Deck.get(deckId)

        learner.createCard(front, back, deck)
    }

    Card getCard(int cardId) {
        Card card = Card.get(cardId)
        if (!card) {
            throw new RuntimeException("There is no card by that ID")
        }

        card
    }

    void remove(int cardId) {
        Card card = getCard(cardId)

        card.delete()
    }

    long getDeckId(int cardId) {
        Card card = getCard(cardId)

        return card.deck.id
    }

    long getLearnerId(int cardId) {
        Card card = getCard(cardId)

        return card.deck.learner.id
    }
}
