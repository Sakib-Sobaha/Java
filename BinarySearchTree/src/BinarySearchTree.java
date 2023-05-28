public class BinarySearchTree {
    /**
     * BST is a sorted tree, a rooted binary tree
     * Internal nodes store a key greater than all the keys int the node's left subtree
     * Less than those in its right subtree
     */

    private Node root;
    public BinarySearchTree(){

    }

    public void insertItem(int item){
        if(root == null){
            root = new Node(item,null,null);
        }
        else{
            insertItem(root, item);
        }
    }

    private void insertItem(Node node, int item){
        if(node.getValue() < item){
            if(node.getRight() == null){
                Node newNode= new Node(item,null,null);
                node.setRight(newNode);
            }
            else{
                insertItem(node.getRight(),item);
            }
        }
        else if(node.getValue() > item){
            if(node.getLeft() == null){
                Node newNode = new Node(item,null,null);
                node.setLeft(newNode);
            }
            else{
                insertItem(node.getLeft(),item);
            }
        }
        else{
            return;
        }
    }

    public void findItem(int item){
        if(findItem(root,item) == null){
            System.out.println("False");
        }
        else{
            System.out.println("True");
        }
    }

    private Node findItem(Node node,int item){
        if(node == null || node.getValue() == item){
            return node;
        }
        else if(node.getValue() < item){
            return findItem(node.getRight(),item);
        }
        else{
            return findItem(node.getLeft(),item);
        }
    }

    public void deleteItem(int item){
        root = deleteItem(root,item);
        if(root == null){
            System.out.println("Invalid operation");
        }
        else{
            printBST();
        }
    }

    private Node deleteItem(Node node, int item){
        if(node == null){
            return node;
        }
        if(item > node.getValue()){
            node.setRight(deleteItem(node.getRight(),item));
        }
        else if(node.getValue() > item){
            node.setLeft(deleteItem(node.getLeft(),item));
        }
        /*else {
            if(node.getLeft() == null){
                return node.getRight();
            }
            if(node.getRight() == null){
                return node.getLeft();
            }
            /*int maxLeft = getMaxLeft(node.getLeft());
            node.setValue(maxLeft);
            node.setLeft(deleteItem(node.getLeft(),maxLeft));
            int min = getMinItem(node.getLeft());
            node.setValue(min);
            node.setLeft(deleteItem(node.getLeft(),min));
        }*/
        return node;
    }

    public int getMinItem(){
        return getMinItem(root);
    }

    private int getMinItem(Node node){
        if(node == null){
            return -1;
        }
        while(node.getLeft() != null){
            node = node.getLeft();
        }
        return node.getValue();
    }

    private int getMaxLeft(Node node){
        if(node == null){
            return -1;
        }
        int leftMax = node.getLeft().getValue();
        while(node.getLeft() != null){
            if(node.getLeft().getValue() > leftMax ){
                leftMax = node.getLeft().getValue();
                node = node.getLeft();
            }
        }
        return leftMax;


    }

    public void printInOrder(){
        printInOrder(root);
    }

    private void printInOrder(Node node){
        if(node == null){
            return;
        }
        printInOrder(node.getLeft());
        System.out.print(node.getValue() + " ");
        printInOrder(node.getRight());
    }

    public void printPreOrder(){
        printPreOrder(root);
    }

    private void printPreOrder(Node node){
        if(node == null) {
            return;
        }
        System.out.print(node.getValue()+" ");
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
    }

    public void printPostOrder(){
        printPostOrder(root);
    }

    private void printPostOrder(Node node){
        if(node == null){
            return;
        }
        printPostOrder(node.getLeft());
        printPostOrder(node.getRight());
        System.out.print(node.getValue()+" ");
    }

    public void printBST(){
        printBST(root);
    }

    private void printBST(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.getValue());
        System.out.print("(");
        printPreOrder(node.getLeft());
        System.out.print(")");
        System.out.print("(");
        printPreOrder(node.getRight());
        System.out.print(")");

    }
}
