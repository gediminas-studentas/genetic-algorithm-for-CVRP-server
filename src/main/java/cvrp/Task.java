package cvrp;

import cvrp.problem.CVRPProblem;

public class Task {
    private CVRPProblem problem;
    private TaskProperties properties;

    private Task() {
    }

    public Task(CVRPProblem problem, TaskProperties properties) {
        this.problem = problem;
        this.properties = properties;
    }

    public CVRPProblem getProblem() {
        return problem;
    }

    public TaskProperties getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "Task{" +
                "problem=" + problem +
                ", properties=" + properties +
                '}';
    }
}
