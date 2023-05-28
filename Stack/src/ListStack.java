import java.util.Scanner;

class Link<T>{
    private T element;
    private Link<T> next;

    Link(T it, Link<T> nextval){
        element = it;
        next = nextval;
    }
    Link(Link<T> nextval){
        next = nextval;
    }
    Link<T> next(){
        return next;
    }
    Link<T> setNext(Link<T> nextval){
        return next = nextval;
    }
    T element(){
        return element;
    }
    T setElement(T it){
        return element = it;
    }
}
class LStack<T> implements Stack<T>{
    private Link<T> top;
    private Link<T> head;
    private int size;

    public LStack(){
        top = null;
        size = 0;
    }
    public void clear(){
        top = null;
        size = 0;
    }

    public void push(T it){
        top = new Link<T>(it, top);
        size++;
    }
    public T pop(){
        if(top == null){
            System.out.println("No elements in stack.");
            return null;
        }
        else{
            T it = top.element();
            /*Link<T> temp = new Link<T>(null);
            while(temp.next() != top){
                temp = temp.next();
            }
            top = temp;*/
            top = top.next();
            size--;
            return it;
        }
    }

    public int length(){
        return size;
    }

    public T topValue(){
        if(top == null){
            System.out.println("Empty Stack");
            return null;
        }
        else{
            return top.element();
        }
    }

    public void setDirection(int direction ){

    }



}

public class ListStack {
    public static<T> void showStack(Stack<T> stack){
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
            for (int i = len - 1; i >= 0; i--) {
                stack.push(arr[i]);
            }
            System.out.print("\n");
        }

    }

    public static void main(String[] args) {
        Stack<Integer> stck = new LStack<>();
        int k,P,Q;
        int p = -1;
        Scanner input = new Scanner (System.in);
        k = input.nextInt();
        for(int i=0; i<k; i++){
            stck.push(input.nextInt());
        }
        System.out.println(stck.length());
        showStack(stck);

        while(true){
            P = input.nextInt();
            Q = input.nextInt();
            if(P == 0){
                System.exit(0);
            }
            else if(P == 1){
                stck.clear();
                showStack(stck);
                System.out.println(p);
            }
            else if(P == 2){
                stck.push(Q);
                showStack(stck);
                System.out.println(p);
            }
            else if(P == 3){
                int temp = stck.pop();
                showStack(stck);
                System.out.println(temp);
            }
            else if(P == 4){
                int temp = stck.length();
                showStack(stck);
                System.out.println(temp);
            }
            else if(P == 5){
                int temp = stck.topValue();
                showStack(stck);
                System.out.println(temp);
            }

        }


    }

}
