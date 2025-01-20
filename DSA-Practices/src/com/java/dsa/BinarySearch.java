package com.java.dsa;

public class BinarySearch {

    public static void main(String[] args) {

       int[] a = {2, 5, 7, 12, 23, 31, 50, 61, 72, 83};

       int start = 0;
       int end = a.length - 1;
       int find = 83;

       System.out.println(findNumPosition(start, end, find, a));

    }

    public static int findNumPosition(int start, int end, int find, int[] a){
        for(int i = 0; i < a.length; i++) {
            int mid = (start + end) / 2;
            if (a[mid] == find) {
                return mid;
            } else if (find < a[mid]) {
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
