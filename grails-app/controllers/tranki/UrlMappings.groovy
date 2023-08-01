package tranki

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/learner/back/$id?"(controller: 'learner', action: 'back')

        "/deck/newDeck/$id?"(controller: 'deck', action: 'newDeck')
        "/deck/removeDeck/$deckId"(controller: 'deck', action: 'removeDeck')

        "/deck/newCard/$deckId"(controller: 'deck', action: 'newCard')
        "/deck/removeCard/$cardId"(controller: 'deck', action: 'removeCard')

        "/deck/showCards/$deckId"(controller: 'deck', action: 'showCards')
        "/deck/showBack/$cardId"(controller: 'deck', action: 'showBack')

        "/deck/show/$deckId"(controller: 'deck', action: 'show')
        "/deck/changeCardDifficulty/$cardId/$difficulty"(controller: 'deck', action: 'changeCardDifficulty')

        "/"(view: '/learner/index')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
