package tranki

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class LearnerSpec extends Specification implements DomainUnitTest<Learner> {

    def setup() {
    }

    def cleanup() {
    }

    void "learner can create deck when decks amount is less than max allowed"() {
        given:"decks amount is less than max allowed"
        Learner learner = new Learner(name: "Juan", decks: [])

        when:"learner tries to create a new deck"
        Deck deck = learner.createDeck("Mazo1")

        then:"new deck is created empty to be used"
        deck.isEmpty()

    }

    void "learner can create a card when having at least a deck created"() {
        given:"decks amount is at least one"
        Learner learner = new Learner(name: "Rober", decks: [])
        Deck deck = learner.createDeck("Mazo1")

        when:"learner tries to create a new card"
        Card newCard = learner.createCard("Hello", "Hola", deck)

        then:"new card is created and deck is not empty"
        newCard && !deck.isEmpty()
    }
}
