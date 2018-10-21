package com.oliwia;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello Oli!");

        InputFile sch10 = new InputFile(10);
        System.out.println(sch10.getKthProblemWithH(2, 0.4).printInfo());
    }
}
