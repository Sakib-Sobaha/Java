public class BinarySearchTree<Key extends Comparable<? super Key>, T> {
    private BSTNode<Key,T> root;
    private int nodecount;

    /**
     * Constructor
     */
    BinarySearchTree(){
        root = null;
        nodecount = 0;
    }
    public void insertItem(Key k, T t){
        root = insertHelp(root,k,t);
        nodecount++;
    }
    private BSTNode<Key,T> insertHelp(BSTNode<Key,T> rt, Key k, T t){
        if(rt == null) return new BSTNode<Key,T> (k,t);
        if(rt.key().compareTo(k) > 0){       //lexicographically greater item < root so left child
            rt.setLeft(insertHelp(rt.left(),k,t));
        }
        else{
            rt.setRight(insertHelp(rt.right(),k,t));
        }
        return rt;
    }

    public void deleteItem(Key k){
        T temp = findHelp(root,k);
        if(temp == null){
            System.out.print("Invalid operation");
        }
        else if(temp != null){
            root = deleteHelp(root,k);
            nodecount--;
            printBST();
        }
    }

    private T findHelp(BSTNode<Key,T> rt, Key k){
        if(rt == null){
            return null;
        }
        if(rt.key().compareTo(k) > 0){      //item < root.element
            return findHelp(rt.left(),k);
        }
        else if(rt.key().compareTo(k) == 0){  //item == root.element
            return rt.element();
        }
        else return findHelp(rt.right(),k);
    }

    private BSTNode<Key,T> deleteHelp(BSTNode<Key,T> rt, Key k){
        if(rt == null){
            return null;
        }
        if(rt.key().compareTo(k) > 0){
            rt.setLeft(deleteHelp(rt.left(),k));
        }
        else if(rt.key().compareTo(k) < 0){
            rt.setRight(deleteHelp(rt.right(),k));
        }
        else{
            if(rt.left() == null){
                return rt.right();
            }
            else if(rt.right() == null){
                return rt.left();
            }
            else{
                /*BSTNode<Key,T> temp = getmin(rt.right());
                rt.setElement(temp.element());
                rt.setKey(temp.key());
                rt.setRight(deletemin(rt.right()));*/

                BSTNode<Key,T> temp = getMax(rt.left());
                rt.setElement(temp.element());
                rt.setKey(temp.key());
                rt.setLeft(deleteMax(rt.left()));
            }
        }
        return rt;
    }

    private BSTNode<Key,T> deletemin(BSTNode<Key,T> rt){
        if(rt.left() == null){
            return rt.right();
        }
        rt.setLeft(deletemin(rt.left()));
        return rt;
    }

    private BSTNode<Key,T> getmin(BSTNode<Key,T> rt){
        if(rt.left() == null){
            return rt;
        }
        return getmin(rt.left());
    }

    private BSTNode<Key,T> deleteMax(BSTNode<Key,T> rt){
        if(rt.right() == null){
            return rt.left();
        }
        rt.setRight(deleteMax(rt.right()));
        return rt;
    }

    private BSTNode<Key,T> getMax(BSTNode<Key,T> rt){
        if(rt.right() == null){
            return rt;
        }
        return getMax(rt.right());
    }

    public void findItem(Key k){
        T temp = findHelp(root,k);
        if(temp == null){
            System.out.println("False");
        }
        else{
            System.out.println("True");
        }
    }

    public void printBST(){
        printHelp(root);
    }

    private void printHelp(BSTNode<Key,T> rt){
        //System.out.print("(");
        if(rt == null){
            return;
        }
        System.out.print("(");
        System.out.print(rt.element());
        if(rt.left() == null && rt.right() != null){
            System.out.print("()");
        }
        printHelp(rt.left());

        printHelp(rt.right());
        if(rt.left() == null && rt.right() != null){
            System.out.print("()");
        }
        System.out.print(")");
    }

    public void printPreOrder(){
        printPreOrder(root);
    }

    private void printPreOrder(BSTNode<Key,T> rt){
        if(rt == null){
            return;
        }
        System.out.print(rt.element()+" ");
        printPreOrder(rt.left());
        printPreOrder(rt.right());
    }

    public void printInOrder(){
        printInOrder(root);
    }

    private void printInOrder(BSTNode<Key,T> rt){
        if(rt == null){
            return;
        }
        printInOrder(rt.left());
        System.out.print(rt.element()+" ");
        printInOrder(rt.right());
    }

    public void printPostOrder(){
        printPostOrder(root);
    }

    private void printPostOrder(BSTNode<Key,T> rt){
        if(rt == null){
            return;
        }
        printPostOrder(rt.left());
        printPostOrder(rt.right());
        System.out.print(rt.element()+" ");
    }


}

