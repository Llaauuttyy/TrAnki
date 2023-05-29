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
            Deck newDeck = new Deck(name, [])
            addDeck(newDeck)
        }
        // else {
        //     // no se puede crear el mazo.
        // }
    }

    Card createCard(String front, String back, Deck deck) {
        // cards at first are set to NORMAL difficulty.
        Card newCard = new Card(front, back, 5, Difficulty.NORMAL)
        deck.addCard(newCard)
        newCard
    }

    def addDeck(Deck deck) {
        decks.add(deck)
    }
}
