package com.oliwia;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFile {
    private List<Problem> problems;
    private int numberOfProblems;
    private int n;

    public InputFile(int n) {
        this.n = n;
        problems = new ArrayList<>();
        readInput();
    }

    private void readInput() {
        try {
            Scanner in = new Scanner(new FileReader("src/main/resources/com/oliwia/examples/sch" + n + ".txt"));
            //Scanner in = new Scanner(new FileReader("sch" + n + ".txt"));

            this.numberOfProblems = in.nextInt();
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
                Problem problem = new Problem(jobs, numberOfJobs, i+1);
                problems.add(problem);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Problem getKthProblem(int k){
        return problems.get(k-1);
    }

    public SubProblem getKthProblemWithH(int k, double h){
        Problem kthProblem = getKthProblem(k);
        return kthProblem.getProblemWithH(h);
    }
}
