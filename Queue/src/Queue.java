public interface Queue<T> {
    public void clear();

    public void enqueue(T it);

    public T dequeue();

    public int length();

    public T frontValue();

    public T rearValue();

    public T leaveQueue();
}
