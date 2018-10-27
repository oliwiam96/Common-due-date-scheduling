package com.oliwia;

public class Job {
    private int p;
    private int a;
    private int b;
    private int indexInInputFile;

    public Job(int p, int a, int b, int indexInInputFile) {
        this.p = p;
        this.a = a;
        this.b = b;
        this.indexInInputFile = indexInInputFile;
    }

    public int getP() {
        return p;
    }

    public int getA() {
        return a;
    }


    public int getB() {
        return b;
    }

    public int getIndexInInputFile() {
        return indexInInputFile;
    }
}
