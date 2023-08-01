package tranki

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CardSpec extends Specification implements DomainUnitTest<Card> {

    def setup() {
    }

    def cleanup() {
    }

    void "creation of invalid card creates an exception"() {
        given:"invalid params for a card"
        def front = " "
        def back = "Hola"
        def deck = null

        when:"tries to create a card with those params"
        Card card = new Card(front, back, deck)

        then:"an exception is thrown"
        Exception exception = thrown(IllegalArgumentException)
        exception.message == "Arguments cant be null or empty"
    }
}
