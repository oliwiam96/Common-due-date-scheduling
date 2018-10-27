package com.oliwia.solver;

import com.oliwia.common.FileReaderWriter;

public class Solver {
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
