package com.xiaoze17;

public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private int size;
    private Node root;

    public BST() {
        size = 0;
        root = null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        Node addNode = new Node(e);
        if (size == 0) {
            root = addNode;
            size++;
        }else{
            Node temp = root;

            do {
                if (e.compareTo(temp.e) < 0) {
                    if (temp.left == null) {
                        temp.left = addNode;
                        size++;
                        return;
                    }else{
                        temp = temp.left;
                    }
                }

                if (e.compareTo(temp.e) > 0) {
                    if (temp.right == null) {
                        temp.right = addNode;
                        size++;
                        return;
                    }else{
                        temp = temp.right;
                    }
                }

            } while (true);

        }
    }

    public void recurAdd(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            recurAdd(root, e);
        }
    }

    private void recurAdd(Node node, E e) {
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }
        if (e.compareTo(node.e) < 0) {
            recurAdd(node.left, e);
        }
        else{
            recurAdd(node.right, e);
        }
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(41);
        bst.add(22);
        bst.add(58);
        bst.add(15);
        bst.add(13);
        bst.add(33);
        bst.add(37);
        bst.recurAdd(50);
        bst.recurAdd(60);
        System.out.println(bst.size);
    }
}
