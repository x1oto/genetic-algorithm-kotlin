import Constants.ITEMS_SIZE
import Constants.MAX_WEIGHT_LIMIT
import Constants.POPULATION_SIZE
import Constants.TARGET_WEIGHT
import Constants.TIMES
import Constants.indexToBaggageWeight
import kotlin.random.Random

fun main() {
    // Initial population list created with random combinations of items (0 or 1).
    val population = List(POPULATION_SIZE) { List(ITEMS_SIZE) { Random.nextInt(from = 0, until = 2) } }
    // Run the genetic algorithm with the initial population.
    runGeneticAlgorithm(population)
}

private fun runGeneticAlgorithm(population: List<List<Int>>) {
    var population1 = population
    // Iterate through a specified number of times (generations).
    for (i in 0..TIMES) {
        // Calculate the fitness of the current population.
        val calculatedFitness = fitness(population1)
        // Check if any individual meets the suitability criteria; if so, exit the loop.
        if (isIndividualSuitable(calculatedFitness, i)) break
        // Select the best individuals based on fitness for the next generation.
        val selected = select(calculatedFitness, population1)
        // Perform crossover to create new offspring.
        val crossed = crossover(selected)
        // Mutate the offspring to introduce genetic diversity.
        val mutated = mutation(crossed)
        // Update the population for the next iteration.
        population1 = mutated
    }
}

private fun isIndividualSuitable(calculatedFitness: List<Int>, i: Int): Boolean {
    // Check if the maximum fitness is less than the target weight.
    if (calculatedFitness.max() < TARGET_WEIGHT) {
        // Print the generation number and similarity if not suitable.
        println("$i. ${calculatedFitness.similarity()}")
    } else {
        // Print that a suitable variant has been found and exit.
        println("$i. ${calculatedFitness.similarity()} <--| Suitable variant has been found! Exit.")
        return true
    }
    return false
}

// Extension function to calculate the similarity of the best fitness score.
private fun List<Int>.similarity() =
    "${max()} | Similarity: ${max() / MAX_WEIGHT_LIMIT.toDouble() * 100.0}"


/**
 * Function to evaluate the fitness of each individual in the population.
 * It calculates the total weight of the selected items and ensures it doesn't exceed the maximum limit.
 */
private fun fitness(population: List<List<Int>>): List<Int> {
    return population.map { individual ->
        var sum = 0
        individual.forEachIndexed { index, i ->
            if (i == 1) sum += indexToBaggageWeight[index]!!
        }
        sum
    }.map { e -> if (e <= MAX_WEIGHT_LIMIT) e else 0 }
}

/**
 * Tournament selection method to choose the best individuals based on their fitness scores.
 */
private fun select(fitness: List<Int>, population: List<List<Int>>): List<List<Int>> {
    val bestIndividuals = mutableListOf<List<Int>>()

    repeat(POPULATION_SIZE) {
        val bestIndividual = List(size = 3) { fitness.random() }.max()
        val indexInPopulation = fitness.indexOf(bestIndividual)
        bestIndividuals.add(population[indexInPopulation])
    }

    return bestIndividuals
}

/**
 * Crossover function to combine selected individuals to create new offspring.
 */
private fun crossover(selected: List<List<Int>>): List<List<Int>> {
    val offspring = mutableListOf<List<Int>>()

    for (i in selected.indices step 2) {
        if (i + 1 < selected.size) {
            val parent1 = selected[i]
            val parent2 = selected[i + 1]

            val crossoverPoint = Random.nextInt(1, parent1.size)

            val child1 = parent1.take(crossoverPoint) + parent2.drop(crossoverPoint)
            val child2 = parent2.take(crossoverPoint) + parent1.drop(crossoverPoint)

            offspring.add(child1)
            offspring.add(child2)
        }
    }
    return offspring
}

/**
 * Mutation function to introduce random changes in the offspring.
 */
private fun mutation(crossed: List<List<Int>>): List<List<Int>> {
    return crossed.map { individual ->
        val mutableIndividual = individual.toMutableList()
        val randomIndex = Random.nextInt(from = 0, until = individual.size)

        if (mutableIndividual[randomIndex] == 0) mutableIndividual[randomIndex] =
            1 else mutableIndividual[randomIndex] = 0

        mutableIndividual
    }
}
