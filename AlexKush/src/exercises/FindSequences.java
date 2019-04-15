package exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSequences {
    private final static int[] data = {75,1,2,3,4,45,1,2,3,1,2,1,2,3,4};
    private final static Map<List<Integer>,Integer> counter = new HashMap<>();

    public static boolean compare(List<Integer> seq1, List<Integer> seq2){
        return seq1.equals(seq2);
    }

    public static void inc(List<Integer> key){
        if(counter.containsKey(key)){
            Integer val = counter.get(key);
            val++;
            counter.put(key,val);
        } else {
            counter.put(key,1);
        }
    }

    public static List<Integer> sequence(int index1, int len){
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = index1; i < index1+len; i++) {
            res.add(data[i]);
        }
        return res;
    }


    public static void main(String[] args) {
       final int len = data.length;
        for (int l = 3; l < len - 3; l++) {
            for (int left = 0; left < len - l; left++) {
                List<Integer> pattern = sequence(left, l);
                for (int i = 0; i < len - l + 1; i++) {
                    List<Integer> seq = sequence(i, l);
                    if(i != left){
                        if(compare(seq,pattern)){
                            inc(pattern);
                        }
                    }
                }
            }
        }
        counter
            .entrySet()
            .stream()
            .filter(e -> e.getValue() > 1)
            .forEach(k -> System.out.printf("pattern:%s, count:%d\n",k.getKey(),k.getValue()));
    }

}
