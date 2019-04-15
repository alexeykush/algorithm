package sort;
import utils.Utils;

public class Merge {

    public static void main(String[] args) {
        int[] arr = Utils.create_random_data(20);
        Utils.printArray("source: ", arr);
        mergeSort(arr);
        Utils.printArray("sorted: ",arr);
    }

    public static void mergeSort(int[] arr) {
        if (arr.length == 1) {
            return;
        } else {
            int midIndex = arr.length / 2;
            int leftArr[] = new int[midIndex];
            int rightArr[] = new int[arr.length - midIndex];
            for (int i = 0; i < leftArr.length; i++) {
                leftArr[i] = arr[i];
            }
            for (int i = midIndex; i < arr.length; i++) {
                rightArr[i - midIndex] = arr[i];
            }
            mergeSort(leftArr);
            mergeSort(rightArr);
            sort(arr,leftArr,rightArr);

        }
    }

    private static void sort(int[] arr, int[] leftArr, int[] rightArr) {
        int i = 0,leftArrIndex=0,rightArrIndex=0;
        while (leftArrIndex < leftArr.length && rightArrIndex < rightArr.length){
            if(leftArr[leftArrIndex] < rightArr[rightArrIndex]){
                arr[i++] = leftArr[leftArrIndex++];
            } else {
                arr[i++] = rightArr[rightArrIndex++];
            }
        }
        while (leftArrIndex < leftArr.length){
            arr[i++] = leftArr[leftArrIndex++];
        }

        while (rightArrIndex < rightArr.length){
            arr[i++] = rightArr[rightArrIndex++];
        }
    }

}
