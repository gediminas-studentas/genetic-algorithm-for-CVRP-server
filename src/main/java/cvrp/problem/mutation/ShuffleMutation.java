package cvrp.problem.mutation;

import cvrp.problem.Chromosome;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;

public class ShuffleMutation implements MutationPolicy {

    private static RandomGenerator randomGenerator = new JDKRandomGenerator();

    private final double mutationRate;

    public ShuffleMutation(double mutationRate) {
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

        return original.copyWithGenes(shuffle(genes));
    }

    /**
     * Code from method java.util.Collections.shuffle();
     */
    private static Integer[] shuffle(Integer[] array) {
        int count = array.length;
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, randomGenerator.nextInt(i));
        }

        return array;
    }

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
