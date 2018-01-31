package cvrp.problem.mutation;

import cvrp.problem.Chromosome;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;

import java.util.List;

/**
 * Pagalbinė klasė padedanti lengviau sujungti mutacijos operatorius į eilę
 */
public class MutationChain implements MutationPolicy {

    private static RandomGenerator randomGenerator = new JDKRandomGenerator();

    private final List<MutationPolicy> mutatators;

    private final double mutationRate;

    public MutationChain(List<MutationPolicy> mutatators, double mutationRate) {
        this.mutatators = mutatators;
        this.mutationRate = mutationRate;
    }

    @Override
    public boolean shouldMutate() {
        return randomGenerator.nextDouble() <= mutationRate;
    }

    @Override
    public Chromosome mutate(Chromosome original) {
        Chromosome mutated = original;
        for (MutationPolicy mutator : mutatators) {
            mutated = mutator.mutate(mutated);
        }

        return mutated;
    }
}
