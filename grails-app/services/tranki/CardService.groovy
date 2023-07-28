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
            
            // SACAR ESTO
            println deck.getSize()
            // Lo dejo aca de esta forma para probar la funcionalidad
            // de aprendizaje por repeticion espaciada,
            // luego tendria que poner la logica, que preferentemente deberia
            // estar en la clase de dominio.
            learner.level = Level.INTERMEDIATE
            learner.save()

            return true
        }

        return false
    }

    Card getCard(int cardId) {
        Card card = Card.get(cardId)
        card
    }

    def changeCardDifficulty(Deck deck, int cardId, Difficulty difficulty) {
        Card card = getCard(cardId)
        // card.difficulty = Difficulty.EASY
        card.changeDifficulty(difficulty)
        // card.save(flush: true)

        // aca lo cambia, pero en el service deck, no.
        // deck.name = "sabatinelas"
        // deck.save()
    }
}
