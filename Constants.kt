object Constants {

    const val POPULATION_SIZE = 10 // Quantity of individuals in our population.
    const val ITEMS_SIZE = 8 // Size must be the same as in indexToBaggageWeight 0..7 (8) elements.
    const val TIMES = 1000 // Number of generations to produce.
    const val TARGET_WEIGHT = 5950 // Individuals exceeding this weight indicate the required value.
    const val MAX_WEIGHT_LIMIT = 6000 // Maximum weight limit for the items to be transported.

    // Weights associated with each item index.
    val indexToBaggageWeight = mapOf(
        0 to 400,   // Weight of item 0
        1 to 2200,  // Weight of item 1
        2 to 2900,  // Weight of item 2
        3 to 15,    // Weight of item 3
        4 to 225,   // Weight of item 4
        5 to 700,   // Weight of item 5
        6 to 850,   // Weight of item 6
        7 to 380    // Weight of item 7
    )
}
