package tranki

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class LearnerSpec extends Specification implements DomainUnitTest<Learner> {

    def setup() {
    }

    def cleanup() {
    }

    void "learner can create deck when decks amount is less than max allowed per level"() {
        given:"decks amount is less than max allowed per level"
        // Si usamos el constructor por defecto.
        // Learner learner = new Learner(name: "Juan", decks: [])
        Learner learner = new Learner("Juan", [])

        when:"learner tries to create a new deck"
        Deck deck = learner.createDeck("Mazo1")

        then:"new deck is created empty to be used"
        deck.isEmpty()
    }

    void "learner can create a card when having at least a deck created"() {
        given:'''decks amount is at least one, 
        cards amount is less than max allowed per level, 
        learner needs only a card to get to intermediate level'''
        Learner learner = new Learner("Rober", [])
        Deck deck = learner.createDeck("Mazo1")

        for(int i = 0; i < Level.INTERMEDIATE.cardsRequired - 1; i++) {
            Card card = learner.createCard("Hello", "Hola", deck)
        }

        when:"learner tries to create a new card"
        Card newCard = learner.createCard("Hello", "Hola", deck)

        then:"new card is created and learner level is updated"
        newCard && (learner.isLevel(Level.INTERMEDIATE))
    }

    void "learner can change card difficulty if level is not noob"() {
        given:"decks and cards amount is at least one and level is other than noob"
        Learner learner = new Learner("Pepe", [])

        Deck deck = learner.createDeck("Mazo1")
        Card card = learner.createCard("Hello", "Hola", deck)

        learner.setLevel(Level.INTERMEDIATE)

        when:"tries to change difficulty to a card"
        learner.changeCardDifficulty(deck, card, Difficulty.EASY)

        then: "card difficulty is changed and card points are updated"
        card.getPoints() == Difficulty.EASY.points
    }

    // void "testing deleting"() {
    //     given: "asasd"
    //     Learner learner = new Learner("Pepe", [])
    //     Deck deck = learner.createDeck("Mazo1")
    //     Card card = learner.createCard("Hello", "Hola", deck)
    //     println deck.normalCards.size()
    //     learner.setLevel(Level.INTERMEDIATE)
    //     learner.changeCardDifficulty(deck, card, Difficulty.EASY)

    //     println deck.normalCards.size()
    //     println deck.easyCards.size()

    //     when: "Asdasd"

    //     then: "asasd"
    //     true
    // }
}
