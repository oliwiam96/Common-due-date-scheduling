package com.oliwia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SubProblem {
    private double h;
    private Problem problem;
    private int sumOfEarliness;
    private int sumOfTardiness;
    private int dueDate;
    private List<Job> executedJobsInOrder;
    private int firstJobMoment; // must be >= 0 and of course should be <= dueDate

    public SubProblem(double h, Problem problem) {
        this.h = h;
        this.problem = problem;
        this.executedJobsInOrder = new ArrayList<>();
        calculateDueDate();
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    private void calculateDueDate() {
        dueDate = (int) Math.floor(h * problem.getSumOfP());
    }

    private void insertFistJob() {
        // find the worst job and put it as good as possible
        Job jobWithMaxA = getJobWithMaxA();
        Job jobWithMaxB = getJobWithMaxB();
        if (jobWithMaxA.getA() > jobWithMaxB.getB()) {
            // if too early is that bad, start just after due date
            this.firstJobMoment = dueDate;
            executedJobsInOrder.add(jobWithMaxA);
        } else {
            // if too late is that bad, end just before due date
            executedJobsInOrder.add(jobWithMaxB);
            int preferredStartMoment = dueDate - jobWithMaxB.getB();
            this.firstJobMoment = (preferredStartMoment > 0) ? preferredStartMoment : 0;
        }
    }

    public void solve() {
        insertFistJob();

        Job jobWithMaxA = getJobWithMaxA();
        Job jobWithMaxB = getJobWithMaxB();


    }

    public int getSolutionValue() {
        return sumOfEarliness + sumOfTardiness;
    }

    public void saveSolutionToFile() {
        // TODO
    }

    private Job getJobWithMaxA() {
        return Collections.max(this.problem.getJobs(), Comparator.comparing(Job::getA));

    }

    private Job getJobWithMaxB() {
        return Collections.max(this.problem.getJobs(), Comparator.comparing(Job::getB));
    }

    public String printInfo() {
        return "SubProblem with h: " + getH() + " k: " + getProblem().getK() + " SUM_P: " + getProblem().getSumOfP();
    }
}
