package com.company;

import static java.lang.Math.abs;

public class GaussMethod { //Решение уравнений методом Гаусса
    private static int[][] indexMass;

    public static void setIndexMass(int size) {
        indexMass = new int[size][size+1];
    }

    public static double getDeterminant(double[][] a)
    {
        double det = 1;
        int size = a.length;
        for(int i = 0; i < size; i++)
            det = det * a[i][i];

        return det;
    }

    public static double[] getRootsNew(double[][] matrix)
    {
        int n = matrix.length;
        double[] roots = new double[n];

        int writeOutCnt = n-1;
        for(int i = n-1; i >= 0; i--){
            if(i == n-1){
                roots[indexMass[n-1][writeOutCnt]-1] = matrix[i][n]/matrix[i][n-1];
            }else{
                double root = matrix[i][n];
                int point = 0;
                for(int j = n-1; j >= 0 && matrix[i][j] != 0; j--){
                    if(roots[indexMass[n-1][j]-1] != 0) {
                        root -= matrix[i][j] * roots[indexMass[n - 1][j] - 1];
                    }
                    point = j;
                }
                roots[indexMass[n-1][writeOutCnt]-1] = root/matrix[i][point];
            }
            writeOutCnt--;
        }
        return roots;
    }

    public static double[] getDiscrepancyNew(double[][] matrix, double[] x)
    {
        int n = matrix.length;
        double[] dis = new double[n];

        for(int i = 0; i < n; i++){
            double r = matrix[i][n];
            for(int j = 0; j < n; j++){
                r -= matrix[i][j]*x[j];
            }

            dis[i] = r;
        }
        return dis;
    }

    public static void printMtx(double[][] a)
    {
        int size = a.length;
        for (double[] doubles : a) {
            for (int j = 0; j < size + 1; j++) {
                System.out.printf("%.2f\t", doubles[j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static double[][] getTriangleNew(double[][] matrix)
    {
        int n = matrix.length;

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                indexMass[i][j] = j+1;


        for(int i = 0; i < n; i++) {
            System.out.println("-------");
            System.out.println("Начало " + (i+1) + "й итерации");
            int point = 0;
            double max = 0;
            for (int j = i; j < n; j++) {
                if (max < abs(matrix[j][i])) {
                    max = abs(matrix[j][i]);
                    point = j;
                }
            }
            System.out.print("Максимальный элемент столбца: ");
            System.out.printf("%.2f\t", max);
            System.out.println();

            if (max == 0) { //Система не решается, выходим
                return null;
            }

            if(point != i)
                System.out.println("Переставляем строки " + (point + 1) + " и " + (i+1));
            else System.out.println("Перестановка не требуется");


            //ставим max элемент на начало строки от 0
            for (int j = i; j <= n; j++) {
                double temp = matrix[i][j];
                matrix[i][j] = matrix[point][j];
                matrix[point][j] = temp;

                int t = indexMass[i][j];
                indexMass[i][j] = indexMass[point][j];
                indexMass[point][j] = t;
            }

            System.out.println("Матрица после перестановки:");
            printMtx(matrix);

            for (int k = n; k >= i; k--)
                matrix[i][k] = matrix[i][k] / matrix[i][i];

            for (int k = i + 1; k < n; k++)
                for (int j = n; j >= i; j--)
                    matrix[k][j] = matrix[k][j] - matrix[k][i] * matrix[i][j];


            System.out.println("Мтрица после " + (i+1) + "го преобразования:");
            printMtx(matrix);
            System.out.println("-------");

        }
        return matrix;
    }
}