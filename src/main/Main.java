package main;

import java.util.Scanner;

public class Main {
    private static ExtendedMatrix loadMatrixFromFile() {
        double[][] matrix = new double[1][1];
        SquareMatrix squareMatrix = new SquareMatrix(matrix.length);
        return new ExtendedMatrix(squareMatrix);
    }

    private static ExtendedMatrix loadMatrixFromCmd() {
        double[][] matrix = new double[1][1];
        SquareMatrix squareMatrix = new SquareMatrix(matrix.length);
        return new ExtendedMatrix(squareMatrix);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите: 1 - для ввода с консоли, 2 - для чтения файла");
        int num = in.nextInt();
        while (num != 1 && num != 2) {
            System.out.println("Ввод не распознан, повторите ввод");
            System.out.println("Введите: 1 - для ввода с консоли, 2 - для чтения файла");
            num = in.nextInt();
        }
        ExtendedMatrix extendedMatrix;
        switch (num) {
            case 1 -> extendedMatrix = loadMatrixFromCmd();
            case 2 -> extendedMatrix = loadMatrixFromFile();
        }
    }
}
