package cvrp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import cvrp.problem.*;
import cvrp.problem.mutation.InsertMutation;
import cvrp.problem.mutation.ShuffleMutation;
import cvrp.problem.mutation.SwapMutation;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.List;

public class Server
{

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main( String[] args ) throws IOException {
        if (args.length == 0) {
            System.out.println("Please provide a port number");
            System.exit(64);
        }
        int port = Integer.parseInt(args[0]);
        ServerSocket welcomeSocket = new ServerSocket(port);

        while (true) {
            System.out.println("Accepting a task on port: " + port);
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            String serializedTask = inFromClient.readLine();
            Task task = objectMapper.readValue(serializedTask, Task.class);
            System.out.println("Received: " + serializedTask);
            List<Chromosome> initialRandomChromosomes = Chromosome.randomize(task.getProperties().getInitPopulationSize(), task.getProblem());
            Population population = new Population(initialRandomChromosomes, task.getProperties().getPopulationLimit(), task.getProperties().getElitismRate());
            GeneticAlgorithm ga = new GeneticAlgorithm(
                    new PMXCrossover(), task.getProperties().getCrossoverRate(),
                    ImmutableList.of(
                            new SwapMutation(task.getProperties().getSwapMutationRate()),
                            new InsertMutation(task.getProperties().getInsertMutationRate()),
                            new ShuffleMutation(task.getProperties().getShuffleMutationRate())
                    ),
                    new RandomSelection()
            );

            long startTime = System.currentTimeMillis() - task.getProperties().getResultRefreshRate();
            for (int i = 0; i < task.getProperties().getEvolutionCyclesLimit(); i++) {
                long elapsedTime = (new Date()).getTime() - startTime;
                if (elapsedTime >= task.getProperties().getResultRefreshRate()) {
                    DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                    String result = i + ", " + population.toString() + ", " + population.getFittestChromosome();
                    try {
                        outToClient.writeBytes(result + '\n');
                    } catch (SocketException socketException) {
                       break; //Start listening for another task if connection is lost or reset by client
                    }

                    startTime = System.currentTimeMillis();
                }
                population = ga.evolve(population);
            }
        }
    }
}
