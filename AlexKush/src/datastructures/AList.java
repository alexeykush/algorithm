package datastructures;

import datastructures.interfaces.IList;

public class AList implements IList {
    private final static int DEFAULT_SIZE = 4;
    private int[] data;
    private int position;

    public AList() {
        this(DEFAULT_SIZE);
    }

    public AList(int capacity) {
        this.data = new int[capacity];
        position = 0;
    }

    private void checkValid(int index) {
        if (index < 0 || index >= position) {
            throw new IllegalArgumentException("invalid index");
        }
    }

    private void resize() {
        int newLength = data.length << 1 | 1;
        int[] newData = new int[newLength];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public void add(int el) {
        if (position >= data.length) {
            resize();
        }
        data[position++] = el;
    }

    @Override
    public int get(int index) {
        checkValid(index);
        return data[index];
    }

    @Override
    public int size() {
        return position;
    }

    @Override
    public void remove(int index) {
        checkValid(index);
        int[] newData = new int[data.length];
        for (int i = 0; i < index; i++) {
            newData[i] = data[i];
        }
        for (int i = index + 1; i < position; i++) {
            newData[i-1] = data[i];
        }
        data = newData;
        position--;
    }

    @Override
    public void remove() {
        remove(position);
    }

    @Override
    public void print(){
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (int i = 0; i < position; i++) {
            if(first) {
                first = false;
            } else {
                sb.append(",");
            }
            sb.append(data[i]);
        }
        System.out.println(sb.append("]"));
    }

    public static void main1(String[] args) {
        AList aList = new AList();
        aList.add(5);
        aList.add(10);
        aList.add(15);
        System.out.println(aList.get(0));
        System.out.println(aList.get(1));
        System.out.println(aList.get(2));
        System.out.println(aList.get(3));
    }

    public static void main2(String[] args) {
        AList aList = new AList();
        aList.add(5);
        aList.print();
        aList.add(10);
        aList.print();
        aList.add(15);
        aList.print();
        aList.remove(1);
        aList.print();
        aList.remove(1);
        aList.print();
        aList.remove(0);
        aList.print();

    }

    public static void main(String[] args) {
        AList aList = new AList(0);
        aList.add(5);
        aList.print();
        aList.add(15);
        aList.print();

    }
}
