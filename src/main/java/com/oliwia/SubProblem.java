package com.oliwia;

public class SubProblem {
    private double h;
    private Problem problem;
    private int sumOfEarliness;
    private int sumOfTardiness;

    public SubProblem(double h, Problem problem) {
        this.h = h;
        this.problem = problem;
        solve();
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

    private void solve(){
        // TODO
    }
    public int getSolutionValue(){
        return sumOfEarliness + sumOfTardiness;
    }

    public void saveSolutionToFile(){
        // TODO
    }
}
