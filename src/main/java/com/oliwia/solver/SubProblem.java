package com.oliwia.solver;

import java.util.*;

public class SubProblem {
    private double h;
    private Problem problem;
    private int dueDate;
    private List<Job> executedJobsInOrder;

    public SubProblem(double h, Problem problem) {
        this.h = h;
        this.problem = problem;
        this.executedJobsInOrder = new ArrayList<>();
        calculateDueDate();
    }

    public double getH() {
        return h;
    }

    public Problem getProblem() {
        return problem;
    }

    private void calculateDueDate() {
        dueDate = (int) Math.floor(h * problem.getSumOfP());
    }

    public int getCostFunction() {
        int time = 0;
        int cost = 0;
        for (Job job : executedJobsInOrder) {
            if (time + job.getP() < this.dueDate) {
                // earliness -> a
                cost += (this.dueDate - time - job.getP()) * job.getA();
            }
            if (time + job.getP() > this.dueDate) {
                // tardiness -> b
                cost += (time + job.getP() - this.dueDate) * job.getB();
            }
            time += job.getP();
        }
        return cost;
    }

    public List<Job> getExecutedJobsInOrder() {
        return executedJobsInOrder;
    }

    public String getInfo() {
        return "SubProblem with h: " + getH() + " k: " + getProblem().getK() + " SUM_P: " + getProblem().getSumOfP();
    }


    public void solve() {
        if (h <= 0.5) {
            solveGreedyByChoosingBeforeDueDate();
        } else {
            solveGreedyByChoosingAfterDueDate();
        }
        int cost = getCostFunction();
        System.out.println("Cost: " + cost);
    }

    public void solveGreedyByChoosingBeforeDueDate() {
        List<Job> leftJobsToInsert = new ArrayList<>(this.problem.getJobs());
        int time = 0;
        boolean wasShuffled = false;
        for (int i = 0; i < problem.getNumberOfJobs(); i++) {
            Job jobToInsert;
            if (time < dueDate) {
                jobToInsert = getBestJobBeforeDueDate(leftJobsToInsert, time);

            } else {
                if (!wasShuffled) {
                    shuffleBeforeDueDate();
                    wasShuffled = true;
                }
                jobToInsert = getJobWithMaxBExtended(leftJobsToInsert);
            }
            leftJobsToInsert.remove(jobToInsert);
            executedJobsInOrder.add(jobToInsert);
            time += jobToInsert.getP();
        }
    }

    public void solveGreedyByChoosingAfterDueDate() {
        // back in time
        List<Job> leftJobsToInsert = new ArrayList<>(this.problem.getJobs());
        int time = getProblem().getSumOfP();
        boolean wasShuffled = false;
        for (int i = 0; i < problem.getNumberOfJobs(); i++) {
            Job jobToInsert;
            if (time > dueDate) {
                jobToInsert = getBestJobBeforeEnd(leftJobsToInsert, time);

            } else {
                if (!wasShuffled) {
                    shuffleAfterDueDate();
                    wasShuffled = true;
                }
                jobToInsert = getJobWithMaxAExtended(leftJobsToInsert);
            }
            leftJobsToInsert.remove(jobToInsert);
            executedJobsInOrder.add(0, jobToInsert);
            time -= jobToInsert.getP();
        }
    }

    private void shuffleBeforeDueDate() {
        List<Job> leftJobsToInsert = new ArrayList<>(executedJobsInOrder);
        executedJobsInOrder.clear();
        while (!leftJobsToInsert.isEmpty()) {
            Job jobToInsert = getJobWithMinAExtended(leftJobsToInsert);
            leftJobsToInsert.remove(jobToInsert);
            executedJobsInOrder.add(jobToInsert);
        }
    }

