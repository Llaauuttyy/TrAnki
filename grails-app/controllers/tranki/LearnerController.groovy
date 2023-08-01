package tranki

class LearnerController {

    def learnerService

    def index() { 
    }

    def start() {
        println "//////// start ////////"

        try {
            Learner learner = learnerService.createLearner(params.learnerName)

            println learner.name
            println learner.decks

            redirect action: "showLearner", params: [id: learner.id]
        }

        catch (Exception e) {
            render(view: "/errorDomain", model: [message: "An error has ocurred.\n${e.message}"])
        }


    }

    def showLearner() {
        println "//////// showLearner ////////\n\n\n\n\n\n\n"

        try {
            Learner learner = learnerService.getLearner(params.id.toInteger())
            Stats stats = learnerService.getStats(params.id.toInteger())

            println learner.level
            println learner.decks

            render(view: "/learner/show", model: [learner: learner, stats: stats])
        }

        catch (Exception e) {
            render(view: "/errorDomain", model: [message: "An error has ocurred.\n${e.message}"])

        }
    }

    def back() {
        println "//////// back ////////"
        redirect action: "showLearner", params: [id: params.id]
    }

}
