package tranki

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class DeckSpec extends Specification implements DomainUnitTest<Deck> {

    def setup() {
    }

    def cleanup() {
    }

    void "creation of invalid deck creates an exception"() {
        given:"invalid params for a deck"
        def name = "Mazo"
        def learner = null

        when:"tries to create a deck with those params"
        Deck deck = new Deck(name, learner)

        then:"an exception is thrown"
        Exception exception = thrown(IllegalArgumentException)
        exception.message == "Arguments cant be null or empty"
    }
}
