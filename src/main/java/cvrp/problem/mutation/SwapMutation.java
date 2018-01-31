package cvrp.problem.mutation;

import cvrp.problem.Chromosome;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;

/**
 * Sukeitimo operacija.
 * Jos metu yra sukeičiami vietomis du atsitiktinai parinkti genai.
 */
public class SwapMutation implements MutationPolicy {

    private static RandomGenerator randomGenerator = new JDKRandomGenerator();

    private final double mutationRate;

    public SwapMutation(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    @Override
    public boolean shouldMutate() {
        if (mutationRate == 0D) {
            return false;
        }

        if (mutationRate == 1D) {
            return true;
        }

        return randomGenerator.nextDouble() <= mutationRate;
    }

    @Override
    public Chromosome mutate(Chromosome original) {
        Integer[] genes = original.getGenes();
        int a = randomGenerator.nextInt(genes.length);
        int b = randomGenerator.nextInt(genes.length);
        int temp = genes[a];
        genes[a] = genes[b];
        genes[b] = temp;

        return original.copyWithGenes(genes);
    }
}
