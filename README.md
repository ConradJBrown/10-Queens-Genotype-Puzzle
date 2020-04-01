# 10-Queens-Genotype-Puzzle
Here is the assignment I did, with the description of what what meant to be done for each part.

You are going to continue writing an evolutionary algorithm to solve the 10 Queens Puzzle.

When combined with the code written for Assignment 2, the work you do here will provide all the key components for a full genetic algorithm.  With slight re-writing you will be able to apply it to solving other permutation problems in future, and the modular nature of the code will allow you to create alternative operators for mutation, recombination and selection.  But right now, let's focus on the tasks for Assignment 3.

**For this assignment you will need to write the code for 4 of the key components:**

- Mutation (an improved operator compared to Assignment 2)
- Parent Selection
- Survivor Selection
- Measuring Population Diversity (often useful for termination criteria and analysing a GA's performance)

Four classes have been provided for this task.  The first three classes are in .java format and you can view their code: Queens2.java, Tester2.java and Coordinate.java. Tester2.java and coordinate.java where given by Dr. Mark Hatcher

The Queens.java file was my original file from my second assignment.

You should keep all four of these classes in the same folder when compiling and running your work.

*Queens2.java*

This class contains the four methods that you need to complete.  This is what they need to do:

- ***inversionMutate()***
  - selects two random points in the genotpye
  - inverts the sequence of genes between those points, inclusive
  - this happens with probability p
    - so if p = 0.8, then 8 out of 10 times this method is called, a swap should happen
- ***rouletteSelect()***
  - takes a population and performs fitness proportional parent selection
  - it selects two parents that are different to each other
  - because the results of this method are highly stochastic, it's going to be tested in the Tester2 class by making 10000 calls of this method:
    - comparing the expected number of times that each population member will be selected with the actual number
      - the actual and expected figures should be close, but are highly unlikely to be identical
      - and the worst fitness individual will still get chosen over 10000 runs, so don't be surprised by that!
- ***survivorSelection()***
  - takes an existing population of size n plus a population of newly created children of size m, and performs λ + μ survivor selection
  - simply measures the fitness of each individual in both populations and then selects the n fittest individuals to form the new population
- ***genoDiversity()***
  - measures the number of unique genotypes found in the provided population


## Running this program
  All you need to do is run the Tester2.java file. This is a generally made file from my professor that he uses but feel free to create a new testing tester file if you want to.
