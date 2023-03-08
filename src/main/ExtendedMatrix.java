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
        for (int j = firstLineNum; j <= this.MATRIX.matrix().length; j++) {
            temp = this.MATRIX.matrix()[firstLineNum][j];
            this.MATRIX.matrix()[firstLineNum][j] = this.MATRIX.matrix()[secondLineNum][j];
            this.MATRIX.matrix()[secondLineNum][j] = temp;
        }
    }

    /**
     * Получение корней СЛАУ
     * @return вектор корней уравнения СЛАУ
     */
    public double[] getRoots() {
        double[][] matrix = this.MATRIX.matrix();
        int n = matrix.length;
        double[] roots = new double[n];

        int writeOutCnt = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1){
                roots[indexMass[writeOutCnt] - 1] = matrix[i][n] / matrix[i][n - 1];
            } else {
                double root = matrix[i][n];
                int point = 0;
                for (int j = n - 1; j >= 0 && matrix[i][j] != 0; j--) {
                    if(roots[indexMass[n - 1][j] - 1] != 0) {
                        root -= matrix[i][j] * roots[indexMass[n - 1][j] - 1];
                    }
                    point = j;
                }
                roots[indexMass[n - 1][writeOutCnt] - 1] = root / matrix[i][point];
            }
            writeOutCnt--;
        }
        return roots;
    }


    /**
     * Получение вектора невязки
     * @return вектор невязки
     */
    public double[] getDiscrepancy(double[] roots) {
        double[][] matrix = this.MATRIX.matrix();
        int n = matrix.length;
        double[] dis = new double[n];
        for (int i = 0; i < n; i++) {
            double r = matrix[i][n];
            for (int j = 0; j < n; j++) {
                r -= matrix[i][j] * roots[j];
            }
            dis[i] = r;
        }
        return dis;
    }
}
