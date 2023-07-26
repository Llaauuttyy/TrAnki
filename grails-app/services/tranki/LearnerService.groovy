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

    Learner createLearner(String name) {
        Learner learner = new Learner(name)
        // aca podria ocurrir un error si no se puede crear.
        learner.save()
        return learner
    }

    Learner getLearnerById(int id) {
        return Learner.get(id)
    }
}