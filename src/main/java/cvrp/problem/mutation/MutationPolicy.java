package cvrp.problem.mutation;

import cvrp.problem.Chromosome;

public interface MutationPolicy {

    boolean shouldMutate();

    /**
     * Mutuoja dvi chromosomas
     * @param original originali chromosoma.
     * @return mutuota chromosoma
     */
    Chromosome mutate(Chromosome original);
}
