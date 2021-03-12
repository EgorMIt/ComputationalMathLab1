package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int size = 0;
        ArrayList<Double> arrayList = new ArrayList<>();
        Scanner inConsole = new Scanner(System.in);
        System.out.println("Введите: 1 - для ввода с консоли; 2 - для чтения файла");
        int num = inConsole.nextInt();
        while (!(num == 1 || num == 2))
        {
            System.out.println("Ошибка ввода!");
            System.out.println("Введите: 1 - для ввода с консоли; 2 - для чтения файла");
            num = inConsole.nextInt();
        }
        switch (num) {
            case 1 -> {
                System.out.println("Укажите размерносить матрицы: ");
                size = inConsole.nextInt();

                if (size == 1)
                    System.out.println("Размерность СЛАУ не может быть равна одному");
                else if (size == 2) {
                    System.out.println("Формат ввода: 'a11 a12 b1'");
                    System.out.println("Введите коффициенты через пробел:");
                } else {
                    System.out.println("Формат ввода: 'a11 ... a1" + size + " b1'");
                    System.out.println("Введите коффициенты через пробел:");
                }

                try {
                    for (int i = 0; i < size * size + size; i++)
                        arrayList.add(inConsole.nextDouble());
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка ввода!  Проверьте, что дробные числа записаны через запятую");
                }
            }
            case 2 -> {
                try {
                    FileInputStream path = new FileInputStream("res/input");
                    DataInputStream inFile = new DataInputStream(path);
                    BufferedReader br = new BufferedReader(new InputStreamReader(inFile));
                    String data;

                    while ((data = br.readLine()) != null) {
                        String[] tmp = data.split(" ");    //Split space
                        for (String s : tmp)
                            arrayList.add(Double.parseDouble(s));
                        size++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода!  Проверьте, что дробные числа записаны через точку");
                    System.exit(0);
                }
                System.out.println("Размерность матрицы: ");
                System.out.println(size);
                System.out.println();
            }
        }

        double[][] mtx = new double[size][size+1];
        double[][] mtxCopy = new double[size][size+1];
        int index = 0;
        for(int i = 0; i< size; i++)
            for(int j = 0; j <size+1;j++)
            {
                mtx[i][j] = mtxCopy[i][j] = arrayList.get(index);
                index++;
            }


        GaussMethod.printMtx(mtxCopy);
        GaussMethod.setIndexMass(size);
        double[][] triangleMtx = GaussMethod.getTriangleNew(mtx);
        if(triangleMtx != null) {
            System.out.println("Получена треугольная матрица: ");
            GaussMethod.printMtx(triangleMtx);

            System.out.println("Определитель матрицы равен: ");
            double det = GaussMethod.getDeterminant(triangleMtx);
            System.out.println(det);
            System.out.println();

            if (det != 0) {
                double[] x = GaussMethod.getRootsNew(triangleMtx);
                System.out.println("Найдены корни СЛАУ:");
                for (double v : x) System.out.printf("%.2f\t", v);
                System.out.println();
                System.out.println();

                System.out.println("Вектор невязки: ");
                double[] dis = GaussMethod.getDiscrepancyNew(mtxCopy, x);
                for (double di : dis) System.out.printf("%.2f\t", di);
                System.out.println();

            }
            else
                System.out.println("Система имеет бесконечное множество решений!");
        }
        else System.out.println("Ошибка в подсчете матрицы или система не имеет решений!");

    }
}
