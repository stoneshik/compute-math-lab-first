package main;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static ExtendedMatrix loadMatrixFromCmd(Scanner in) {
        System.out.println("Введите размерность матрицы");
        int n = Integer.parseInt(in.nextLine());
        while (n < 1) {
            System.out.println("Ввод не распознан, повторите ввод");
            System.out.println("Введите размерность матрицы");
            n = Integer.parseInt(in.nextLine());
        }
        if (n == 1) {
            System.out.println("Размерность СЛАУ не может быть равна одному");
        } else if (n == 2) {
            System.out.println("Формат ввода: 'a11 a12 b1'");
            System.out.println("Коффициенты вводятся через пробел в строку:");
        } else {
            System.out.println("Формат ввода: 'a11 ... a1" + n + " b1'");
            System.out.println("Коффициенты вводятся через пробел в строку:");
        }
        String[] row;
        int i = 0;
        ExtendedMatrix extendedMatrix = new ExtendedMatrix(new SquareMatrix(n));
        while (i < n) {
            row = in.nextLine().split(" ");
            try {
                for (int j = 0; j < n; j++) {
                    extendedMatrix.updateMatrix(i, j, Double.parseDouble(row[j]));
                }
                extendedMatrix.updateVectorFreeVariables(i, Double.parseDouble(row[n]));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода! Проверьте, чтобы дробные числа были записаны через запятую");
                continue;
            }
            i++;
        }
        return extendedMatrix;
    }

    private static ExtendedMatrix loadMatrixFromFile() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./input/input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            return null;
        }
        int n;
        try {
            n = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка при чтении размерности СЛАУ");
            return null;
        }
        System.out.printf("Введенная размерность СЛАУ: %s\n", n);
        if (n < 1) {
            System.out.println("Размерность СЛАУ не может быть отрицательной");
        } else if (n == 1) {
            System.out.println("Размерность СЛАУ не может быть равна одному");
        }
        String[] row;
        int i = 0;
        ExtendedMatrix extendedMatrix = new ExtendedMatrix(new SquareMatrix(n));
        while (i < n) {
            try {
                row = reader.readLine().split(" ");
            } catch (IOException e) {
                System.out.println("Ошибка ввода при чтении файла");
                return null;
            }
            try {
                for (int j = 0; j < n; j++) {
                    if (j == n - 1) {
                        extendedMatrix.updateVectorFreeVariables(i, Double.parseDouble(row[n]));
                    }
                    extendedMatrix.updateMatrix(i, j, Double.parseDouble(row[j]));
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода! Проверьте, чтобы дробные числа были записаны через запятую");
                return null;
            }
            i++;
        }
        return extendedMatrix;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите: 1 - для ввода с консоли, 2 - для чтения файла");
        int type = Integer.parseInt(in.nextLine());
        while (type != 1 && type != 2) {
            System.out.println("Ввод не распознан, повторите ввод");
            System.out.println("Введите: 1 - для ввода с консоли, 2 - для чтения файла");
            type = Integer.parseInt(in.nextLine());
        }
        ExtendedMatrix extendedMatrix;
        switch (type) {
            case 1 -> extendedMatrix = loadMatrixFromCmd(in);
            case 2 -> extendedMatrix = loadMatrixFromFile();
            default -> extendedMatrix = null;
        }
        if (extendedMatrix == null) {
            return;
        }

        GaussMethod.printMatrix(extendedMatrix);
        ExtendedMatrix triangleMatrix = GaussMethod.conversionToTriangularMatrix(extendedMatrix);
        if (triangleMatrix != null) {
            System.out.println("Получена треугольная матрица: ");
            GaussMethod.printMatrix(triangleMatrix);

            System.out.println("Определитель матрицы равен: ");
            double det = triangleMatrix.matrix().getDeterminant();
            System.out.println(det);
            System.out.println();

            if (det != 0) {
                GaussMethod.printRoots(triangleMatrix);
                System.out.println();

                GaussMethod.printDiscrepancy(triangleMatrix);
                System.out.println();
            } else {
                System.out.println("Система имеет бесконечное множество решений!");
            }
        } else {
            System.out.println("Ошибка в подсчете матрицы или система не имеет решений!");
        }
    }
}
