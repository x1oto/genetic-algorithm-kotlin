# Implementation of Genetic Algorithm in Kotlin

## Situation
We have a plane that can carry only 6000 kg. We are faced with the task of selecting from a set of items that need to be transported to another location. These items can have different weights and values, and our goal is to find the optimal combination of items that maximizes the use of the available weight of the plane without exceeding the limit of 6000 kg. For more information about the items and their characteristics, please refer to the `Constants.kt` file.

## Goal
The main objective is to determine the most advantageous combination of items that we can load onto the plane to maximize the total value of the cargo. This problem relates to the classic knapsack problem, where the aim is to select items such that their total weight does not exceed a certain limit, in our case, 6000 kg.

## Use of Genetic Algorithm
To solve this problem, we will use a genetic algorithm that simulates the process of natural selection. Genetic algorithms are well-suited for finding optimal solutions in complex problems that have a large number of possible combinations.

## The process will involve several key stages:

1. **Initialization of the Population:**  
   Creating an initial group of individuals that represent different combinations of items.

2. **Fitness Evaluation:**  
   Calculating the value of each combination while considering weight constraints.

3. **Selection:**  
   Choosing the best individuals based on their fitness scores for further evolution.

4. **Crossover:**  
   Combining parental combinations to create new offspring that may possess improved characteristics.

5. **Mutation:**  
   Introducing random changes in combinations to maintain genetic diversity and explore new solutions.

6. **Iteration:**  
   The process is repeated until an acceptable solution is found or the specified number of iterations is reached.

## Conclusion
Thus, by using a genetic algorithm, we will be able to find the optimal solution for transporting cargo that best meets our requirements.
