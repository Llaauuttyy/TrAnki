package tranki

class Learner {
    int MAX_DECKS = 5

    String name
    List<Deck> decks
    // Stats stats

    static constraints = {
    }

    Deck createDeck(String name) {
        if (decks.size() < MAX_DECKS) {
            Deck newDeck = new Deck(name: name, cards: [])
            addDeck(newDeck)
    
            newDeck
        }
        // else {
            // no se puede crear el mazo.
            // tirar error
        // }
    }

    Card createCard(String front, String back, Deck deck) {
        Card newCard = new Card(front, back)
        deck.addCard(newCard)
        newCard
    }

    def addDeck(Deck deck) {
        decks.add(deck)
    }
}
