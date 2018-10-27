package com.oliwia.checker;

import com.oliwia.common.FileReaderWriter;
import com.oliwia.solver.SubProblem;

public class Checker {
    public static void main(String[] args) {
        System.out.println("Checker by Oliwia Masian 127324");

        String inputBatName = args[0];
        String studentIndex = inputBatName.substring(0, inputBatName.length()-4);
        System.out.println(studentIndex);
        int inputN = Integer.parseInt(args[1]);
        int inputK = Integer.parseInt(args[2]);
        double inputH = Double.parseDouble(args[3]);

        // run bat and measure time

        SubProblem subProblem = FileReaderWriter.getKthProblemWithH(inputK, inputN, inputH);
        StringBuilder sb = new StringBuilder();
        sb.append("sch_").append(studentIndex).append("_")
                .append(inputN).append("_")
                .append(inputK).append("_")
                .append((int) (inputH*10)).append(".out");
        String fileNameToOpen = sb.toString();
        int declaredCost = FileReaderWriter.getDeclaredCostAndSetOrder(subProblem, fileNameToOpen);
        System.out.println("Declared cost: " + declaredCost + " calculated cost: " + subProblem.getCostFunction());
        if(declaredCost != subProblem.getCostFunction()){
            System.out.println("ERROR! COSTS ARE DIFFERENT!");
        } else{
            System.out.println("Costs are equal");
        }


    }
    private void check(){

    }
}
