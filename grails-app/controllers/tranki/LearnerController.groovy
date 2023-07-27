package tranki

// import grails.gorm.transactions.*

// @Transactional
class LearnerController {

    def learnerService

    // poner el service del learner

    def index() { 
        println "estoy en learner"
        // NO (esto lo hace el service que llamo aca): aca manejo si esta el learner con ese nombre y sino lo creo como nuevo.
        // puedo hacer un redirect y pasarle el learner para listar los mazos
        // y luego en la vista de en la que muestro los mazos, al elegir uno me cambio
        // con el URI= a el controller del mazo...
        // render "home/index"

        // Con redirect me puedo cambiar de método dentro del mismo controler.
        // con action= en una vista puedo llamar a un metodo del controller de esa vista
        // y con uri me redirijo a una URL y uso la def de url mappings para saber qué controller la utiliza y que metodo. 
    
        // Se pueden tener arrays en el deck que tengan los ids de las cartas especiales que ya salieron para no agarrar
        // como siguente carta especial a las que esten ahi y cuando el array tiene un tamaño igual a la cantidad de cartas especiales, se resetea.
        // Esto se hace en el mazo y puede haber tests para testear que pase.
        // De esta forma enriquecemos un poco las clases de dominio.

        // Abajo de todos los mazos se puede poner un boton para agregar uno nuevo y al lado
        // de cada mazo se puede poner un boton para agregar una carta al mismo. Se pone un boton por 
        // cada mazo asi ya se a cual le tengo que agregar sin que el usuario tenga que hacer un ingreso extra 
        // mas que tocar el boton y poner los datos de la carta.

        // Tener otro botón al lado de cada mazo que permita ver todas las cartas
        // y un botón al lado de cada una que te permita borrarlas.
    }

    def start() {
        println "//////// start ////////"
        // println params.learnerName

        // Learner learner = new Learner("Lauty")
        // Deck deck = learner.createDeck("MazoLauty")
        // Card card1 = learner.createCard("Hello", "Hola", deck)
        // Card card2 = learner.createCard("Bye", "Chau", deck)
        // learner.save()
        Learner learner = learnerService.getLearner(params.learnerName)
        println learner.name
        println learner.decks
        redirect action: "showLearner", params: [id: learner.id]
        // Learner learner = Learner.findByName("Lauty")
        // println learner.decks[0].cards[0].front
        // println learner.decks[0].cards[1].front

        // render "ok"
        // Learner learner = Learner.get()
        // aca traigo al learner y si no esta el service crea uno y me lo da. No valida el controller si esta
        // o no. Eso lo hace el service.
    }

    def showLearner() {
        println "//////// showLearner ////////\n\n\n\n\n\n\n"
        // println params
        // capaz puedo usar un repository aca
        Learner learner =  learnerService.getLearnerById(params.id.toInteger())
        Stats stats = learnerService.getStats(params.id.toInteger())
        println learner.level
        println learner.decks
        render(view: "/learner/show", model: [learner: learner, stats: stats])
    }

    def back() {
        println "//////// back ////////"
        redirect action: "showLearner", params: [id: params.id]
    }

}
