package com.oliwia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem {
    private List<Job> jobs;
    private int numberOfJobs;
    private int k; // index of problem in input
    private int sumOfP;
    private Map<Double, SubProblem> subProblems;

    public Problem(List<Job> jobs, int numberOfJobs, int k) {
        this.jobs = jobs;
        this.numberOfJobs = numberOfJobs;
        this.k = k;
        subProblems = new HashMap<>();
        calculateSumOfP();
        subProblems.put(0.2, new SubProblem(0.2, this));
        subProblems.put(0.4, new SubProblem(0.4, this));
        subProblems.put(0.6, new SubProblem(0.6, this));
        subProblems.put(0.8, new SubProblem(0.8, this));
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public int getNumberOfJobs() {
        return numberOfJobs;
    }

    public void setNumberOfJobs(int numberOfJobs) {
        this.numberOfJobs = numberOfJobs;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getSumOfP() {
        return sumOfP;
    }

    private void calculateSumOfP() {
        sumOfP = jobs.stream().mapToInt(Job::getP).sum();
    }

    public SubProblem getProblemWithH(double h){
        return subProblems.get(h);
    }
}
