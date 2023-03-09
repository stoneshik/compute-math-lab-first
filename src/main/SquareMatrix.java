package main;

import static java.lang.Math.abs;

public class SquareMatrix {
    protected final double[][] MATRIX;
    protected int point;

    public SquareMatrix(int n) {
        this.MATRIX = new double[n][n];
        this.point = 0;
    }

    public double[][] matrix() {
        return this.MATRIX;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
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
                this.point = j;
            }
        }
        return max;
    }

    /**
     * Проверка является ли матрица верхнетреугольной
     * @return является или нет
     */
    public boolean isTriangleMatrix() {
        if (this.MATRIX.length <= 1) {
            return false;
        }
        for (int i = 1; i < this.MATRIX.length; i++) {
            for (int j = 0; j < i; j++) {
                if (this.MATRIX[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
