package com.test.prs.heap;

public class TestHeap {

    public boolean isComplete(Node node,int index, int noOfNodes){
        if(node==null)
            return true;
        if(index>=noOfNodes)
            return false;
        return isComplete(node.left,2*index+1,noOfNodes) && isComplete(node.right,2*index+2,noOfNodes);
    }
//checking for maxHeap
    public boolean isHeapUtil(Node node){
        if(node.left==null && node.right==null)
            return true;

        if(node.right==null){
            return node.left.k<=node.k;
        }
        else{
            if(node.left.k<=node.k && node.right.k<=node.k){
                return isHeapUtil(node.left) && isHeapUtil(node.right);
            }
            else
                return false;
        }
    }

    boolean isHeap(Node node){
        if(node==null)
            return true;
        int noOfNodes = countNodes(node);

        return isComplete(node,0,noOfNodes) && isHeapUtil(node);
    }

    private int countNodes(Node node) {
        if(node==null)
            return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public static void main(String args[])
    {
        TestHeap bt = new TestHeap();

        for(int i=10;i>=1;i--)
            System.out.println(bt.add(i));
        /*root.left = new Node(9);
        root.right = new Node(8);
        root.left.left = new Node(7);
        root.left.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(4);
        root.left.left.left = new Node(3);
        root.left.left.right = new Node(2);
        root.left.right.left = new Node(1);*/

        if(bt.isHeap(root) == true)
            System.out.println("Given binary tree is a Heap");
        else
            System.out.println("Given binary tree is not a Heap");
    }
    static Node root;
    boolean add(int data){
        int oldNodeCount = countNodes(root);
        root = add(root,data);
        int newNodeCount = countNodes(root);
        return newNodeCount-oldNodeCount>0;
    }

    Node add(Node n,int data){
        if(n==null) {
            n = new Node(data);
        }
        else {
            n.left = add(n.left,data);
            n.right = add(n.right,data);
        }
        return n;
    }
}

class Node{
    int k;Node left,right;

    public Node(int k) {
        this.k = k;
    }
}
