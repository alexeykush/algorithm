package extra;

import java.util.Arrays;

public class Buffer {
    private static int[] buffer = new int[32];
    private static int index = 0;

    public static void main(String[] args) {
        for (int i = 1; i <= 64; i++) {
            perform(i);
        }
        System.out.println(Arrays.toString(buffer));
    }

    public static void perform(int val){
        buffer[index++&31] = val;
    }
}
