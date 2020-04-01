import java.lang.Math;
import java.util.*;

/* YOU NEED TO ADD YOUR CODE TO THIS CLASS, REMOVING ALL DUMMY CODE
 *
 * DO NOT CHANGE THE NAME OR SIGNATURE OF ANY OF THE EXISTING METHODS
 * (Signature includes parameter types, return types and being static)
 *
 * You can add private methods to this class if it makes your code cleaner,
 * but this class MUST work with the UNMODIFIED Tester.java class.
 *
 * This is the ONLY class that you can submit for your assignment.
 *
 * MH 2020
 */
public class Queens
{
    private static int boardSize = 10;
    
    // creates a valid genotype with random values
    public static Integer [] randomGenotype()
    {
        Integer [] genotype = new Integer [boardSize];
        
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        genotype = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        Random ran = new Random();
        for (int i = 0; i< boardSize; i++){
            int r = ran.nextInt(boardSize);
            int change = genotype[r];
            genotype[r] = genotype[i];
            genotype[i] = change;

        }

        // END OF YOUR CODE
        
        return genotype;
    }
    
    // swaps 2 genes in the genotype
    // the swap happens with probability p, so if p = 0.8
    // then 8 out of 10 times this method is called, a swap happens
    public static Integer[] mutate(Integer[] genotype, double p)
    {
        // YOUR CODE GOES HERE
        double prob = 100 * p;
        Random ran = new Random();
        int chance = ran.nextInt(100);

        if (chance <= prob){
            int a = ran.nextInt(boardSize);
            int b = ran.nextInt(boardSize);
            int change = genotype[a];
            genotype[a]=genotype[b];
            genotype[b]=change;
        }
        // END OF YOUR CODE
        
        return genotype;
    }
    
    // creates 2 child genotypes using the 'cut-and-crossfill' method
    public static Integer[][] crossover(Integer[] parent0, Integer[] parent1)
    {
        Integer [][] children = new Integer [2][boardSize];
        
        // YOUR CODE GOES HERE
        int n = (boardSize/2);
        List<Integer> Child0 = new ArrayList<Integer>();
        List<Integer> Child1 = new ArrayList<Integer>();
        for (int i = 0; i < n; i++){
            Child0.add(parent0[i]);
            Child1.add(parent1[i]);
        }
        
        for (int i = n; i < boardSize; i++){
            if(!Child0.contains(parent1[i])){
                Child0.add(parent1[i]);
            }
            if(!Child1.contains(parent0[i])){
                Child1.add(parent0[i]);
            }
        }
        for (int i = 0; i < n; i++){
            if(!Child0.contains(parent1[i])){
                Child0.add(parent1[i]);
            }
            if(!Child1.contains(parent0[i])){
                Child1.add(parent0[i]);
            }
        }
       for (int i = 0; i < boardSize; i++){
           children[0][i] = Child0.get(i);
       }
       for (int i = 0; i < boardSize; i++){
        children[1][i] = Child1.get(i);
    }

        // END OF YOUR CODE
        
        return children;
    }
    
    // calculates the fitness of an individual
    public static int measureFitness(Integer [] genotype)
    {
        /* The initial fitness is the maximum pairs of queens
         * that can be in check (all possible pairs in check).
         * So we are using it as the maximum fitness value.
         * We deduct 1 from this value for every pair of queens
         * found to be in check.
         * So, the lower the score, the lower the fitness.
         * For a 10x10 board the maximum fitness is 45 (no checks),
         * and the minimum fitness is 0 (all queens in a line).
         */
        int fitness = (int) (0.5 * boardSize * (boardSize - 1));
        
        // YOUR CODE GOES HERE
        for (int i = 0; i < boardSize;i++){
            int dup = genotype[i] + 1;
            int ddown = genotype[i] - 1;
            for (int j = i + 1; j < boardSize; j++){
               if ((genotype[j] == dup) || (genotype[j] == ddown)){
                    fitness = fitness - 1;
               }
               ddown -= 1;
               dup += 1;
            }
        }
        return fitness;
    }
}
