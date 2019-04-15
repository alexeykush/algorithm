package datastructures;

import datastructures.interfaces.IMap;

public class XHashMap implements IMap<Integer, String> {

    class Entry {
        Integer key;
        String value;
        Entry next;

        public Entry(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Entry{key=%d, value='%s'}", key, value);
        }
    }

    private Entry[] entries;
    private final static int LEN = 16;

    public XHashMap() {
        this.entries = new Entry[LEN];
    }

    private int hash(int object) {
        return object % LEN;
    }

    @Override
    public void put(Integer key, String val) {
        int index = hash(key);
        Entry ent = new Entry(key, val);
        Entry current =  entries[index];
        if(current == null){
            entries[index] = ent;
        } else {
            while (current.next != null){
                current = current.next;
            }
            current.next = ent;
        }
    }

    @Override
    public String get(Integer key) {
        int index = hash(key);
        Entry current =  entries[index];
        if(current == null){
            throw new IllegalArgumentException(String.format("element with key %d not found",key));
        }
        while(current != null){
            if(current.key == key){
                return current.value;
            }
            current = current.next;
        }
        throw new IllegalArgumentException(String.format("element with key %d not found",key));
    }

    public static void main1(String[] args) {
        XHashMap map = new XHashMap();
        map.put(1, "Dima");
        map.put(2, "Lena");
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
    }

    public static void main(String[] args) {
        XHashMap map = new XHashMap();
        map.put(1, "Dima");
        map.put(17, "Lena");
        map.put(33, "Ira");
        System.out.println(map.get(1));
        System.out.println(map.get(17));
        System.out.println(map.get(33));
        System.out.println(map.get(66));
    }
}
