package tranki

class Deck {

    String name
    List<Card> cards

    static constraints = {
    }

    def addCard(Card card) {
        cards.add(card)
    }

    boolean isEmpty() {
        cards == []
    }

}
