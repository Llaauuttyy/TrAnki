package tranki

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/learner/back/id?"(controller: 'learner', action: 'back')

        "/deck/index"(controller: 'deck', action: 'index')
        "/deck/index/$id?"(controller: 'deck', action: 'prueba')

        "/deck/newDeck/id?"(controller: 'deck', action: 'newDeck')
        "/deck/removeDeck/$learnerId/$deckId"(controller: 'deck', action: 'removeDeck')

        "/deck/newCard/$learnerId/$deckId"(controller: 'deck', action: 'newCard')

        "/deck/showCards/$deckId"(controller: 'deck', action: 'showCards')

        // "/"(controller: 'learner', action: 'index')
        "/"(view: '/learner/index')
        // "/"(view:"/index")
        // "/"(view:"/deck/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
