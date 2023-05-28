class Outer2{
    int outer_x = 100;

    void test(){
        Inner inner = new Inner();
        inner.display();
    }

    class Inner{
        int y = 10;
        void display(){
            System.out.println(outer_x);
        }
    }

    void showxy(){

    }
}
public class InnerClassDemo2 {
    public static void main(String[] args) {
        Outer2 outer = new Outer2();
        outer.test();
    }
}
