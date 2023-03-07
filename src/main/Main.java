package main;

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

    }
}
