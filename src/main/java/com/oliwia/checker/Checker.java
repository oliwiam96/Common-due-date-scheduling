package com.oliwia.checker;

import com.oliwia.common.FileReaderWriter;
import com.oliwia.solver.SubProblem;

import java.io.IOException;

public class Checker {
    public static void main(String[] args) {
        System.out.println("Checker by Oliwia Masian 127324");

        String inputBatName = args[0];
        String studentIndex = inputBatName.substring(0, inputBatName.length() - 4);
        System.out.println(studentIndex);
        int inputN = Integer.parseInt(args[1]);
        int inputK = Integer.parseInt(args[2]);
        double inputH = Double.parseDouble(args[3]);
        StringBuilder toExecuteString = new StringBuilder();
        toExecuteString.append(inputBatName).append(" ")
                .append(inputN).append(" ")
                .append(inputK).append(" ")
                .append(inputH);
        try {
            long startTime = System.currentTimeMillis();
            Runtime
                    .getRuntime()
                    .exec(toExecuteString.toString())
                    .waitFor();
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("Elapsed time: " + elapsedTime + " ms");
            SubProblem subProblem = FileReaderWriter.getKthProblemWithH(inputK, inputN, inputH);
            StringBuilder outputFileName = new StringBuilder();
            outputFileName.append("sch_").append(studentIndex).append("_")
                    .append(inputN).append("_")
                    .append(inputK).append("_")
                    .append((int) (inputH * 10)).append(".out");
            String fileNameToOpen = outputFileName.toString();
            int declaredCost = FileReaderWriter.getDeclaredCostAndSetOrder(subProblem, fileNameToOpen);
            System.out.println("Declared cost: " + declaredCost + "; calculated cost: " + subProblem.getCostFunction());
            if (declaredCost != subProblem.getCostFunction()) {
                System.out.println("ERROR! COSTS ARE DIFFERENT!");
            } else {
                System.out.println("Costs are equal.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
