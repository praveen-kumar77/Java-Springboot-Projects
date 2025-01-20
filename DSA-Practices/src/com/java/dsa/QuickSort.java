package com.java.dsa;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {

        int[] nums = {13, 6, 3, 32, 20, 2, 1, 9, 5, 44, 32, 20};

        quickSort(nums, 0, nums.length - 1);

        System.out.println(Arrays.toString(nums));

    }

    private static void quickSort(int[] nums, int low, int high) {

        if(low < high){

            int pi = partition(nums, low, high);

            quickSort(nums, low, pi - 1);
            quickSort(nums, pi + 1, high);

        }

    }

    private static int partition(int[] nums, int low, int high) {

        int pivot = nums[high];
        int i = low - 1;

        for(int j = low; j < high; j++){
            if(nums[j] < pivot){
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        int temp = nums[i + 1];
        nums[i + 1] = nums[high];
        nums[high] = temp;

        return i + 1;
    }


}
