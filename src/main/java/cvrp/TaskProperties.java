package cvrp;

public class TaskProperties {
    private int resultRefreshRate;
    private int evolutionCyclesLimit;
    private int initPopulationSize;
    private int populationLimit;
    private double elitismRate;
    private double crossoverRate;
    private double swapMutationRate;
    private double insertMutationRate;
    private double shuffleMutationRate;

    private TaskProperties() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getResultRefreshRate() {
        return resultRefreshRate;
    }

    public int getEvolutionCyclesLimit() {
        return evolutionCyclesLimit;
    }

    public int getInitPopulationSize() {
        return initPopulationSize;
    }

    public int getPopulationLimit() {
        return populationLimit;
    }

    public double getElitismRate() {
        return elitismRate;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public double getSwapMutationRate() {
        return swapMutationRate;
    }

    public double getInsertMutationRate() {
        return insertMutationRate;
    }

    public double getShuffleMutationRate() {
        return shuffleMutationRate;
    }

    public static final class Builder {
        private int resultRefreshRate;
        private int evolutionCyclesLimit;
        private int initPopulationSize;
        private int populationLimit;
        private double elitismRate;
        private double crossoverRate;
        private double swapMutationRate;
        private double insertMutationRate;
        private double shuffleMutationRate;

        private Builder() {
        }

        public Builder withResultRefreshRate(int resultRefreshRate) {
            this.resultRefreshRate = resultRefreshRate;
            return this;
        }

        public Builder withEvolutionCyclesLimit(int evolutionCyclesLimit) {
            this.evolutionCyclesLimit = evolutionCyclesLimit;
            return this;
        }

        public Builder withInitPopulationSize(int initPopulationSize) {
            this.initPopulationSize = initPopulationSize;
            return this;
        }

        public Builder withPopulationLimit(int populationLimit) {
            this.populationLimit = populationLimit;
            return this;
        }

        public Builder withElitismRate(double elitismRate) {
            this.elitismRate = elitismRate;
            return this;
        }

        public Builder withCrossoverRate(double crossoverRate) {
            this.crossoverRate = crossoverRate;
            return this;
        }

        public Builder withSwapMutationRate(double swapMutationRate) {
            this.swapMutationRate = swapMutationRate;
            return this;
        }

        public Builder withInsertMutationRate(double insertMutationRate) {
            this.insertMutationRate = insertMutationRate;
            return this;
        }

        public Builder withShuffleMutationRate(double shuffleMutationRate) {
            this.shuffleMutationRate = shuffleMutationRate;
            return this;
        }

        public TaskProperties build() {
            TaskProperties taskProperties = new TaskProperties();
            taskProperties.resultRefreshRate = resultRefreshRate;
            taskProperties.evolutionCyclesLimit = evolutionCyclesLimit;
            taskProperties.initPopulationSize = initPopulationSize;
            taskProperties.populationLimit = populationLimit;
            taskProperties.elitismRate = elitismRate;
            taskProperties.crossoverRate = crossoverRate;
            taskProperties.swapMutationRate = swapMutationRate;
            taskProperties.insertMutationRate = insertMutationRate;
            taskProperties.shuffleMutationRate = shuffleMutationRate;

            return taskProperties;
        }
    }

    @Override
    public String toString() {
        return "TaskProperties{" +
                "resultRefreshRate=" + resultRefreshRate +
                ", evolutionCyclesLimit=" + evolutionCyclesLimit +
                ", initPopulationSize=" + initPopulationSize +
                ", populationLimit=" + populationLimit +
                ", elitismRate=" + elitismRate +
                ", crossoverRate=" + crossoverRate +
                ", swapMutationRate=" + swapMutationRate +
                ", insertMutationRate=" + insertMutationRate +
                ", shuffleMutationRate=" + shuffleMutationRate +
                '}';
    }
}
