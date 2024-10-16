fun main(args: Array<String>) {
    // Solving the Backpack (Knapsack) Problem using a Genetic Algorithm (GA).
    // Parameters: population size = 10 (number of possible solutions), items size = 8 (number of available items to choose from),
    // target weight = 5950, max allowed weight = 6000.
    // The goal of this algorithm is to optimize the selection of items that maximizes the total value,
    // while ensuring the total weight is as close as possible to the target weight (5950) without exceeding the max weight (6000).
    // The algorithm evolves the population through crossover, mutation, and selection to find the best solution.
    val backpackProblemByGA = BackpackProblemByGA()
        .run(populationSize = 10, itemsSize = 8, targetWeight = 5950, maxWeightLimit = 6000)

    // Solving the Target String Problem using a Genetic Algorithm (GA).
    // The goal of this algorithm is to evolve a population of randomly generated strings,
    // through multiple generations, until one of the strings matches the target string "FIND ME".
    // The algorithm applies selection, crossover, and mutation to gradually improve the population
    // based on the similarity (fitness) of each string to the target string.
    val findTargetStringByGA = FindTargetStringByGA()
        .run(target = "FIND ME")
}
