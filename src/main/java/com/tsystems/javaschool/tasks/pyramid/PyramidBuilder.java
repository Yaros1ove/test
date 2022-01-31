package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;

public class PyramidBuilder {
    int[][] pyramid;

    public int[][] buildPyramid(List<Integer> inputNumbers) throws CannotBuildPyramidException{
        int[] array;
        try {
            array = new int[inputNumbers.size()];
        } catch (OutOfMemoryError e) {
            throw new CannotBuildPyramidException();
        }
        for (int i = 0; i < inputNumbers.size(); i++) {
            try {
                array[i] = inputNumbers.get(i);
            } catch (NullPointerException e) {
                throw new CannotBuildPyramidException();
            }
        }
        quickSort(array, 0, array.length - 1);
        int counter;
        counter = checkFibonacci(array.length);
        int length = getLength(counter);
        
        pyramid = pyramidCreate(array, counter, length);
        return pyramid;
    }

    /**
     * создание пирамиды
     * @param array массив входных чисел
     * @param counter кол-во строк
     * @param length кол-во элементов в строке
     * @return получившаяся пирамида
     */
    int[][] pyramidCreate(int[] array, int counter, int length){
        int indexOFArray = 0;
        int[][] pyramid = new int[counter][length];
        int position = length / 2;
        int offset = 0;
        for (int i = 0; i < counter; i++) {
            int j;
            for (j = 0; j < position-offset; j++) {
                pyramid[i][j] = 0;
            }
            for (int k = 1; k <= (i+1) + (i+1) - 1; k++) {
                if (k % 2 != 0) {
                    pyramid[i][j+k-1] = array[indexOFArray];
                    indexOFArray++;
                } else {
                    pyramid[i][j+k] = 0;
                }
            }
            for (int l = position+offset+1; l < length; l++) {
                pyramid[i][l] = 0;
            }
            offset++;
        }
        return pyramid;
    }

    /**
     * найти кол-во элементов в строке
     * @param counter кол-во строк
     * @return кол-во элементов в строке
     */
    int getLength(int counter) {
        int length = 0;
        for (int i = 1; i <= counter; i++) {
            length = i;
        }
        length = length + length - 1;
        return length;
    }

    /**
     * проверка на возможность создания пирамиды
     * @param length кол-во элементов во входном массиве
     * @return кол-во строк в пирамиде
     * @throws CannotBuildPyramidException
     */
    int checkFibonacci(int length) throws CannotBuildPyramidException {
        int sum = 0;
        int counter = 0;
        while (sum < length) {
            counter++;
            sum = sum + counter;
            if (sum > length) {
                throw new CannotBuildPyramidException();
            }
        }
        return counter;
    }

    /**
     * быстрая сортировка
     * @param array входной массив
     * @param low
     * @param high
     */
    void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        int opora = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
}
