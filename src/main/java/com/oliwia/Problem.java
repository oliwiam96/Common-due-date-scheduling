package com.oliwia;

import java.util.List;

public class Problem {
    private List<Job> jobs;
    private int numberOfJobs;
    private int k; // index of problem in input
    private int sumOfP;

    public Problem(List<Job> jobs, int numberOfJobs, int k) {
        this.jobs = jobs;
        this.numberOfJobs = numberOfJobs;
        this.k = k;
        calculateSumOfP();
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public int getNumberOfJobs() {
        return numberOfJobs;
    }

    public int getK() {
        return k;
    }

    public int getSumOfP() {
        return sumOfP;
    }

    private void calculateSumOfP() {
        sumOfP = jobs.stream().mapToInt(Job::getP).sum();
    }
}
