package datastructures;

import datastructures.interfaces.IList;

public class LList implements IList {

    class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
            next = null;
        }

        boolean hasNext(){
            return next != null;
        }
    }

    private Node head = null;

    public LList(){}

    private Node findLastNode() {
        Node current = head;
        while (current.hasNext()){
            current = current.next;
        }
        return current;
    }

    private Node findByValue(int el){
        Node current = head;
        while (current != null){
            if(current.value == el){
                return current;
            }
            current = current.next;
        }
       throw new IllegalArgumentException(String.format("element %d not found",el));
    }

    public boolean contains(int el){
        Node current = head;
        while (current != null){
            if(current.value == el){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void insertBefore(int val, int newVal){
        Node newNode = new Node(newVal);
    }

    public void insertAfter(int val, int newVal){
        Node newNode = new Node(newVal);
        Node current = findByValue(val);
        newNode.next = current.next;
        current.next = newNode;
    }

    @Override
    public void add(int el) {
        Node current = new Node(el);
        if(head == null){
            head = current;
        } else {
            Node last = findLastNode();
            last.next = current;
        }
    }

    @Override
    public int get(int index) {
        throw new IllegalStateException("not implemented by design...");
    }

    @Override
    public int size() {
        int size = 0;
        Node current = head;
        while (current != null){
            size++;
            current = current.next;
        }
        return size;
    }

    @Override
    public void remove(int index) {
        throw new IllegalStateException("not implemented by design...");
    }

    @Override
    public void remove() {
        throw new IllegalStateException("not implemented by design...");
    }

    @Override
    public void print(){
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        if(current != null) {
            while (current.hasNext()) {
                sb.append(current.value);
                sb.append(",");
                current = current.next;
            }
            sb.append(current.value);
        }
        System.out.println(sb.append("]"));
    }

    public void removeByValue(int val) {
        Node current = head;
        while (current.hasNext()){
            if(current.next.value == val){
                Node nextNode = current.next;
                current.next = nextNode.next;
                return;
            }
            current = current.next;
        }
        throw new IllegalArgumentException(String.format("Element %d not found",val));
    }


    public static void main1(String[] args) {
        LList lList = new LList();
        lList.print();
        System.out.println(lList.size());
        lList.add(11);
        lList.print();
        System.out.println(lList.size());
        lList.add(22);
        lList.print();
        System.out.println(lList.size());
    }

    public static void main2(String[] args) {
        LList ll = new LList();
        ll.add(11);
        ll.add(22);
        ll.add(33);
        System.out.println(ll.contains(22));
        System.out.println(ll.contains(23));
    }

    public static void main3(String[] args) {
        LList ll = new LList();
        ll.add(11);
        ll.add(22);
        ll.add(22);
        ll.add(33);
        ll.print();
        ll.insertAfter(22,30);
        ll.print();
    }

    public static void main4(String[] args) {
        LList ll = new LList();
        ll.add(11);
        ll.add(22);
        ll.add(33);
        ll.add(44);
        ll.add(55);
        ll.print();
        ll.removeByValue(33);
        ll.print();
        ll.removeByValue(55);
        ll.print();
    }

    public static void main(String[] args) {
        LList ll = new LList();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(12);
        LList ll2 = new LList();
        ll2.add(101);
        ll2.add(102);
        ll2.add(103);
//        ll.print();
//        ll2.print();
        sort(ll,ll2);
    }

    public static void sort(LList firstList,LList secondList){
        LList result = new LList();
        Node firstListNode = firstList.head;
        Node secondListNode = secondList.head;
        while (firstListNode != null && secondListNode != null){
            if(firstListNode.value < secondListNode.value){
                result.add(firstListNode.value);
                firstListNode = firstListNode.next;
            } else {
                result.add(secondListNode.value);
                secondListNode = secondListNode.next;
            }
        }
        while (firstListNode != null){
            result.add(firstListNode.value);
            firstListNode = firstListNode.next;
        }

        while (secondListNode != null){
            result.add(secondListNode.value);
            secondListNode = secondListNode.next;
        }

        result.print();
    }

}
