package com.xiaoze17;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

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
    private int temp = 0;

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
            Node parentNode = root;
            //先找到待插入的节点，这个parent节点就很关键，保存了父亲节点的信息，从而决定插在哪里
            while (temp != null) {
                parentNode = temp;
                if (e.compareTo(temp.e) < 0) {
                    temp = temp.left;
                }else {
                    temp = temp.right;
                }
            }
            if (e.compareTo(parentNode.e) < 0) {
                parentNode.left=new Node(e);
                size++;
            }else{
                parentNode.right = new Node(e);
                size++;
            }
//            do {
//                if (e.compareTo(temp.e) < 0) {
//                    if (temp.left == null) {
//                        temp.left = addNode;
//                        size++;
//                        return;
//                    }else{
//                        temp = temp.left;
//                    }
//                }
//
//                if (e.compareTo(temp.e) > 0) {
//                    if (temp.right == null) {
//                        temp.right = addNode;
//                        size++;
//                        return;
//                    }else{
//                        temp = temp.right;
//                    }
//                }
//
//            } while (true);

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

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if(node == null) {
            return false;
        }
        if(e.equals(node.e)){
            return true;
        }
        else if(e.compareTo(node.e)>0){
            return contains(node.right,e);
        }
        else{
            return contains(node.left,e);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.e+" ");
        preOrder(node.left);
        preOrder(node.right);
    }
    private void zeOrder(Node node) {
        if (node == null) {
                                                                                                return;
        }
        zeOrder(node.right);
        System.out.print(node.e+" ");
        zeOrder(node.left);

    }

    private void zeOrder2(Node node) {
        if (node == null) {
            return;
        }
        zeOrder2(node.right);
        temp += (Integer)node.e;
        System.out.print(temp+" ");
        zeOrder2(node.left);

    }

    public void preOrder2() {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){

            Node cur = stack.pop();
            System.out.print(cur.e+" ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }

        }
    }

    public void lyerOrder() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.e + " ");
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public Node minNode(){
        Node temp =root;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }

        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public Node maxNode(){
        Node temp =root;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    //递归删除二分搜索树最小值
    public E delMin() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return delMin(root) .e;
    }
    //思路太关键了，一定要从 返回删除最大值的根节点 出发。
    private Node delMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        //下面这句话又是我最不擅长的
        node.left = delMin(node.left);
        //node.right = delMax(node.right);
        //node.right = delMax(node.right);
        return node;
    }
    //递归删除二分搜索树最大值
    public E delMax() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return delMax(root).e;
    }
    //思路太关键了，一定要从 返回删除最大值的根节点 出发。
    private Node delMax(Node node) {

        if (node.right == null) {
             Node leftNode = node.left;
             node.left = null;
             size--;
             return leftNode;
        }
        //下面这句话又是我最不擅长的
        node.right = delMax(node.right);
        //node.right = delMax(node.right);
        //node.right = delMax(node.right);
        return node;
    }
    //非递归删除最大值
    public E delMax2() {
        Node temp = root;
        Node parent = root;
        Node maxNode = maxNode();

        //小技巧，父节点搞出来，保存起来
        while (temp.right != null) {
            parent = temp;
            temp = temp.right;
        }
        parent.right = temp.left;
        //System.out.println(temp.e);
        size--;
        return maxNode.e;
    }

    public void delElem(E e){
        if(!contains(e)){
            System.out.println("not have elem");
        }else{
            root = delElem(root,e);
        }
    }
    //递归返回的是删除指定元素的根节点
    private Node delElem(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = delElem(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = delElem(node.right,e);
            return node;
        }else{
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                return leftNode;
            }
            Node successor = minimum(node.right);
            successor.right = delMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;

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
        //System.out.println(bst.contains(22));
        bst.zeOrder(bst.root);
        System.out.println();
        bst.delElem(14);
        bst.zeOrder(bst.root);
        //bst.zeOrder2(bst.root);
//        System.out.println(bst.minNode().e);
//        System.out.println(bst.minimum());
//        System.out.println(bst.maxNode().e);
        //bst.lyerOrder();

    }
}
