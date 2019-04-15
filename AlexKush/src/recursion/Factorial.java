package recursion;

public class Factorial {

    public static int factorial(int n){
        if(n == 0){
            return 1;
        } else {
            return n * factorial(n-1);
        }
    }

    public static void main(String[] args) {
        int N = 6;
        int fact = factorial(N);
        System.out.println(fact);
    }
}