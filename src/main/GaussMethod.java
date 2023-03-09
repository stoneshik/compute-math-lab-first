package main;

public class GaussMethod {
    public static ExtendedMatrix conversionToTriangularMatrix(ExtendedMatrix extendedMatrix) {
        SquareMatrix squareMatrix = extendedMatrix.matrix();
        double[][] matrix = squareMatrix.matrix();
        int n = squareMatrix.matrix().length;
        double max;
        double[] vectorFreeVariables = extendedMatrix.vectorFreeVariables();
        for (int i = 0; i < n; i++) {
            squareMatrix.setPoint(0);
            System.out.println("=======");
            System.out.printf("Начало %d-й итерации\n", i + 1);
            max = squareMatrix.findMaxElementInColumn(i);
            System.out.printf("Максимальный элемент столбца: %.2f\t\n", max);

            if (max == 0) {  // Система не решается
                return null;
            }

            if (squareMatrix.getPoint() != i) {
                System.out.printf("Переставляем строки %d и %d\n", squareMatrix.getPoint() + 1, i + 1);
            } else {
                System.out.println("Перестановка не требуется");
            }

            // Ставим максимальный элемент на начало строки от 0
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
            System.out.printf("Матрица после %d-го преобразования:\n", i + 1);
            printMatrix(extendedMatrix);
            System.out.print("=======\n\n");
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
    }

    public static void printRoots(ExtendedMatrix extendedMatrix) {
        double[] roots = extendedMatrix.getRoots();
        if (roots == null) {
            System.out.println("Матрица не треугольная");
            return;
        }
        System.out.println("Найденые корни СЛАУ:");
        for (double v : roots) {System.out.printf("%.2f\t", v);}
    }

    public static void printDiscrepancy(ExtendedMatrix extendedMatrix) {
        System.out.println("Вектор невязки:");
        double[] roots = extendedMatrix.getRoots();
        if (roots == null) {
            System.out.println("Матрица не треугольная");
            return;
        }
        double[] dis = extendedMatrix.getDiscrepancy(roots);
        for (double di : dis) {System.out.printf("%.24f\t", di);}
    }
}
