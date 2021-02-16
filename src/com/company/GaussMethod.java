package com.company;

public class GaussMethod { //Решение уравнений методом Гаусса

    public static double[][] getTriangleMtx(double[][] a, int size) {
        int k, m, i, j;
        double ak, bk;
        for (k = 0; k < size; k++) {
            ak = Math.abs(a[k][k]);
            i = k;
            for (m = k + 1; m < size; m++)
                if (Math.abs(a[m][k]) > ak) {
                    i = m;
                    ak = Math.abs(a[m][k]);
                }

            if (ak == 0)   //проверка на нулевой элемент
                return null;

            if (i != k)  //  перестановка i-ой строки, содержащей главный элемент k-ой строки
                for (j = k; j < size + 1; j++) {
                    bk = a[k][j];
                    a[k][j] = a[i][j];
                    a[i][j] = bk;
                }

            ak = a[k][k]; //преобразование k-ой строки, вычисление множетелей
            a[k][k] = 1;
            for (j = k + 1; j < size + 1; j++)
                a[k][j] = a[k][j] / ak;
            for (i = k + 1; i < size; i++) //преобразование строк с помощью k-ой строки
            {
                bk = a[i][k];
                a[i][k] = 0;
                if (bk != 0)
                    for (j = k + 1; j < size + 1; j++)
                        a[i][j] = a[i][j] - bk * a[k][j];
            }
        }
        return a;
    }

    public static double getDeterminant(double[][] a, int size)
    {
        double det = 1;
        for(int i = 0; i < size; i++)
            det = det * a[i][i];

        return det;
    }

    public static double[] getRoots(double[][] a, int size)
    {
        int i, j;
        double s;
        double[] x = new double[size];

        x[size-1] = a[size-1][size] / a[size-1][size-1];
        for (i = size-1; i >= 0; i--)   //Нахождение решений СЛАУ
        {
            s = 0;
            for (j = size-1; j > i; j--)
                s = s + a[i][j] * x[j];
            x[i] = (a[i][size] - s) / (a[i][i]);
        }

        return x;
    }

    public static double[] getDiscrepancy(double[][] a, double[] x)
    {
        int size = x.length;
        double[] h = new double[size];
        double[] dis = new double[size];
        for (int i = 0; i < size; i++)
        {
            h[i] = 0;
            for (int j = 0; j < size; j++)
                h[i] = h[i] + x[i] * a[i][j];

            dis[i] = Math.abs((a[i][size] - h[i]));
        }

        return dis;
    }
}
