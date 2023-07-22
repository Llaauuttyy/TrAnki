package tranki

import grails.gorm.transactions.Transactional

@Transactional
class CardService {

    boolean createCard(int learnerId, int deckId, String front, String back) {
        Learner learner = Learner.get(learnerId)
        Deck deck = Deck.get(deckId)

        Card card = learner.createCard(front, back, deck)
        if (card) {
            println "Card created succesfully"
            return true
        }

        return false
    }
}
