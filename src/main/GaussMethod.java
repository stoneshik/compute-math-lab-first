package main;

public class GaussMethod {
    public static ExtendedMatrix conversionToTriangularMatrix(ExtendedMatrix extendedMatrix) {
        SquareMatrix squareMatrix = extendedMatrix.matrix();
        double[][] matrix = squareMatrix.matrix();
        int n = squareMatrix.matrix().length;
        double max;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                extendedMatrix.getIndexArr()[i][j] = j + 1;
            }
        }
        double[] vectorFreeVariables = extendedMatrix.vectorFreeVariables();
        for (int i = 0; i < n; i++) {
            squareMatrix.setPoint(0);
            System.out.println("-------");
            System.out.println("Начало " + (i + 1) + "й итерации");
            max = squareMatrix.findMaxElementInColumn(i);
            System.out.print("Максимальный элемент столбца: ");
            System.out.printf("%.2f\t", max);
            System.out.println();

            if (max == 0) { //Система не решается, выходим
                return null;
            }

            if (squareMatrix.getPoint() != i) {
                System.out.println("Переставляем строки " + (squareMatrix.getPoint() + 1) + " и " + (i + 1));
            } else {
                System.out.println("Перестановка не требуется");
            }

            // ставим max элемент на начало строки от 0
            extendedMatrix.linePermutation(i, squareMatrix.getPoint());

            System.out.println("Матрица после перестановки:");
            printMatrix(extendedMatrix);

            vectorFreeVariables[i] = vectorFreeVariables[i] / matrix[i][i];
            for (int k = n - 1; k >= i; k--) {
                matrix[i][k] = matrix[i][k] / matrix[i][i];
            }
            for (int k = i + 1; k < n; k++) {
                vectorFreeVariables[k] = vectorFreeVariables[k] - matrix[k][i] * vectorFreeVariables[i];
                for (int j = n - 1; j >= i; j--) {
                    matrix[k][j] = matrix[k][j] - matrix[k][i] * matrix[i][j];
                }
            }
            System.out.println("Матрица после " + (i + 1) + "го преобразования:");
            printMatrix(extendedMatrix);
            System.out.println("-------");
        }
        return extendedMatrix;
    }

    public static void printMatrix(ExtendedMatrix extendedMatrix) {
        double[][] matrix = extendedMatrix.matrix().matrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length + 1; j++) {
                if (j == matrix.length) {
                    System.out.printf("|\t%.2f\n", extendedMatrix.vectorFreeVariables()[i]);
                    continue;
                }
                System.out.printf("%.2f\t", matrix[i][j]);
            }
        }
        System.out.println();
    }

    public static void printRoots(ExtendedMatrix extendedMatrix) {
        double[] roots = extendedMatrix.getRoots();
        System.out.println("Найдены корни СЛАУ:");
        for (double v : roots) {System.out.printf("%.2f\t", v);}
    }

    public static void printDiscrepancy(ExtendedMatrix extendedMatrix) {
        System.out.println("Вектор невязки: ");
        double[] dis = extendedMatrix.getDiscrepancy(extendedMatrix.getRoots());
        for (double di : dis) {System.out.printf("%.2f\t", di);}
    }
}
