package main;

public class ExtendedMatrix {
    private final SquareMatrix MATRIX;
    private final double[] VECTOR_FREE_VARIABLES;

    public ExtendedMatrix(SquareMatrix matrix) {
        this.MATRIX = matrix;
        this.VECTOR_FREE_VARIABLES = new double[matrix.matrix().length];
    }

    public SquareMatrix matrix() {
        return this.MATRIX;
    }

    public double[] vectorFreeVariables() {
        return this.VECTOR_FREE_VARIABLES;
    }

    public void updateValue(int j, double value) {
        this.VECTOR_FREE_VARIABLES[j] = value;
    }

    /**
     * Перестановка двух строк местами в матрице
     * Комментарии параметров данны к методу Гаусса
     * @param firstLineNum текущая строка при итерации
     * @param secondLineNum строка с максимальным элементом
     */
    public void linePermutation(int firstLineNum, int secondLineNum) {
        double temp = this.VECTOR_FREE_VARIABLES[firstLineNum];
        this.VECTOR_FREE_VARIABLES[firstLineNum] = this.VECTOR_FREE_VARIABLES[secondLineNum];
        this.VECTOR_FREE_VARIABLES[secondLineNum] = temp;
        for (int j = firstLineNum; j <= this.MATRIX.matrix().length; j++) {
            temp = this.MATRIX.matrix()[firstLineNum][j];
            this.MATRIX.matrix()[firstLineNum][j] = this.MATRIX.matrix()[secondLineNum][j];
            this.MATRIX.matrix()[secondLineNum][j] = temp;
        }
    }
}
