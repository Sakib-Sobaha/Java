import java.util.Scanner;
import java.util.*;

import static java.lang.System.exit;

interface List<T>{


    public void clear();

    public void insert(T item);

    public void append(T item);

    public T remove();

    public void moveToStart();

    public void moveToEnd();

    public void prev();

    public void next();

    public int length();

    public int currPos();

    public void moveToPos(int pos);

    public T getValue();

    public int Search(T item);

    //public void printList();

}

class AList<T> implements List<T>{

    private static int defaultSize = 10;  //Change
    private int maxSize;
    private int memorySize;
    private  int listSize;
    private int curr;
    private T[] listArray;
    private final int p = -1;
    private Class<T> type;

    public AList(Class<T> cls){
        type = cls;
    }

    Class<T> getType(){
        return this.type;
    }

    /*AList(){

    }*/


    AList(int defaultSize){
        this.defaultSize = defaultSize;
        this.curr = 0;
        listArray = (T[]) new Object[defaultSize];
        this.listSize = 0;
    }



    AList(T arr[],int size,int listSize,Class<T>cls){
        maxSize = size;
        this.memorySize = size;
        this.curr = 0;
        this.listSize = listSize;
        listArray = (T[]) new Object[maxSize];
        //listArray = arr;
        for(int i=0; i<this.listSize; i++ ){
            listArray[i] = arr[i];
        }
        type = cls;

        //AddCode
    }

    public void clear(){
        listSize =  0;
        curr = -1;

    }

    public void insert(T it){
        if(listSize > maxSize - 1){
            maxSize +=memorySize;
            listArray = (T[]) new Object[maxSize];
        }

        for(int i=listSize; i>curr; i--){
            listArray[i] = listArray[i-1];
        }
        listArray[curr] = it;
        listSize++;

    }

    public void append(T it){
        if(listSize > maxSize){
            maxSize += memorySize;
            listArray = (T[]) new Object[maxSize];
        }
        listArray[listSize++] = it;


    }

    public T remove(){
        if((curr<0) || (curr>=listSize)){
            return null;
        }
        T it = listArray[curr];
        for(int i=curr; i<listSize-1; ++i){
            listArray[i] = listArray[i+1];
        }
        listSize--;
        return it;


    }

    public void moveToStart(){
        curr = 0;
    }
    public void moveToEnd(){
        curr = listSize - 1;
    }

    public void prev(){
        if(curr == 0){
            System.out.println("Invalid action");
        }
        else{
            curr--;

        }
    }
    public void next(){
        if(curr == listSize-1){
            System.out.println("Invalid action");
        }
        else{
            curr++;

        }
    }

    public int length(){
        return listSize;
    }
    public int currPos(){
        return curr;
    }

    public void moveToPos(int pos){
        if(pos<0 || pos>=listSize){
            System.out.println("Invalid action");
        }
        else{
            curr= pos;

        }
    }

    public T getValue(){
        if(curr<0){
            System.out.println("Invalid Action");
            return null;
        }
        else{
            return listArray[curr];
        }
    }

    public int Search(T it){
        boolean found = false;
        int pos = -1;
        for(int i=0; i<listSize; i++){
            if(it == listArray[i]){
                pos = i;
                break;
            }
        }
       return pos;
    }

    public void noReturn(){
        System.out.println(p);
    }

    /*public void printList(){
        if(listSize == 0){
            System.out.println("<>");
        }
        else{
            System.out.print("<");
            for(int i=0; i<listSize; i++){
                if(curr == i){
                    System.out.print("| ");
                }
                if(i == listSize-1){
                    System.out.print(listArray[i]);
                }
                else{
                    System.out.print(listArray[i]+" ");
                }
            }
            System.out.println(">");
        }
    }*/



}
public class ArrayList {
    static void showFunc(){
        System.out.println("1     =     clear()");
        System.out.println("2     =     insert(item)");
        System.out.println("3     =     append(item)");
        System.out.println("4     =     remove()");
        System.out.println("5     =     moveToStart()");
        System.out.println("6     =     moveToEnd()");
        System.out.println("7     =     prev()");
        System.out.println("8     =     next()");
        System.out.println("9     =     length()");
        System.out.println("10    =     currPos()");
        System.out.println("11     =     moveToPos(pos)");
        System.out.println("12    =     getValue()");
        System.out.println("13    =      Search(item)");
    }

    public static void main(String[] args) {
        showFunc();
        Scanner input = new Scanner(System.in);
        int K, X;                 //K  = initLength X = memory chunk size
        K = input.nextInt();
        X = input.nextInt();
       Integer arr[] = new Integer[K];
       for(int i=0; i<K; i++){
           arr[i] = input.nextInt();
       }
       int p = -1;
        List<Integer>aL = new AList<>(arr,X,K,Integer.class);
        int Q,P;        //1<=Q<=13
        //aL.printList();
        printListtest(aL);
        boolean T = true;



        while(T ){
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
                aL.clear();
                //aL.printList();
                printListtest(aL);
                System.out.println(p);
            }
            else if(Q == 2){
                aL.insert(P);
                //aL.printList();
                printListtest(aL);
                System.out.println(p);
            }
            else if(Q == 3){
                aL.append(P);
                //aL.printList();
                printListtest(aL);
                System.out.println(p);
            }
            else if(Q == 4){
                //aL.printList();
                printListtest(aL);
                System.out.println(aL.remove());
            }
            else if(Q == 5){
                aL.moveToStart();
                //aL.printList();
                printListtest(aL);
                System.out.println(p);
            }
            else if(Q == 6){
                aL.moveToEnd();
                //aL.printList();
                printListtest(aL);
                System.out.println(p);
            }
            else if(Q == 7){
                aL.prev();
                //aL.printList();
                printListtest(aL);
                System.out.println(p);
            }
            else if(Q == 8){
                aL.next();
                //aL.printList();
                printListtest(aL);
                System.out.println(p);
            }
            else if(Q == 9){
                //aL.printList();
                printListtest(aL);
                System.out.println(aL.length());
            }
            else if(Q == 10){
                //aL.printList();
                printListtest(aL);
                System.out.println(aL.currPos());
            }
            else if(Q == 11){
                aL.moveToPos(P);
                //aL.printList();
                printListtest(aL);
                System.out.println(p);
            }
            else if(Q == 12){
                //aL.printList();
                printListtest(aL);
                System.out.println(aL.getValue());
            }
            else if(Q == 13){
                //aL.printList();
                printListtest(aL);
                System.out.println(aL.Search(P));
            }

        }
    }
    public static <T> void printListtest(List<T> arrayList ) {
        int track = arrayList.currPos();
        if (arrayList.length() == 0) {
            System.out.println("<>");
        } else {
            System.out.print("<");
            for (int i = 0; i < arrayList.length(); i++) {
                if (track == i) {
                    System.out.print("| ");
                    arrayList.moveToPos(i);
                }
                if (i == arrayList.length() - 1) {
                    arrayList.moveToPos(i);
                    System.out.print(arrayList.getValue());
                } else {
                    arrayList.moveToPos(i);
                    System.out.print(arrayList.getValue() + " ");
                }
            }
            System.out.println(">");
            arrayList.moveToPos(track);
        }
    }

}
