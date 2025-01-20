package com.java.dsa;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {

        int[] arr = {2, 5, 1, 6, 3};

        mergeSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));

    }

    private static void mergeSort(int[] arr, int left, int right) {

        if(left < right){

            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);

        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] lArr = new int[n1];
        int[] rArr = new int[n2];

        for(int x = 0; x < n1; x++){
            lArr[x] = arr[left + x];
        }

        for(int x = 0; x < n2; x++){
            rArr[x] = arr[mid + 1 + x];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j <n2){
            if(lArr[i] < rArr[j]){
                arr[k] = lArr[i];
                i++;
            }
            else {
                arr[k] = rArr[j];
                j++;
            }
            k++;
        }

        while (i < n1){
            arr[k] = lArr[i];
            i++;
            k++;
        }
        while (j < n2){
            arr[k] = rArr[j];
            j++;
            k++;
        }

    }
}