public class BSTNode<Key, T> implements BinNode<T> {
    private Key key;          //key for this node
    private T element;        //Element for this node
    private BSTNode<Key,T> left;
    private BSTNode<Key,T> right;

    /**
     * Constructors
     */
    public BSTNode(){
        left = right = null;
    }
    public BSTNode(Key k, T val){
        left = right = null;
        key = k;
        element = val;
    }
    public BSTNode(Key k, T val, BSTNode<Key,T> l, BSTNode<Key,T>r){
        left = l;
        right = r;
        key = k;
        element = val;
    }
    /**
     * Get and set the key value
     */
    public Key key(){
        return key;
    }
    public void setKey(Key k){
        key = k;
    }
    /**
     * Get and set the element value
     */
    public T element(){
        return element;
    }
    public void setElement(T v){
        element = v;
    }
    /**
     * Get and set the left child
     */
    public BSTNode<Key,T> left(){
        return left;
    }
    public void setLeft(BSTNode<Key,T> p){
        left = p;
    }
    /**
     * Get and set the right child
     */
    public BSTNode<Key,T> right(){
        return right;
    }
    public void setRight(BSTNode<Key,T> p){
        right = p;
    }
    /**
     * @return true if a leaf node, false otherwise
     */
    public boolean isLeaf(){
        return (left == null) && (right == null);
    }
}
