import java.util.Scanner;
class ArrStack<T>  implements Stack<T>{
    private static final int defaultSize = 50;
    private int maxSize;
    private int top;
    private T[] listArray;
    private T[] reverseList;
    private int setDirection = 1;
    private boolean redirected = false;
    private int trackTop;
    private int temp;
    private int count = 0;

    ArrStack(int size){
        maxSize = size;
        top = 0;
        trackTop = 0;
        listArray = (T[]) new Object[maxSize];
        reverseList = (T[]) new Object[maxSize];
    }

    ArrStack(T[] arr, int size, int maxSize){
        top = size ;
        this.maxSize = maxSize;
        trackTop = size;
        temp = size;
        listArray = (T[])new Object[maxSize];
        reverseList = (T[]) new Object[maxSize];
        for(int i=0; i<size; i++){
            listArray[i] = arr[i];
        }
    }

    public void rotateStack(T[] listStack, int size){
        int k = size - 1;
        int init = 0;
        while(init < k){
            T tree = listStack[init];
            listStack[init] = listStack[k];
            listStack[k] = tree;
            init++;
            k--;
        }
    }

    private void downward(T[] arr, int size){
        top = size;
        for(int i = top - 1; i>=0; i--){
            listArray[i] = arr[i];
        }
    }

    private void allocation(){
        if(top == maxSize){
            maxSize+=defaultSize;
        }
    }


    public void clear(){
        top = 0;
    }

    public void push(T it){
        if (top == maxSize ) {
            maxSize += defaultSize;
            listArray = (T[])new Object[maxSize];
        }
        listArray[top++] = it;
        if(trackTop == top) {
            rotateStack(listArray, top);
        }
        if(redirected){
            for(int i=0; i<top; i++){
                reverseList[i] = listArray[i];
            }
            rotateStack(reverseList,top);
        }
    }

    public T pop(){
        trackTop = top;
        if(top >= 0){
            if(redirected){
                return reverseList[--top];
            }
            else {
                return listArray[--top];
            }
        }
        else{
            return null;
        }

    }

    public int length(){
        return top;
    }

    public T topValue(){
        if(top < 0 ){
            return null;
        }
        else {
            return listArray[top - 1];
        }
    }

    public void setDirection(int direction){
        if(top == 0) {
            if (direction == 1) {
                this.setDirection = 1;
                redirected = false;

            } else if (direction == -1) {
                this.setDirection = -1;
                redirected = true;
            }
        }
        else{
            System.out.println("Invalid Action! Stack is not empty.");
        }
    }

    public boolean getDirection(){
        return this.redirected;
    }

}

public class StackImplement {
    public static<T> void showStack1(ArrStack<T> stack){
        int len = stack.length();
        if(len == 0){
            System.out.println("<>");
        }
        else {
            System.out.print("<");
            T arr[] = (T[]) new Object[len];
            for (int i = 0; i < len; ++i) {
                arr[i] = stack.pop();
            }
            for (int i = len - 1; i >= 0; i--) {
                if(i == 0){
                    System.out.print(arr[i]+">");
                }
                else {
                    System.out.print(arr[i] + ",  ");
                }
            }
            if(!stack.getDirection()){
                for (int i = len - 1; i >= 0; i--) {
                    stack.push(arr[i]);
                }
            }
            else if(stack.getDirection()){
                for (int i = 0; i <len ; i++) {
                    stack.push(arr[i]);
                }
            }

            System.out.print("\n");
        }

    }

    public static void main(String[] args) {
        int p = -1;
        int k, n, P, Q;
        Scanner input = new Scanner(System.in);
        k = input.nextInt();
        n = input.nextInt();
        Integer arr[] = new Integer[k];
        for(int i=0; i<k; ++i){
            arr[i] = input.nextInt();
        }
        ArrStack<Integer> stck = new ArrStack<Integer>(arr,k,n);
        //Stack<Integer> stck = new ArrStack<>(10);
        /*Stack<Integer> stck = new LStack<>();
        for(int i=0; i<k; i++){
            stck.push(input.nextInt());
        }*/
        System.out.println(stck.length());
        ListStack.showStack(stck);
        while(true){
            P = input.nextInt();
            Q = input.nextInt();
            if(P == 0){
                System.exit(0);
            }
            else if(P == 1){
                stck.clear();
                showStack1(stck);
                System.out.println(p);
            }
            else if(P == 2){
                stck.push(Q);
                showStack1(stck);
                System.out.println(p);
            }
            else if(P == 3){
                int temp = stck.pop();
                showStack1(stck);
                System.out.println(temp);
            }
            else if(P == 4){
                int temp = stck.length();
                showStack1(stck);
                System.out.println(temp);
            }
            else if(P == 5){
                int temp = stck.topValue();
                showStack1(stck);
                System.out.println(temp);
            }
            else if(P == 6){
                stck.setDirection(Q);
                showStack1(stck);
                System.out.println(p);
            }
        }
    }
}
