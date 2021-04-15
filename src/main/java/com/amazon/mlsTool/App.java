package com.amazon.mlsTool;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        mlsRemove mr = new mlsRemove();
        mlsRemove.createAndStartService();
        mr.createDriver();
        int l = mr.ketData();
        System.out.println(l);
        for (int i = 1; i <= l; i++) {
            mr.getValues(i);
            try {
                mr.removeTheDumb();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mr.closewindow();
    }
}
