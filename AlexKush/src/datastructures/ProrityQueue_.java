package datastructures;

import java.util.PriorityQueue;

public class ProrityQueue_ {
    private static int[] data;
    private static int count;

    public ProrityQueue_(int size) {
        data = new int[size];
    }




    private static void shift(int pos){
        for (int i = count-1; i >= pos; i--) {
            data[i+1] = data[i];
        }
        count++;
    }

    private static void insert(int pos, int val){
        data[pos] = val;
    }

    private static int findPos(int val) {
        int left = 0;
        int right = count - 1;
        while (left <= right){
            int middle = (right + left) / 2;
            if(val > data[middle]){
                left = middle+1;
            } else if(val < data[middle]){
                right = middle-1;
            } else {
                return middle;
            }
        }
        return left;
//
//        for (int i = 0; i < count; i++) {
//            if(data[i] >= val){
//                return i;
//            }
//        }
//        return count;
    }

    private void resize() {
        int newLength = data.length << 1 | 1;
        int[] newData = new int[newLength];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public boolean contains(int val){
        int left = 0;
        int right = count - 1;

        while (left <= right){
            int middle = (left + right) / 2;
            System.out.println(left);
            System.out.println(right);
            System.out.println(data[middle]);
            if(val < data[middle]){
                right = middle - 1;
            } else if(val > data[middle]){
                left = middle + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public  void add(int val) {
        if(count >= data.length){
            resize();
        }
        int pos = findPos(val);
        shift(pos);
        insert(pos,val);
    }

    public int poll(){
        int res = data[0];
        for (int i = 1; i<=count;i++){
            data[i-1] = data[i];
        }
        count--;
        return res;
    }

    public void print(){
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (int i = 0; i <= count-1; i++) {
            if(first) {
                first = false;
            } else {
                sb.append(",");
            }
            sb.append(data[i]);
        }
        System.out.println(sb.append("]"));
    }

    public static void main(String[] args) {
        ProrityQueue_ pq = new ProrityQueue_(20);

        for (int i = 30; i <= 35; i++) {
            pq.add(i);
        }
        for (int i = 37; i <= 40; i++) {
            pq.add(i);
        }

        pq.print();
        pq.add(10);
        pq.add(36);
        pq.add(44);
        pq.print();
        System.out.println(pq.contains(10));
        System.out.println(pq.contains(10123));
    }


}
