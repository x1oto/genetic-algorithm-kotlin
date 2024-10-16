# Genetic Algorithm for Knapsack Problem and Target String Matching

This repository contains two implementations of a **Genetic Algorithm (GA)** in Kotlin:
1. **Knapsack Problem** (Backpack problem).
2. **Target String Matching Problem**.

The GA is a powerful optimization technique inspired by the process of natural selection. It iteratively evolves a population of potential solutions through selection, crossover, and mutation, eventually converging to an optimal or near-optimal solution.

## What is a Genetic Algorithm?

A **Genetic Algorithm** is a search heuristic that is commonly used to find optimal or near-optimal solutions to problems that are difficult to solve using traditional methods. It mimics the process of biological evolution and operates using the following key concepts:

- **Population**: A set of potential solutions.
- **Fitness Function**: A way to evaluate how good each solution is.
- **Selection**: Choosing the best solutions to pass their genes to the next generation.
- **Crossover (Recombination)**: Combining parts of two solutions to create new offspring.
- **Mutation**: Randomly altering some part of a solution to introduce genetic diversity.

### Key Steps of the Genetic Algorithm

1. **Fitness Evaluation (`fitness()`)**:  
   This function evaluates how "fit" or suitable each individual (solution) is in the current population. The fitness score is calculated based on how well the solution meets the problem's constraints.
   
   - In the **Knapsack Problem**, the fitness is the total weight of the selected items, ensuring it does not exceed the maximum weight limit.
   - In the **Target String Problem**, the fitness measures how close a string is to the target string.

2. **Selection (`select()`)**:  
   The selection step chooses the best individuals from the current population based on their fitness scores. These individuals are selected to pass their genetic material to the next generation.

   - This project uses **tournament selection**, where a few random individuals are selected, and the one with the highest fitness is chosen.

3. **Crossover (`crossover()`)**:  
   Crossover is the process of taking two parent individuals and creating new offspring by combining parts of each parent’s solution. This step introduces new combinations of traits into the population.

   - In this project, we perform single-point crossover, where a random point is selected in the parent individuals, and the genetic material is swapped after that point to create offspring.

4. **Mutation (`mutation()`)**:  
   Mutation introduces random changes in the individuals’ solutions to maintain diversity in the population and prevent premature convergence.

   - Here, mutation is performed by randomly flipping one bit in the individual (changing a `0` to a `1` or vice versa).

5. **Termination**:  
   The algorithm repeats the process of selection, crossover, and mutation for a predefined number of iterations or until a suitable solution is found.

## Implemented Problems

### 1. Knapsack Problem

The **Knapsack Problem** involves selecting a subset of items with given weights to maximize the total weight without exceeding a weight limit. The genetic algorithm evolves a population of potential solutions, where each individual represents a combination of items (binary values: `0` for not selecting, `1` for selecting the item).

- **Objective**: Achieve a total weight close to the target weight (5950) without exceeding the max allowed weight (6000).
- **Parameters**:
  - Population size: 10
  - Number of items: 8
  - Target weight: 5950
  - Maximum allowed weight: 6000

### 2. Target String Matching

The **Target String Problem** involves evolving a population of random strings over multiple generations until one of the strings matches the target string exactly.

- **Objective**: Find the string "FIND ME" by evolving a population of random strings.
- **Fitness function**: Measures how close each string is to the target string based on character matches.

## How to Run

To run the program, follow these steps:

1. Open the `Main.kt` file in your development environment.

2. Run the `Main.kt` file. This will execute both genetic algorithm implementations **at the same time**:
   - The **Knapsack Problem** implementation will attempt to find an optimal selection of items that maximizes value while staying within a weight limit.
   - The **Target String Matching** implementation will evolve a population of random strings until one matches the target string (e.g., `"FIND ME"`).

3. You can also customize the parameters for both algorithms directly in `Main.kt`:
   - **For the Knapsack Problem**:
     - `populationSize`: The size of the population (default is 10).
     - `itemsSize`: The number of items available for selection (default is 8).
     - `targetWeight`: The desired total weight (default is 5950).
     - `maxWeightLimit`: The maximum allowed weight (default is 6000).

   - **For the Target String Matching**:
     - `target`: The string you want the algorithm to evolve towards (default is "FIND ME"). 
