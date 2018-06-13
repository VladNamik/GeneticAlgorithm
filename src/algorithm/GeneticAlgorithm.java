package algorithm;

import algorithm.crossing.Crossing;
import algorithm.mutation.Mutation;
import algorithm.refresh.Refresh;
import algorithm.selection.Selection;

import java.util.List;

/**
 * Implementation of genetic algorithm
 */
public class GeneticAlgorithm {

    // algoritm parameters
    private Roads roads;
    private Population currentPopulation;
    private double maxWeight = 0;
    private int iterationQuantity = 200;
    private int mutationChance = 20;
    private boolean deleteTheSame;


    // implementation of specific functions used in GA
    private Crossing crossing;
    private Mutation mutation;
    private Selection selection;
    private Refresh refresh;


    // execution process
    private double resultStatus = 0; // percentage of completed task
    private boolean isStopped = false;


    public GeneticAlgorithm(Roads roads, AlgorithmStartParameters startParameters)
    {
        this.roads = roads;
        this.crossing = startParameters.crossing;
        this.mutation = startParameters.mutation;
        this.iterationQuantity = startParameters.iterationQuantity;
        this.selection = startParameters.selection;
        this.mutationChance = startParameters.mutationChance;
        this.refresh = startParameters.refresh;
        this.deleteTheSame = startParameters.deleteTheSame;


        currentPopulation = new Population();
        for (int i = 0; i < startParameters.populationSize; i++)
            currentPopulation.add(new Phenotype(roads));
    }

    public void run()
    {
        int i=0; // generation number

        while(currentPopulation.getBest().getFitness() > maxWeight && !isStopped && i < iterationQuantity)
        {


            // select and cross
            this.currentPopulation = selection.selection(currentPopulation, crossing);



            // mutation
            if (getStartMutation(i)) {
                List<Phenotype> phenotypeList = currentPopulation.getAll();
                for (int j = 1; j < phenotypeList.size(); j++)
                    if (RandomNumber.random.nextDouble() <= (double) mutationChance / 100)
                        mutation.mutation(phenotypeList.get(j));
                currentPopulation.sort();
            }



            // mark the same if they exist
            if (deleteTheSame)
            {
                double[] fitness = new double[1];
                fitness[0] = 0.0;
                currentPopulation.getAll().forEach((x) ->
                {
                    if (x.getFitness() == fitness[0])
                        x.setAlive(false);
                    fitness[0] = x.getFitness();
                });
            }

            // purification of the population from extra individuals
            this.currentPopulation = refresh.refresh(currentPopulation);


            i++;
            resultStatus = i / iterationQuantity;
            System.out.println(currentPopulation.getBest().getFitness());
        }
    }

    public double getResultStatus()
    {
        return resultStatus;
    }

    public Phenotype getResult()
    {
        return currentPopulation.getBest();
    }

    public void stop() {
        isStopped = true;
    }

    public boolean getStartMutation(int x)
    {
//        if(x < iterationQuantity / 10)
//            return true;
//
//        if (x % (int)Math.sqrt(iterationQuantity) == 0)
//            return true;
//        return false;
        return true;
    }
}
