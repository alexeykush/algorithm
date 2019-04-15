package sort;

import algorithms.sort.Utils;

import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
        int[] arr = Utils.create_random_data(10);

        int[] sorted = sort(arr);
        System.out.println(Arrays.toString(sorted));
    }

    public static int[] sort(int[] origin){
        int[] process =  origin.clone();
        for (int i = 0; i < process.length; i++) {
            for (int j = 0; j < process.length-i-1; j++) {
                if(process[j]>process[j+1]){
                    swap(process,j,j+1);
                }
            }
        }
        return process;
    }

    public static void swap(int[] origin,int i,int j){
        int tmp = origin[i];
        origin[i] = origin[j];
        origin[j] = tmp;
    }
}
