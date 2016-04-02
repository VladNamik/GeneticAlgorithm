package algorithm;

import algorithm.crossing.Crossing;
import algorithm.crossing.DoublePointCrossover;
import algorithm.crossing.SinglePointCrossover;
import algorithm.mutation.*;
import algorithm.refresh.ProportionalRefresh;
import algorithm.refresh.Refresh;
import algorithm.refresh.RefreshKeepBest;
import algorithm.refresh.RefreshWithoutDelete;
import algorithm.selection.ProportionalSelection;
import algorithm.selection.Selection;
import algorithm.selection.TruncationSelection;

/**
 * Параметры запуска алгоритма
 */
public class AlgorithmStartParameters {
    //функции
    public Crossing crossing;
    public Mutation mutation;
    public Selection selection;
    public Refresh refresh;

    public int populationSize = 100; //начальный размер популяции
    public int mutationChance = 20; //шанс на мутацию в процентах
    public int crossingCoefficient = 50; // процент участвующих в передаче информации наследникам

    public int iterationQuantity = 1000;

    public boolean deleteTheSame = true;

    public AlgorithmStartParameters() {
//        refresh = new RefreshKeepBest(populationSize);
        refresh = new ProportionalRefresh(populationSize);

//        selection = new TruncationSelection(crossingCoefficient, false, false);
        selection = new ProportionalSelection(crossingCoefficient * 2);

//        crossing = new SinglePointCrossover();
        crossing = new DoublePointCrossover();

//        mutation = new SinglePointMutation();
//        mutation = new CombinedMutation(0.07);
        mutation = new ModifiedGreedyMutation(0.04);
//        mutation = new GreedyMutation();
    }
}
