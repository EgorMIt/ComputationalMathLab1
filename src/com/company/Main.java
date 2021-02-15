package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int size = 0;
        ArrayList<Double> arrayList = new ArrayList<>();
        Scanner inConsole = new Scanner(System.in);
        System.out.println("Введите: 1 - для ввода с консоли; 2 - для файла");
        int num = inConsole.nextInt();
        if(num == 1)
        {
            System.out.println("Укажите размерносить матрицы: ");
            size = inConsole.nextInt();
            System.out.println("Введите коффициенты в консоль:");
            for(int i = 0; i < size*size+size; i++)
            {
                arrayList.add(inConsole.nextDouble());
            }

        }
        else if(num == 2) {
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
        }
        System.out.println("Размерность матрицы: ");
        System.out.println(size);
        System.out.println();

        double[][] mtx = new double[size][size+1];
        int index=0;
        for(int i = 0; i< size; i++)
        {
            for(int j = 0; j <size+1;j++)
            {
                mtx[i][j] = arrayList.get(index);
                index++;
            }
        }

        /*for(int i = 0; i< size; i++)
        {
            for(int j = 0; j <size+1;j++)
            {
               System.out.print(mtx[i][j] + " ");
            }
            System.out.println();
        }*/


        double[][] triangleMtx = GaussMethod.getTriangleMtx(mtx, size);
        if(triangleMtx != null) {
            System.out.println("Получена треугольная матрица: ");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size + 1; j++) {
                    System.out.print(triangleMtx[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            System.out.println("Определитель матрицы равен: ");
            System.out.println(GaussMethod.getDeterminant(triangleMtx, size));
            System.out.println();

            double[] x = GaussMethod.getRoots(triangleMtx, size);
            System.out.println("Найдены корни СЛАУ:");
            for (double v : x) System.out.println(v + " ");
            System.out.println();

            System.out.println("Вектор невязки: ");
            double[] dis = GaussMethod.getDiscrepancy(mtx, x);
            for (double di : dis) System.out.println(di + " ");
        }
        else System.out.println("Ошибка в подсчете матрицы или система не имеет решений!");



    }
}
