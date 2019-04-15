package datastructures;

import java.util.LinkedList;

public class LinkedListApp {
    LinkedList<Integer> queue = new LinkedList<>();

    void produce(){
        int el = (int) (Math.random() * 100);
        queue.addFirst(el);
    }

    int get(){
        return queue.pollLast();
    }

    void print(){
        System.out.println(queue);
    }

    public static void main(String[] args) {
        LinkedListApp app = new LinkedListApp();
//        for (int i = 0; i < 10; i++) {
//            app.produce();
//            app.print();
//            if(app.queue.size() > 2){
//                System.out.println(app.get());
//            }
//        }
        System.out.println("Apple" +
                " costs " +
                + '2'
                + " USD");
    }
}
