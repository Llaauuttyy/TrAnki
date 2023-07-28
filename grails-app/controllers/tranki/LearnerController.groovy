package tranki

class LearnerController {

    def learnerService

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

        // Tener otro botón al lado de cada mazo que permita ver todas las cartas
        // y un botón al lado de cada una que te permita borrarlas.
    }

    def start() {
        println "//////// start ////////"

        Learner learner = learnerService.getLearner(params.learnerName)

        println learner.name
        println learner.decks

        redirect action: "showLearner", params: [id: learner.id]
    }

    def showLearner() {
        println "//////// showLearner ////////\n\n\n\n\n\n\n"

        // capaz puedo usar un repository aca
        Learner learner =  learnerService.getLearnerById(params.id.toInteger())
        Stats stats = learnerService.getStats(params.id.toInteger())

        println learner.level
        println learner.decks

        // render "error"

        render(view: "/learner/show", model: [learner: learner, stats: stats])
    }

    def back() {
        println "//////// back ////////"
        redirect action: "showLearner", params: [id: params.id]
    }

}
