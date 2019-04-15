package trees;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinaryTree {
    private class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

    }

    private Node root;

    private boolean contains(Node from, int val) {
        if (from == null) {
            return false;
        }
        if (val == from.value) {
            return true;
        } else if (val < from.value) {
            return contains(from.left, val);
        } else {
            return contains(from.right, val);
        }
    }

    public boolean contains(int val) {
        return contains(root, val);
    }

    private void add(Node from, int val) {
        if (val < from.value) {
            if (from.left == null) {
                from.left = new Node(val);
            } else {
                add(from.left, val);
            }
        } else if (val > from.value) {
            if (from.right == null) {
                from.right = new Node(val);
            } else {
                add(from.right, val);
            }
        }
    }

    public void add(int val) {
        if (root == null) {
            root = new Node(val);
        } else {
            add(root, val);
        }
    }

    private void relinkParent(Node parent,Node from , Node to){
        if(parent.left == from){
            parent.left = to;
        } else {
            parent.right = to;
        }
    }

    private void remove(Node parent, Node curr, int val) {
        if (val == curr.value) {

            if (curr.left == null && curr.right == null){
                relinkParent(parent,curr,null);
            } else if (curr.left != null && curr.right == null){
                relinkParent(parent,curr,curr.left);
            } else if (curr.left == null && curr.right != null){
                relinkParent(parent,curr,curr.right);
            } else {

            }

        } else if (val < curr.value) {
            remove(curr,curr.left, val);
        } else if (val > curr.value) {
            remove(curr,curr.right, val);
        }
    }

    public void remove(int val) {
        remove(null,root, val);
    }


    public static void main(String[] args) {
        BinaryTree t = new BinaryTree();
        Set<Integer> randoms1 = Stream
                .generate(() -> (int) (Math.random() * 100))
                .limit(20)
                .collect(Collectors.toSet());

        Set<Integer> randoms2 = Stream
                .generate(() -> (int) (Math.random() * 100 + 100))
                .limit(20)
                .collect(Collectors.toSet());

        randoms1.forEach(t::add);
        List<Boolean> collect = randoms1
                .stream()
                .map(t::contains)
                .filter(item -> item)
                .collect(Collectors.toList());

        System.out.println(collect.size() == randoms1.size() ? "Ales good" : "Smth went wrong");

        List<Boolean> collect2 = randoms2
                .stream()
                .map(t::contains)
                .filter(item -> item)
                .collect(Collectors.toList());

        System.out.println(collect2.size() == 0 ? "Ales good" : "Smth went wrong");

        t.add(500);
        System.out.println(t.contains(500));
        t.remove(500);
        System.out.println(t.contains(500));
    }
}