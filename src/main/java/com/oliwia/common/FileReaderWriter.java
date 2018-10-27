package com.oliwia.common;

import com.oliwia.solver.Job;
import com.oliwia.solver.Problem;
import com.oliwia.solver.SubProblem;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderWriter {
    private static Problem getKthProblem(int k, int n) {
        try {
            Scanner in = new Scanner(new FileReader("sch" + n + ".txt"));

            int numberOfProblems = in.nextInt();
            for (int i = 0; i < numberOfProblems; i++) {
                int numberOfJobs = in.nextInt();
                List<Job> jobs = new ArrayList<>();
                for (int j = 0; j < numberOfJobs; j++) {
                    int p = in.nextInt();
                    int a = in.nextInt();
                    int b = in.nextInt();
                    Job job = new Job(p, a, b, j);
                    jobs.add(job);
                }
                if (i+1 == k) {
                    return new Problem(jobs, numberOfJobs, i + 1);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static SubProblem getKthProblemWithH(int k, int n, double h) {
        Problem problem = FileReaderWriter.getKthProblem(k, n);
        return new SubProblem(h, problem);
    }


    public static void saveSolutionToFile(SubProblem subProblem) {
        StringBuilder sb = new StringBuilder();
        sb.append(subProblem.getCostFunction()).append("\n");
        for (int i = 0; i < subProblem.getExecutedJobsInOrder().size(); i++) {
            sb.append(subProblem.getExecutedJobsInOrder().get(i).getIndexInInputFile());
            if (i != subProblem.getExecutedJobsInOrder().size() - 1) {
                sb.append(" ");
            }
        }
        sb.append("\n");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("sch_127324"
                    + "_" + subProblem.getProblem().getNumberOfJobs() + "_"
                    + subProblem.getProblem().getK() + "_"
                    + (int) (subProblem.getH() * 10) + ".out"));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getDeclaredCostAndSetOrder(SubProblem subProblem, String fileName){
        int declaredCost = 0;
        try {
            Scanner in = new Scanner(new FileReader(fileName));

            declaredCost = in.nextInt();
            in.nextLine();
            String[] indexes = in.nextLine().split(" ");

            List<Job> allJobs = subProblem.getProblem().getJobs();
            List<Job> executedJobInOrder = subProblem.getExecutedJobsInOrder();
            for(int i = 0; i < indexes.length; i++){
                int index = Integer.parseInt(indexes[i]);
                executedJobInOrder.add(allJobs.get(index));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return declaredCost;
    }
}
