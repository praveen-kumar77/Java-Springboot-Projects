package com.java.dsa;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] a = {13, 6, 3, 32, 20, 40, 53, 12, 33, 2, 3};

        for(int i = 0; i < a.length - 1; i++){
            int minIndex = i;
            int temp = 0;
            for(int j = i + 1; j < a.length; j++){
                if(a[j] < a[minIndex]){
                    minIndex = j;
                }
            }
            temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;

        }

        System.out.println(Arrays.toString(a));
    }
}