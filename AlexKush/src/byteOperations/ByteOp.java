package byteOperations;

import java.util.Scanner;

public class ByteOp {
    private final static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
//        int x = in.nextInt();
        int x = 5;
        System.out.println(fromDecimalToBinary(x));
        String num = "10100";
        System.out.println(fromBinaryToDecimal(num));
    }

    public static int fromBinaryToDecimal(String x){
        int res = 0;
        char[] chars = x.toCharArray();
        for (int i = chars.length-1; i >= 0; i--) {
            if(chars[i] != '0'){
                res += Math.pow(2,chars.length - 1 - i);
            }
        }
        return res;
    }

    public static String fromDecimalToBinary(int x){
        String res = "";
        for (int i = 7; i >=0 ; i--) {
            res += (x>>i)&1;
        }
//      or

//        for (int i = 7; i >= 0; i--) {
//            res += (x&(1<<i))>>i;
//        }
        return res;
    }


}
