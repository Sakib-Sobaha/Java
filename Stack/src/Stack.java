public interface Stack<T>{
    public void clear();

    public void push(T it);

    public T pop();

    public int length();

    public T topValue();

    public void setDirection(int direction);

   //public int getTop();          //extra
}
