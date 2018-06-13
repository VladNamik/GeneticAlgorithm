# Genetic Algorithm

Genetic algorithm (GA) for travelling salesman problem implementation on Java.

## GUI

Put dots by yourself or simply enter desired quantity and press "Random". 
Best unit fitness function result on each iteration is displayed in stdout. 

<a href="url"><img src="https://github.com/VladNamik/GeneticAlgorithm/blob/master/screenshots/main.png?raw=true" align="center" height="542" width="842"></a>

## Algorithm parameters

You can tune algorithm parameters, such as _size of population_, _chance of mutation_, _crossing coefficient_, _iterations quantity_, _type of crossing_, _mutation_, _selection_ and _refresh_ in algorithm.AlgorithmStartParameters class.

Types of different parts of GA which implemented:
* Selection
** Truncation Selection - just best ones
** Proportional Selection - better unit has more chances to be selected 
* Crossing
** Single Point Crossover / Partially Displayed Crossover
** Two Point Crossover / Ordered Crossover
* Mutation
** Single Point Mutation - swap two genes
** Greedy Mutation
** Modified Greedy Mutation - with a given probability swaps the first/last with that in the middle
** Combined Mutation - Greedy Mutation + Single Point Mutation
* Refresh (population update, removal of redundant individuals)
** "Keep Best" Refresh - removes first those that are marked, then the "worst" of the population, keeping a certain size of the population
** Proportional Refresh - first removes those that are marked, then with a certain probability removes those that are left.
** Refresh Without Deleting - deletes only when the upper bound is reached, then refresh is performed on the remaining
