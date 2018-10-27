package com.oliwia;

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
                if (i == k) {
                    Problem problem = new Problem(jobs, numberOfJobs, i + 1);
                    return problem;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static SubProblem getKthProblemWithH(int k, int n, double h) {
        Problem problem = FileReaderWriter.getKthProblem(k, n);
        SubProblem subProblem = new SubProblem(h, problem);
        return subProblem;
    }


    public static void saveSolutionToFile(SubProblem subProblem) {
        String fileContent = String.valueOf(subProblem.getCostFunction()) + "\n";
        for (int i = 0; i < subProblem.getExecutedJobsInOrder().size(); i++) {
            fileContent += subProblem.getExecutedJobsInOrder().get(i).getIndexInInputFile();
            if (i != subProblem.getExecutedJobsInOrder().size() - 1) {
                fileContent += " ";
            }
        }
        fileContent += "\n";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("sch_127324"
                    + "_" + subProblem.getProblem().getNumberOfJobs() + "_"
                    + subProblem.getProblem().getK() + "_"
                    + (int) (subProblem.getH() * 10) + ".out"));
            writer.write(fileContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
