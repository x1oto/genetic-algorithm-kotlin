import Constants.TIMES
import Constants.indexToBaggageWeight
import kotlin.random.Random

class BackpackProblemByGA {

    // Run the genetic algorithm with the initial population.
    fun run(populationSize: Int, itemsSize: Int, targetWeight: Int, maxWeightLimit: Int) {
        // Initial population is generated with random combinations of items (0 or 1).
        var population = List(populationSize) { List(itemsSize) { Random.nextInt(from = 0, until = 2) } }

        // Evolve the population for a predefined number of iterations.
        for (i in 0..TIMES) {
            // Calculate the fitness of each individual in the current population.
            val calculatedFitness = fitness(maxWeightLimit, population)

            // Check if any individual meets the suitability criteria; if so, exit the loop.
            if (isIndividualSuitable(calculatedFitness, i, targetWeight, maxWeightLimit)) break

            // Select the best individuals based on fitness to form the next generation.
            val selected = select(calculatedFitness, population)

            // Perform crossover between selected individuals to create offspring.
            val crossed = crossover(selected)

            // Mutate the offspring to introduce genetic diversity.
            val mutated = mutation(crossed)

            // Update the population for the next iteration.
            population = mutated
        }
    }

    /**
     * Check if any individual in the population has a fitness score that meets or exceeds the target weight.
     * If a suitable individual is found, the function returns true and stops further iterations.
     */
    private fun isIndividualSuitable(calculatedFitness: List<Int>, i: Int, targetWeight: Int, maxWeightLimit: Int): Boolean {
        // Check if the maximum fitness is less than the target weight.
        if (calculatedFitness.max() < targetWeight) {
            // Print the generation number and similarity percentage if no suitable individual is found.
            println("$i. ${calculatedFitness.similarity(maxWeightLimit)}")
        } else {
            // Print the generation number and similarity percentage when a suitable individual is found.
            println("$i. ${calculatedFitness.similarity(maxWeightLimit)} <--| Suitable variant has been found! Exit.")
            return true
        }
        return false
    }

    // Extension function to calculate the similarity of the best fitness score to the max weight limit.
    private fun List<Int>.similarity(maxWeightLimit: Int) =
        "${max()} | Similarity: ${max() / maxWeightLimit.toDouble() * 100.0}"

    /**
     * Function to evaluate the fitness of each individual in the population.
     * It calculates the total weight of the selected items (represented by 1s in the individual)
     * and ensures it doesn't exceed the maximum weight limit. If the weight exceeds the limit,
     * the fitness is set to 0, indicating an invalid solution.
     */
    private fun fitness(maxWeightLimit: Int, population: List<List<Int>>): List<Int> {
        return population.map { individual ->
            var sum = 0
            // Calculate the sum of weights for the current individual.
            individual.forEachIndexed { index, i ->
                if (i == 1) sum += indexToBaggageWeight[index]!!  // Add the weight if the item is selected.
            }
            sum
        }.map { e -> if (e <= maxWeightLimit) e else 0 }  // Set fitness to 0 if the weight exceeds the limit.
    }

    /**
     * Tournament selection method to choose the best individuals based on their fitness scores.
     * The tournament randomly selects 3 individuals and chooses the one with the highest fitness.
     */
    private fun select(fitness: List<Int>, population: List<List<Int>>): List<List<Int>> {
        val bestIndividuals = mutableListOf<List<Int>>()

        // Perform tournament selection to pick the best individuals for the next generation.
        repeat(population.size) {
            // Randomly pick 3 individuals and select the one with the highest fitness.
            val bestIndividual = List(size = 3) { fitness.random() }.max()
            // Find the index of the selected individual in the population.
            val indexInPopulation = fitness.indexOf(bestIndividual)
            // Add the best individual to the new population.
            bestIndividuals.add(population[indexInPopulation])
        }

        return bestIndividuals
    }

    /**
     * Crossover function to combine selected individuals to create new offspring.
     * Each pair of parents exchanges genes at a randomly chosen crossover point.
     */
    private fun crossover(selected: List<List<Int>>): List<List<Int>> {
        val offspring = mutableListOf<List<Int>>()

        // Perform crossover in pairs (every 2 individuals).
        for (i in selected.indices step 2) {
            if (i + 1 < selected.size) {
                val parent1 = selected[i]
                val parent2 = selected[i + 1]

                // Randomly choose a crossover point.
                val crossoverPoint = Random.nextInt(1, parent1.size)

                // Create two offspring by combining genes from the parents.
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
     * A random gene (bit) is flipped in each individual to introduce genetic diversity.
     */
    private fun mutation(crossed: List<List<Int>>): List<List<Int>> {
        return crossed.map { individual ->
            val mutableIndividual = individual.toMutableList()
            // Randomly select a gene to mutate.
            val randomIndex = Random.nextInt(from = 0, until = individual.size)

            // Flip the gene (0 becomes 1, and 1 becomes 0).
            if (mutableIndividual[randomIndex] == 0) mutableIndividual[randomIndex] =
                1 else mutableIndividual[randomIndex] = 0

            mutableIndividual
        }
    }
}
