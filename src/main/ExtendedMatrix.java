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

    public void updateVectorFreeVariables(int j, double value) {
        this.VECTOR_FREE_VARIABLES[j] = value;
    }

    public void updateMatrix(int i, int j, double value) {
        this.MATRIX.matrix()[i][j] = value;
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
        for (int j = firstLineNum; j < this.MATRIX.matrix().length; j++) {
            temp = this.MATRIX.matrix()[firstLineNum][j];
            this.MATRIX.matrix()[firstLineNum][j] = this.MATRIX.matrix()[secondLineNum][j];
            this.MATRIX.matrix()[secondLineNum][j] = temp;
        }
    }

    /**
     * Получение корней СЛАУ из треугольной матрицы
     * @return вектор корней уравнения СЛАУ
     */
    public double[] getRoots() {
        double[][] matrix = this.MATRIX.matrix();
        int n = matrix.length;
        double[] roots = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                roots[i] += (matrix[i][j] * roots[j]);
            }
            roots[i] = (this.VECTOR_FREE_VARIABLES[i] - roots[i]) / matrix[i][i];
        }
        return roots;
    }

    /**
     * Получение вектора невязки
     * @param roots массив корней СЛАУ
     * @return вектор невязки
     */
    public double[] getDiscrepancy(double[] roots) {
        double[][] matrix = this.MATRIX.matrix();
        int n = matrix.length;
        double[] dis = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.VECTOR_FREE_VARIABLES[i] -= matrix[i][j] * roots[j];
            }
            dis[i] = this.VECTOR_FREE_VARIABLES[i];
        }
        return dis;
    }
}
