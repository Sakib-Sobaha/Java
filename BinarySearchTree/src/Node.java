public class Node {
    /**
     * Node of BST
     * @param value value stored in a node
     * @param left left child of this node
     * @param right right child of this node
     */
    private int value;       //
    private Node left;
    private Node right;

    public Node(){

    }
    public Node(int value, Node left, Node right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value = value;
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public Node getLeft(){
        return left;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public Node getRight(){
        return right;
    }



}