    private void shuffleAfterDueDate() {
        List<Job> leftJobsToInsert = new ArrayList<>(executedJobsInOrder);
        executedJobsInOrder.clear();
        while (!leftJobsToInsert.isEmpty()) {
            Job jobToInsert = getJobWithMaxBExtended(leftJobsToInsert);
            leftJobsToInsert.remove(jobToInsert);
            executedJobsInOrder.add(jobToInsert);
        }
    }

    public void solveGreedy() {
        List<Job> leftJobsToInsert = new ArrayList<>(this.problem.getJobs());
        int time = 0;
        for (int i = 0; i < problem.getNumberOfJobs(); i++) {
            Job jobToInsert;
            if (time < dueDate) {
                jobToInsert = getJobWithMinAExtended(leftJobsToInsert);
            } else {
                jobToInsert = getJobWithMaxBExtended(leftJobsToInsert);
            }
            leftJobsToInsert.remove(jobToInsert);
            executedJobsInOrder.add(jobToInsert);
            time += jobToInsert.getP();
        }
    }

    public void solveGreedyByAlwaysChoosingMaxB() {
        List<Job> leftJobsToInsert = new ArrayList<>(this.problem.getJobs());
        int time = 0;
        for (int i = 0; i < problem.getNumberOfJobs(); i++) {
            Job jobToInsert;
            if (time < dueDate) {
                jobToInsert = getJobWithMaxB(leftJobsToInsert);
            } else {
                jobToInsert = getJobWithMaxBExtended(leftJobsToInsert);
            }
            leftJobsToInsert.remove(jobToInsert);
            executedJobsInOrder.add(jobToInsert);
            time += jobToInsert.getP();
        }
    }

    public void solveRandomly() {
        Random generator = new Random();
        List<Job> leftJobsToInsert = new ArrayList<>(this.problem.getJobs());
        for (int i = 0; i < problem.getNumberOfJobs(); i++) {
            int index = generator.nextInt(leftJobsToInsert.size());
            Job nextJob = leftJobsToInsert.remove(index);
            executedJobsInOrder.add(nextJob);
        }
    }

    private Job getJobWithMaxA(List<Job> jobsToCheck) {
        return Collections.max(jobsToCheck, Comparator.comparing(Job::getA));

    }

    private Job getJobWithMaxB(List<Job> jobsToCheck) {
        return Collections.max(jobsToCheck, Comparator.comparing(Job::getB));
    }

    private Job getJobWithMaxAExtended(List<Job> jobsToCheck) {
        return Collections.max(jobsToCheck, (o1, o2) -> Integer.compare(o1.getA() * o2.getP(), o2.getA() * o1.getP()));
    }

    private Job getJobWithMaxBExtended(List<Job> jobsToCheck) {
        return Collections.max(jobsToCheck, (o1, o2) -> Integer.compare(o1.getB() * o2.getP(), o2.getB() * o1.getP()));
    }

    private Job getJobWithMinAExtended(List<Job> jobsToCheck) {
        return Collections.min(jobsToCheck, (o1, o2) -> Integer.compare(o1.getA() * o2.getP(), o2.getA() * o1.getP()));
    }

    private Job getJobWithMinA(List<Job> jobsToCheck) {
        return Collections.min(jobsToCheck, Comparator.comparing(Job::getA));

    }

    private Job getJobWithMinB(List<Job> jobsToCheck) {
        return Collections.min(jobsToCheck, Comparator.comparing(Job::getB));
    }

    private Job getBestJobBeforeDueDate(List<Job> jobsToCheck, int time) {
        return Collections.min(jobsToCheck, Comparator.comparing(job -> {
            double coefficient = ((double) (dueDate - time)) / dueDate;
            return job.getA() * coefficient - job.getB() * (1 - coefficient);
        }));
    }

    private Job getBestJobBeforeEnd(List<Job> jobsToCheck, int time) {
        return Collections.min(jobsToCheck, Comparator.comparing(job -> {
            double coefficient = ((double) (time - dueDate)) / (problem.getSumOfP() - dueDate);
            return job.getB() * coefficient - job.getA() * (1 - coefficient);
        }));
    }
}
