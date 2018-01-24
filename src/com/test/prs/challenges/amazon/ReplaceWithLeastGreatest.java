package com.test.prs.challenges.amazon;

import java.util.HashMap;
import java.util.Map;

public class ReplaceWithLeastGreatest {
    LinkedList mHead;
    public static void main(String[] args) {
        ReplaceWithLeastGreatest r = new ReplaceWithLeastGreatest();
        r.mHead = new LinkedList(10);
        r.mHead.next = new LinkedList(12);
        r.mHead.next.next = new LinkedList(5);
        r.mHead.next.next.next = new LinkedList(40);
        r.mHead.next.next.next.next = new LinkedList(21);
        r.mHead.next.next.next.next.next = new LinkedList(70);
        r.mHead.next.next.next.next.next.next = new LinkedList(1);
        r.mHead.next.next.next.next.next.next.next = new LinkedList(49);
        r.mHead.next.next.next.next.next.next.next.next = new LinkedList(37);
//        r.printLinkedList(r.mHead);
        LinkedList newHead =  r.replace();
        r.printLinkedList(newHead);
        r.printLinkedList(r.mHead);
    }
    LinkedList replace(){
        LinkedList head = createClone(mHead);
        LinkedList reverseHead = reverseLinkedList(head);
        Map<Integer,Integer> m = new HashMap<>();
        while(reverseHead!=null){// create bst from right to left
           root = addNode(root,reverseHead);
           Integer greater = getGreater(root,reverseHead.num);
           m.put(reverseHead.num,greater);
           reverseHead = reverseHead.next;
        }
        LinkedList temp = createClone(mHead);
        LinkedList tempHead = temp;
        while(tempHead!=null){
            Integer newNode = m.get(tempHead.num);
            if(newNode!=null){
                tempHead.num = newNode;
            }
            tempHead = tempHead.next;
        }

        return temp;
    }

    private LinkedList createClone(LinkedList mHead) {
        LinkedList original = mHead;
        Map<LinkedList,LinkedList> map = new HashMap<>();
        while(original!=null){
            LinkedList copy = new LinkedList(original.num);
            map.put(original,copy);
            original=original.next;
        }
        original = mHead;

        while(original!=null){
            LinkedList copy = map.get(original);
            copy.next = map.get(original.next);
            original = original.next;
        }
        return map.get(mHead);
    }

    private void printLinkedList(LinkedList reverseHead) {
        System.out.println();
        while(reverseHead!=null)
        {
            System.out.print(reverseHead.num+" > ");
            reverseHead = reverseHead.next;
        }
    }

    private LinkedList reverseLinkedList(LinkedList head) {
        LinkedList prev = null;
        LinkedList curr = head;
        LinkedList next = null;
        while (curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private Integer getGreater(Node root, int x) { // 10 12 5 40 21 70 1 49 37
        if(root==null) return null;
        if(root.data.num<=x) return getGreater(root.right,x);
        else {
            Integer num = getGreater(root.left, x);
            if(num==null) //if left tree is returning take it from  inorder successor
                num = root.data.num; //
            return num;
        }
    }

    Node root;

    private Node addNode(Node root, LinkedList x) {
        Node n = new Node(x);
        if(root==null)
            root = n;
        else{
            if(x.num<root.data.num)
                root.left = addNode(root.left,x);
            else
                root.right = addNode(root.right,x);
        }
        return root;
    }

}

class Node{
    LinkedList data;
    Node left,right;

    public Node(LinkedList data) {
        this.data = data;
    }
}
