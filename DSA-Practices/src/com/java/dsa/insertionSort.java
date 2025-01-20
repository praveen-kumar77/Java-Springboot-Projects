package com.java.dsa;

import java.util.Arrays;
import java.util.List;

public class insertionSort {

    public static void main(String[] args) {

        int[] nums = {13, 6, 3, 32, 20, 40, 53, 12, 33, 2, 3};

        int[] sort1 = sortGen(nums);
        int[] sort2 = mySort(nums);

    }

    public static int[] sortGen(int[] nums){        // General method
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;

            while (j >= 0 && nums[j] > temp) {
                nums[j + 1] = nums[j];
                j--;
            }

            nums[j + 1] = temp;
        }
        return nums;
    }

    public static int[] mySort(int[] nums) {        // My version
        for (int i = 1; i < nums.length; i++) {
            int start = i;
            int temp = nums[start];

            for (int j = i - 1; j >= 0 && nums[j] > temp; j--) {
                nums[start--] = nums[j];
            }

            nums[start] = temp;
        }
        return nums;
    }

    }
