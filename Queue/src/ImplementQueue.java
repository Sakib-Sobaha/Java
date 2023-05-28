import java.util.Scanner;

class AQueue<T> implements Queue<T>{
    private static final int defaultSize = 50;
    private int maxSize;
    private int front;
    private int rear;
    private T[] listArray;
    private int size;
    private int queueLen = 0;

    AQueue(int size){
        maxSize = size + 1;
        rear = 0;
        front = 1;
        listArray = (T[]) new Object[maxSize];
        queueLen = 0;
    }

    AQueue(T[] arr, int size, int maxSize){
        this.maxSize = maxSize;
        this.size = size;
        listArray = (T[]) new Object[maxSize];
        rear = 0;
        front = 1;
        queueLen = 0;
        for(int i=0; i<size; i++){
            enqueue(arr[i]);
            //listArray[(rear++)%maxSize] = arr[i];
        }
        //Add code
    }

    public void clear(){
        rear = -1;
        front = 0;
        queueLen = 0;
    }

    public void enqueue(T it){
        /*if(rear + 1 == front){             //rear + 1 == front   enqueue -> boolean true (true) ? full : not
            System.out.println("Queue is full");
        }*/
        if(queueLen == maxSize){
            System.out.println("Queue is full");
        }
        rear = (rear+1) % maxSize;
        listArray[rear] = it;
        queueLen++;

    }

    public T dequeue(){
        if(queueLen == 0){
            System.out.println("Queue is empty");
            return null;
        }
        else{
            T it = listArray[front];
            front = (front + 1) % maxSize;
            queueLen--;
            return it;
        }
    }

    public int length(){
        //this.queueLen = ((rear+maxSize) - front + 1) % maxSize;
        return queueLen;
    }

    public T frontValue(){
        if(queueLen == 0){
            System.out.println("Queue is empty");
            return null;
        }
        else{
            return listArray[front];
        }
    }

    public T rearValue(){
        if(queueLen == 0){
            System.out.println("Queue is empty");
            return null;
        }
        else{
            return listArray[rear];
        }
    }

    public T leaveQueue(){
        if(this.queueLen == 0){
            System.out.println("Queue is empty");
            return null;
        }
        else{
            T it = listArray[rear];
            rear = (rear - 1) % maxSize;
            queueLen--;
            return it;
        }
    }
}

public class ImplementQueue {
    public static<T> void showQueue(Queue<T> queue){
        int len = queue.length();
        int reserve = len;
        //System.out.println(len);
        if(len == 0){
            System.out.println("<>");
        }
        else{
            System.out.print("<");
            Queue<T> tempQueue = new LQueue<T>(len);
            for(int i=0; i<len; i++){
                T k = queue.frontValue();
                tempQueue.enqueue(queue.dequeue());
                if(i == len - 1){
                    System.out.println(k+">");
                }
                else{
                    System.out.print(k+", ");
                }
            }
            for(int i =0; i<reserve; i++){
                queue.enqueue(tempQueue.dequeue());
            }
        }
    }
    public static void main(String[] args) {
        int n,k;
        int P,Q;
        int  p = -1;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        Integer arr[] = {10,20,30,40,50,60,70};
        //Queue<Integer> queue = new AQueue<Integer>(arr, n,50);
        Queue<Integer> queue = new LQueue<Integer>(n);
        for(int i=0; i<n; ++i){
            queue.enqueue(input.nextInt());
        }
        showQueue(queue);
        while(true){
            P = input.nextInt();
            Q = input.nextInt();
            if(P == 0){
                System.exit(0);
            }
            else if(P == 1){
                queue.clear();
                showQueue(queue);
                System.out.println(p);
            }
            else if(P == 2){
                queue.enqueue(Q);
                showQueue(queue);
                System.out.println(p);
            }
            else if(P == 3){
                int temp = queue.dequeue();
                showQueue(queue);
                System.out.println(temp);
            }
            else if(P == 4){
                int len = queue.length();
                showQueue(queue);
                System.out.println(len);
            }
            else if(P == 5){
                int frontVal = queue.frontValue();
                showQueue(queue);
                System.out.println(frontVal);
            }
            else if(P == 6){
                int rearVal = queue.rearValue();
                showQueue(queue);
                System.out.println(rearVal);
            }
            else if(P == 7){
                int leaveVal = queue.leaveQueue();
                showQueue(queue);
                System.out.println(leaveVal);
            }
        }
    }
}
