public class A {
    private int a;

    public A(){
        this.a = 0;
    }

    public void setA(int a){
        this.a = a;
    }
    public int getA(){
        return this.a;
    }

    public static void main(String[] args) {
        A ob;
        ob = new A();
        ob.setA(80);
        System.out.println(ob.getA());
    }
}
