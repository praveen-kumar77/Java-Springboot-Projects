package com.java.dsa;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {

        int[] a = {2, 1, 9, 5, 44, 13, 6, 3, 32, 20};

        int iter = 0;

        for(int i = 0; i < a.length - 1; i++){
            int temp = 0;
            iter = 0;
            for(int j = 0; j < a.length - i - 1; j++){
                if(a[j] > a[j+1]){
                    temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                }
                iter++;
            }
        }
        System.out.println(Arrays.toString(a));

    }
}
