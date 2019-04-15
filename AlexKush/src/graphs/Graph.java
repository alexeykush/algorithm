package graphs;

import java.util.*;

public class Graph {
    private final HashMap<Integer, Set<Integer>> data = new HashMap<>();
    private final Set<Integer> visited = new TreeSet<>();

    public void addVertex(int v) {
        if (!data.containsKey(v)) {
            data.put(v, new TreeSet<>());
        }
    }

    public void addLink(int v1, int v2) {
        addVertex(v1);
        addVertex(v2);
        data.get(v1).add(v2);
    }

    public void removeLink(int v1, int v2) {
        if (data.containsKey(v1)) {
            data.get(v1).remove(v2);
        }
    }

    public Set<Integer> getOutgoing(int v1) {
        if (!data.containsKey(v1)) {
            throw new IllegalArgumentException(
                    String.format("There is no vertex with requested %d", v1));
        }
        return data.get(v1);
    }

    public Set<Integer> getIncoming(int v1) {
        if (!data.containsKey(v1)) {
            throw new IllegalArgumentException(
                    String.format("There is no vertex with requested number %d", v1)
            );
        }
        TreeSet<Integer> t = new TreeSet<>();
        for (Map.Entry<Integer, Set<Integer>> item : data.entrySet()) {
            if (item.getValue().contains(v1)) {
                t.add(item.getKey());
            }
        }
        return t;
    }

    public boolean dfc(int from, int to) {
        if(visited.contains(from)){
            return false;
        } else {
            visited.add(from);
        }
        if(from == to){
            return true;
        }
        Set<Integer> childs = getOutgoing(from);
        for(int el : childs){
            if(dfc(el,to)){
                return true;
            }
        }
        return false;
    }

    public boolean bfc(int from, int to) {
        if(visited.contains(from)){
            return false;
        } else {
            visited.add(from);
        }
        Set<Integer> children = getOutgoing(from);
        for(int el : children){
            if(el == to){
                return true;
            }
        }
        for (int el : children) {
            return bfc(el,to);
        }
        return false;
    }

    public boolean isConnected(int from, int to) {
        if(!data.containsKey(from) || !data.containsKey(to)){
            return false;
        }
        visited.clear();
//        return dfc(from,to);
        return bfc(from,to);
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addLink(1, 2);
        g.addLink(2, 3);
        g.addLink(3, 4);
        g.addLink(4, 1);

        g.addLink(5, 6);

        g.addLink(1,7);
        g.addLink(7,9);
        g.addLink(9,10);

        g.addLink(6, 31);
        g.addLink(31, 112);
        g.addLink(112, 113);

//        System.out.println(g.isConnected(1, 45));
        System.out.println(g.isConnected(6, 113));
        System.out.println(g.isConnected(6, 114));
    }
}