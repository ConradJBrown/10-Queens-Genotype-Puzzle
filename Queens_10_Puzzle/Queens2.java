import java.lang.Math;
import java.util.*;

/* YOU NEED TO ADD YOUR CODE TO THIS CLASS, REMOVING ALL DUMMY CODE
 *
 * DO NOT CHANGE THE NAME OR SIGNATURE OF ANY OF THE EXISTING METHODS
 * (Signature includes parameter types, return types and being static)
 *
 * You can add private methods to this class if it makes your code cleaner,
 * but this class MUST work with the UNMODIFIED Tester2.java class.
 *
 * This is the ONLY class that you can submit for your assignment.
 *
 * MH 2020
 */
public class Queens2
{
    private static int boardSize = 10;
    
    // inverts the order of a series of genes in the genotype
    public static Integer[] inversionMutate(Integer[] genotype, double p)
    {
        // YOUR CODE GOES HERE
        double prob = 10 * p;
        Random ran = new Random();
        int chance = ran.nextInt(10);
        int max = 9;

        if (chance <= prob){
            int swap1 = ran.nextInt(10);
            int swap2 = ran.nextInt((max-swap1)+1)+swap1;
            
            List<Integer> temp = new ArrayList<Integer>();
            
            for(int i = swap1; i <= swap2; i++){
                temp.add(genotype[i]);
            }
            Collections.reverse(temp);
            Iterator<Integer> iterator = temp.iterator();
            while(iterator.hasNext()) {
                genotype[swap1] = iterator.next();
                swap1++;
             }

        }
        // END OF YOUR CODE
        
        return genotype;
    }
    
    /* performs fitness-proportional parent selection
     * also known as 'roulette wheel' selection
     * selects two parents that are different to each other
     */
    public static Integer[][] rouletteSelect(Integer[][] population)
    {
        Integer [][] parents = new Integer [2][boardSize];
        
        // YOUR CODE GOES HERE
        int totalFit = 0;
        for (int row = 0; row < 5; row++){
            totalFit += Measurefitness(population[row]);
        }

        Random ran = new Random();
        int temp = -1;

        for (int i = 0; i< 2; i++){
            int roulette = ran.nextInt(totalFit);
            int partialFit = 0;
            int parent = -1;
            while (partialFit <= roulette) {
                parent++;
                partialFit += Measurefitness(population[parent]);
                
            }
            if((temp == parent)){
                if (temp == 4){
                    parent--;
                } else if (temp==0){
                    int num = ran.nextInt(4);
                    parent += num;
                }
                else { parent-=1;}
            }
            if (parent >= 5){
                parent = 4;
            }
            parents[i] = population[parent];
            temp = parent;

        }
        // END OF YOUR CODE
        
        return parents;
    }
    
    /* creates a new population through λ + μ survivor selection
     * given a population of size n, and a set of children of size m
     * this method will measure the fitness of all individual in the
     * combined population, and return the n fittest individuals
     * as the new population
     */
    public static Integer[][] survivorSelection(Integer[][] population, Integer [][] children)
    {
        Integer [][] newPop = new Integer [10][10];
        
        // YOUR CODE GOES HERE
        Integer [][] totalPop = new Integer[30][10];
        Integer[] Fitness = new Integer[30];

        for (int a = 0; a < boardSize;a++){
            totalPop[a] = population[a];
        }
        int temp = boardSize;
        for (int b = 0; b < 20;b++){
            totalPop[temp] = children[b];
            temp++;
        }
        for (int c = 0; c < 30;c++){
            Fitness[c] = Measurefitness(totalPop[c]);
        }
        for (int i = 0; i < boardSize;i++){
            int elite = 0;
            int lastFit = 0;
            for (int j = 0; j < 30;j++){
                if (Fitness[j] > lastFit){
                    elite = j;
                    lastFit = Fitness[j];
                }  
            }
            newPop[i] = totalPop[elite];
            Fitness[elite] = 0;
        }

        
        
        // END OF YOUR CODE
        
        return newPop;
    }
    
    // counts the number of unique genotypes in the population
    public static int genoDiversity(Integer[][] population)
    {
        int uniqueTypes = 0;
        
        // YOUR CODE GOES HERE
        boolean equal = false;
        for (int row = 0; row < boardSize - 1;row++){
            for (int compare = row+1; compare < boardSize;compare++){
                for(int i = 0; i < boardSize;i++){
                    if (population[row][i] == population[compare][i]){
                        equal = true;
                    } else {equal = false;}
                }
                
            }
            if (!equal){
                uniqueTypes+=1;
            }
        }
        // END OF YOUR CODE
        
        return uniqueTypes;
    }

    private static int Measurefitness(Integer[] pop){
        int fitness = (int) (0.5 * boardSize * (boardSize - 1));
        
        // YOUR CODE GOES HERE
        for (int i = 0; i < boardSize;i++){
            int dup = pop[i] + 1;
            int ddown = pop[i] - 1;
            for (int j = i + 1; j < boardSize; j++){
               if ((pop[j] == dup) || (pop[j] == ddown)){
                    fitness = fitness - 1;
               }
               ddown -= 1;
               dup += 1;
            }
        }
        return fitness;
        
    }
}
