package tranki

import grails.gorm.transactions.Transactional

@Transactional
class LearnerService {

    Learner getLearner(int id) {
        Learner learner = Learner.get(id)
        if (!learner) {
            throw new RuntimeException("There is no learner by that ID.")
        }

        learner
    }

    Learner createLearner(String name) {
        Learner learner = Learner.findByName(name)
        if (!learner) {
            println "no esta el learner"
            learner = new Learner(name)
            learner.save()
        }

        return learner
    }

    Stats getStats(int id) {
        Learner learner = getLearner(id)

        return learner.getStats()
    }
}
