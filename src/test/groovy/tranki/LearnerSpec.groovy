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
        Deck deck = learner.createDeck(name: "Mazo1")

        then:"new deck is created empty to be used"
        deck.isEmpty() == true

    }
}
