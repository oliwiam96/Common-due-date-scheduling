package com.oliwia;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello Oli!");

//        InputFile sch10 = new InputFile(100);
//        SubProblem k2h0_4 = sch10.getKthProblemWithH(2, 0.2);
        int inputN = Integer.parseInt(args[0]);
        int inputK = Integer.parseInt(args[1]);
        double inputH = Double.parseDouble(args[2]);

//        InputFile sch10 = new InputFile(1000);
//        SubProblem k2h0_4 = sch10.getKthProblemWithH(4, 0.2);
//        System.out.println(k2h0_4.getInfo());
//        k2h0_4.solve();
//        k2h0_4.saveSolutionToFile();

        InputFile inputFile = new InputFile(inputN);
        SubProblem subProblem = inputFile.getKthProblemWithH(inputK, inputH);
        subProblem.solve();
        subProblem.saveSolutionToFile();
    }
}
