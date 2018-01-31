
package cvrp.problem;

import cvrp.problem.mutation.MutationPolicy;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;

import java.util.Collection;

public class GeneticAlgorithm {

    private static RandomGenerator randomGenerator = new JDKRandomGenerator();

    private final CrossoverPolicy crossoverPolicy;
    private final double crossoverRate;

    private final Collection<MutationPolicy> mutationPolicies;

    private final SelectionPolicy selectionPolicy;

    public GeneticAlgorithm(final CrossoverPolicy crossoverPolicy,
                            final double crossoverRate,
                            final Collection<MutationPolicy> mutationPolicies,
                            final SelectionPolicy selectionPolicy
    ) throws IllegalArgumentException {

        if (crossoverRate < 0 || crossoverRate > 1) {
            throw new IllegalArgumentException("Kryžminimo dažnis turėtų buti tarp 0..1");
        }
        this.crossoverPolicy = crossoverPolicy;
        this.crossoverRate = crossoverRate;
        this.mutationPolicies = mutationPolicies;
        this.selectionPolicy = selectionPolicy;
    }

    public Population evolve(final Population current) {
        Population nextGeneration = current.nextGeneration();

        while (nextGeneration.getPopulationSize() < nextGeneration.getPopulationLimit()) {
            // pasirenkamos tėvinės chromosomos
            ChromosomePair pair = selectionPolicy.select(current);

            // kryžminti?
            if (randomGenerator.nextDouble() < crossoverRate) {
                pair = crossoverPolicy.crossover(pair.getFirst(), pair.getSecond());
            }

            // mutuoti?
            pair = mutate(pair);

            // pridedama pirma chromosomą prie populaicijos
            nextGeneration.addChromosome(pair.getFirst());
            // ar dar yra laisvos vietos ?
            if (nextGeneration.getPopulationSize() < nextGeneration.getPopulationLimit()) {
                // pridedama antra chromosomą prie populaicijos
                nextGeneration.addChromosome(pair.getSecond());
            }
        }

        return nextGeneration;
    }

    private ChromosomePair mutate(ChromosomePair original) {
        Chromosome first = original.getFirst();
        Chromosome second = original.getSecond();
        boolean mutated = false;
        for (MutationPolicy mutator : mutationPolicies) {
            if (!mutator.shouldMutate()) {
                continue;
            }
            first = mutator.mutate(first);
            second = mutator.mutate(second);
            mutated = true;
        }

        if (!mutated) {
            return original;
        }

        return new ChromosomePair(first, second);
    }
}
