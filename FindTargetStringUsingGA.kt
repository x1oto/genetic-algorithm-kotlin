import Constants.POPULATION_SIZE
import Constants.TIMES
import Constants.CHARS
import kotlin.random.Random

class GeneticAlgorithm {

    fun run(target: String) {
        var population = List(POPULATION_SIZE) { randomString(target) }
        println("Starting with random population: $population")

        for (i in 0..TIMES) {
            val calculatedFitness = fitness(target, population)
            if (isIndividualSuitable(target, calculatedFitness, i)) break
            val selected = select(calculatedFitness, population)
            val crossed = crossover(selected)
            val mutated = mutation(target, crossed)
            population = mutated
        }
    }

    private fun isIndividualSuitable(target: String, calculatedFitness: List<Int>, i: Int): Boolean {
        if (calculatedFitness.max() != target.length) {
            println("$i. ${calculatedFitness.similarity(target)}")
        } else {
            println("$i. ${calculatedFitness.similarity(target)} <--| Suitable variant has been found! Exit.")
            return true
        }
        return false
    }

    private fun List<Int>.similarity(target: String) =
        "${max()} | Similarity: ${max() / target.length.toDouble() * 100.0}"


    private fun fitness(target: String, population: List<String>): List<Int> {
        return population.map { individual -> target.zip(individual).count { it.first == it.second } }
    }

    private fun select(fitness: List<Int>, population: List<String>): List<String> {
        val bestIndividuals = mutableListOf<String>()

        repeat(POPULATION_SIZE) {
            val bestIndividual = List(size = 3) { fitness.random() }.max()
            val indexInPopulation = fitness.indexOf(bestIndividual)
            bestIndividuals.add(population[indexInPopulation])
        }

        return bestIndividuals
    }

    private fun crossover(selected: List<String>): List<String> {
        val offspring = mutableListOf<String>()

        for (i in selected.indices step 2) {
            if (i + 1 < selected.size) {
                val parent1 = selected[i]
                val parent2 = selected[i + 1]

                val crossoverPoint = Random.nextInt(1, parent1.length)

                val child1 = parent1.take(crossoverPoint) + parent2.drop(crossoverPoint)
                val child2 = parent2.take(crossoverPoint) + parent1.drop(crossoverPoint)

                offspring.add(child1)
                offspring.add(child2)
            }
        }
        return offspring
    }

    private fun mutation(target: String, crossed: List<String>, mutationRate: Double = 0.01): List<String> {
        return crossed.map { individual ->
            individual.mapIndexed { index, char ->
                if (char != target[index] && Random.nextDouble() < mutationRate) {
                    CHARS.random()
                } else {
                    char
                }
            }.joinToString("")
        }
    }

    private fun randomString(target: String) =
        target.map { CHARS.random() }.joinToString("")

}
