package tranki

import grails.gorm.transactions.Transactional

@Transactional
class LearnerService {

    Learner getLearner(String name) {
        Learner learner = Learner.findByName(name)
        if (!learner) {
            println "no esta el learner"
            learner = createLearner(name)
        }

        learner
    }

    // primero create y despues el get en el start de learner
    Learner createLearner(String name) {
        Learner learner = new Learner(name)
        // aca podria ocurrir un error si no se puede crear.
        learner.save()
        return learner
    }

    Learner getLearnerById(int id) {
        return Learner.get(id)
    }

    Stats getStats(int id) {
        Learner learner = getLearnerById(id)

        return learner.getStats()
    }
}
