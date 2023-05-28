public interface BinNode<T> {
    /**
     * Get and set the element value
     * @return
     */
    public T element();
    public void setElement(T v);

    /**
     * @return the left child
     */
    public BinNode<T> left();
    /**
     * @return the right child
     */
    public BinNode<T> right();
    /**
     * @return True if a leaf node, false otherwise
     */
    public boolean isLeaf();

}
/*
int count(BinNode rt){
    if(rt == null) return 0;
    return 1 + count(rt.left()) + count(rt.right())
}

void preoder(BinNode rt){
    if(rt == null) return;
    visit(rt);
    preorder(rt.left());
    preorder(rt.right());
}
 */
