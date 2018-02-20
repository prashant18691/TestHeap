package com.test.prs.binarytree;

public class ConnectRightSiblings {
    Node root;

    public void connectIeration(Node node){
        if (node==null)
            return;
        node.nextRight=null;

        while (node!=null){// loop to change levels
            Node q = node;
            while (q!=null) { // loop to move from left to righ nodes
                if (q.left != null) {
                    if (q.right != null)
                        q.left.nextRight = q.right;
                    else q.left.nextRight = getNextRight(q);
                }
                if (q.right!=null)
                    q.right.nextRight = getNextRight(q);

                q = q.nextRight;
            }
            if (node.left!=null)
                node = node.left;
            else if (node.right!=null)
                node = node.right;
            else
                node = getNextRight(node);
        }
    }

    public void connectRecurr(Node node){
        if (node==null)
            return;
        if(node.nextRight!=null)
            connectRecurr(node.nextRight);

        if(node.left!=null){
            if (node.right!=null){
                node.left.nextRight=node.right;
                node.right.nextRight = getNextRight(node);
            }
            else
                node.left.nextRight = getNextRight(node);
            connectRecurr(node.left);// left calls to right also
        }
        else if (node.right!=null){
            node.right.nextRight = getNextRight(node);
            connectRecurr(node.right);
        }
        else
            connectRecurr(node.nextRight);
    }

    private Node getNextRight(Node node) {
        for (Node temp = node.nextRight;temp!=null;temp=temp.nextRight){
            if (temp.left!=null)
                return temp.left;
            if (temp.right!=null)
                return temp.right;
        }
        return null;
    }

    public static void main(String[] args) {
        ConnectRightSiblings tree = new ConnectRightSiblings();
        tree.root = new Node(10);
        tree.root.left = new Node(8);
        tree.root.right = new Node(2);
        tree.root.left.left = new Node(3);
        tree.root.right.right = new Node(90);

        // Populates nextRight pointer in all nodes
        tree.connectIeration(tree.root);

        // Let us check the values of nextRight pointers
        int a = tree.root.nextRight != null ?
                tree.root.nextRight.data : -1;
        int b = tree.root.left.nextRight != null ?
                tree.root.left.nextRight.data : -1;
        int c = tree.root.right.nextRight != null ?
                tree.root.right.nextRight.data : -1;
        int d = tree.root.left.left.nextRight != null ?
                tree.root.left.left.nextRight.data : -1;
        int e = tree.root.right.right.nextRight != null ?
                tree.root.right.right.nextRight.data : -1;

        // Now lets print the values
        System.out.println("Following are populated nextRight pointers in "
                + " the tree(-1 is printed if there is no nextRight)");
        System.out.println("nextRight of " + tree.root.data + " is " + a);
        System.out.println("nextRight of " + tree.root.left.data + " is " + b);
        System.out.println("nextRight of " + tree.root.right.data + " is " + c);
        System.out.println("nextRight of " + tree.root.left.left.data +
                " is " + d);
        System.out.println("nextRight of " + tree.root.right.right.data +
                " is " + e);
    }
}
