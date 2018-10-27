package com.oliwia;

public class App {
    public static void main(String[] args) {
        System.out.println("Oliwia Masian 127324");

        int inputN = Integer.parseInt(args[0]);
        int inputK = Integer.parseInt(args[1]);
        double inputH = Double.parseDouble(args[2]);

        SubProblem subProblem = FileReaderWriter.getKthProblemWithH(inputK, inputN, inputH);
        subProblem.solve();
        FileReaderWriter.saveSolutionToFile(subProblem);
    }
}
