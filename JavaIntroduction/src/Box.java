public class Box {
    int L,W,H;


    Box(int L,int W,int H){
        this.L = L;
        this.W = W;
        this.H = H;

    }

    public static void main(String[] args) {
        Box b1;
        Box b2;
        b1 = new Box(24,35,46);
        b2 = b1;
        b1 = new Box(3,4,5);
        b1 = b2;
        System.out.println(b1);
    }
}
