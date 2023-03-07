package main;

import static java.lang.Math.abs;

public class SquareMatrix {
    protected final double[][] MATRIX;

    public SquareMatrix(int n) {
        this.MATRIX = new double[n][n];
    }

    public double[][] matrix() {
        return this.MATRIX;
    }

    public void updateValue(int i, int j, double value) {
        this.MATRIX[i][j] = value;
    }

    public double getDeterminant() {
        double det = 1;
        for(int i = 0; i < this.MATRIX.length; i++) {
            det = det * this.MATRIX[i][i];
        }
        return det;
    }

    public double findMaxElementInColumn(int i) {
        double max = 0;
        for (int j = i; j < this.MATRIX.length; j++) {
            if (max < abs(this.MATRIX[j][i])) {
                max = abs(this.MATRIX[j][i]);
            }
        }
        return max;
    }
}
