import static java.lang.System.exit;
import java.util.Scanner;
import java.util.*;

class Link<T>{
    private T element;
    private Link<T> next;

    Link(T it, Link<T> nextVal){
        element = it;
        next  = nextVal;
    }
    Link(Link<T> nextVal){
        next = nextVal;
    }

    Link<T> next(){
        return next;
    }

    Link<T> setNext(Link<T> nextVal){
        return next = nextVal;
    }
    T getElement(){
        return element;
    }
    T setElement(T it){
        return element = it;
    }
}

class LList<T> implements List<T>{
    private Link<T> head;
    private Link<T> tail;
    protected Link<T> curr;
    private int cnt = -1;
    private final int p = -1;                  //

    LList(){
        curr = tail = head = null;       //
        cnt = 0;
    }

    public void clear(){
        head.setNext(null);
        curr = tail = head = null;
        cnt = 0;

    }

    private void insertAtfirst(T it){
        Link<T> node = new Link<T>(it,head);
        head = node;
        cnt++;

    }

    public void insert(T it){
        if(curr == head){
            insertAtfirst(it);

        }
        else{
            Link<T> node = new Link<T>(it, curr);
            Link<T> node1 = head;           //For iteration purpose
            while(node1.next() != curr){
                node1 = node1.next();
            }
            node1.setNext(node);    //New node point to curr
            curr = node;
            cnt++;
        }


    }



    public void append(T it){
        Link<T> node = new Link<T>(it,null);
        if(head == null){                 //If list has no elements
            head = node;
            tail = node;
            curr = node;
        }
        else{
            tail.setNext(node);      //List has at least one elements
            tail = node;
        }
        cnt++;

    }

    public T remove(){
        Link<T> retNode = null;
        if(cnt > 0){
            retNode = curr;
            if(cnt == 1){            //When only one element
                head = tail = curr = null;      //empty list
            }
            else if(curr == head){
                head = head.next();                // when curr points to head then move to one node right
                curr = head;
            }
            else if(curr == tail){
                Link<T> temp = head;
                while(temp.next() != curr){
                    temp = temp.next();
                }
                tail = temp;   //tail points the tail.prev()
                curr = tail;     //curr points the prev node
                tail.setNext(null);
            }
            else{
                Link<T> temp = head;
                while(temp.next() != curr){
                    temp = temp.next();
                }
                curr = curr.next();
                temp.setNext(curr);
            }
            cnt--;

        }
        return retNode.getElement();
    }

    public void moveToStart(){
        curr = head;

    }
    public void moveToEnd(){
        curr = tail;

    }
    public void prev(){
        if(curr == head){
            System.out.println(p);
            return;
        }
        Link<T> temp = head;
        while(temp.next()!= curr ){
            temp = temp.next();
        }
        curr = temp;

    }

    public void next(){
        if(curr != tail){
            curr = curr.next();

        }
    }

    public int length(){
        return cnt;
    }

    public int currPos(){
        Link<T> temp = head;
        int i;
        for(i=0; curr!= temp; i++){
            temp = temp.next();
        }
        return i;
    }

    public void moveToPos(int pos){
        if(pos<0 || pos>=cnt){
            System.out.println("Invalid Action");
        }
        else{
            curr = head;
            for(int i=0; i<pos; i++){
                curr = curr.next();
            }

        }
    }

    public T getValue(){
        return curr.getElement();
    }

    public int Search(T it){
        int pos = 0;
        Link<T> temp = head;
        while(temp != null){
            if(temp.getElement() == it){
                return pos;
            }
            pos++;
            temp = temp.next();
        }
        return -1;
    }

    /*public <T>void printList(List<T> array){
        if(cnt < 0){
            System.out.println("<>");
        }
        else {
            System.out.print("< ");
            int original = array.currPos();

            if (array.length() == 0) {
                System.out.println(">");
                return;
            }

            for (int i = 0; i < array.length(); i++) {
                array.moveToPos(i);
                if (i == original) {
                    System.out.print("| ");
                }

                //array.moveToPos(i);
                System.out.print(array.getValue() + " ");

            }
            array.moveToPos(original);
            System.out.println(">");
        }

    }*/

}

public class LinkedList {
    private static boolean p;

    public static void main(String[] args) {
        List<Integer>lL = new LList<>();
        int p = -1;
        Scanner input = new Scanner(System.in);
        int K,X;
        K = input.nextInt();
        X = input.nextInt();
        show(lL);
        for(int i=0; i<K; i++){
            Integer m = input.nextInt();
            lL.append(m);
        }

        show(lL);
        int P,Q;

        boolean T = true;
        while(T){
            Q = input.nextInt();
            P = input.nextInt();
            /*if(aL.getType().equals(Integer.class)){
                int P;
                P =  input.nextInt();
            }
            else if(aL.getType() .equals(String.class)){
                String P = input.next();
            }*/

            if(Q == 0){
                exit(0);
            }
            else if(Q == 1){
                lL.clear();
                show(lL);
                System.out.println(p);
            }
            else if(Q == 2){
                lL.insert(P);
                show(lL);
                System.out.println(p);
            }
            else if(Q == 3){
                lL.append(P);
                show(lL);
                System.out.println(p);
            }
            else if(Q == 4){
                show(lL);
                System.out.println(lL.remove());
            }
            else if(Q == 5){
                lL.moveToStart();
                show(lL);
                System.out.println(p);
            }
            else if(Q == 6){
                lL.moveToEnd();
                show(lL);
                System.out.println(p);
            }
            else if(Q == 7){
                lL.prev();
                show(lL);
                System.out.println(p);
            }
            else if(Q == 8){
                lL.next();
                show(lL);
                System.out.println(p);
            }
            else if(Q == 9){
                show(lL);
                System.out.println(lL.length());
            }
            else if(Q == 10){
                show(lL);
                System.out.println(lL.currPos());
            }
            else if(Q == 11){
                lL.moveToPos(P);
                show(lL);
                System.out.println(p);
            }
            else if(Q == 12){
                show(lL);
                System.out.println(lL.getValue());
            }
            else if(Q == 13){
                show(lL);
                System.out.println(lL.Search(P));
            }

        }
        
    }

    public static <T> void show(List<T>array)
    {
        System.out.print("< ");
        int original=array.currPos();

        if(array.length()==0)
        {
            System.out.println(">");return;
        }

        for(int i=0;i<array.length();i++)
        {
            if(i==original)
            {
                System.out.print("| ");
            }

            array.moveToPos(i);
            System.out.print(array.getValue()+" ");

        }
        array.moveToPos(original);
        System.out.println(">");
    }

}

