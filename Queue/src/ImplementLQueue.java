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
class LQueue<T> implements Queue<T>{
    private Link<T> front;
    private Link<T> rear;
    private int size;

    public LQueue(int size){
        init();
    }
    private void init(){
        front = rear = new Link<T>(null);
        size = 0;
    }

    public void clear(){
        init();
    }

    public void enqueue(T it){
        rear.setNext(new Link<T>(it,null));
        rear = rear.next();
        size++;
    }
    public T dequeue(){
        if(size == 0){
            System.out.println("Queue is empty");
            return null;
        }
        else{
            T it = front.next().element();
            front.setNext(front.next().next());
            if(front.next() == null){
                rear = front;
            }
            size--;
            return it;
        }
    }

    public int length(){
        return size;
    }
    public T frontValue(){
        if(size == 0){
            System.out.println("Queue is empty");
            return null;
        }
        else{
            return front.next().element();
        }
    }

    public T rearValue(){
        if(size == 0){
            System.out.println("Queue is empty");
            return null;
        }
        else{
            return rear.element();
        }
    }

    public T leaveQueue(){
        if(size == 0){
            System.out.println("Queue is empty");
            return null;
        }
        else{
            Link<T> temp = front;
            Link<T> retNode = rear;
            while(temp.next() != rear){
                temp = temp.next();
            }
            rear = temp;
            rear.setNext(null);
            size--;
            return retNode.element();
        }
    }





}
public class ImplementLQueue {
}
