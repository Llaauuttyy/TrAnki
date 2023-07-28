package tranki

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class StatsSpec extends Specification implements DomainUnitTest<Stats> {

    def setup() {
    }

    def cleanup() {
    }

    void "stats are all zero for a learner with no decks"() {
        given:"a learner with no decks"
        Learner learner = new Learner("Juan")

        when:"stats are calculated for the learner"
        Stats stats = learner.getStats()

        then:"stats numbers are all zero"
        (stats.maxPoints == 0) && (stats.currentPoints == 0) && (stats.deckAmount == 0) && (stats.cardAmount == 0)
    }

    void "stats are not zero for a learnar with decks"() {
        given:"a learner with a deck and a card with hard difficulty"
        Learner learner = new Learner("Juan")
        Deck deck = learner.createDeck("Mazo1")
        Card card = learner.createCard("Hello", "Hola", deck)

        when:"stats are calculated for the learner"
        Stats stats = learner.getStats()

        then:"stats numbers must be 20 for max points, 10 for current points, 1 for deck amount, 1 for card amount"
        (stats.maxPoints == 20) && (stats.currentPoints == 10) && (stats.deckAmount == 1) && (stats.cardAmount == 1)
    }
}
