public class Mainthread {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("Current thread : " + t);
        //change he name
        t.setName("Sakib thread");
        System.out.println("After name change : "+t);
        try{
            for(int i=5; i>0;--i){
                System.out.println(i);
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            System.out.println("Main thread interrupted");
        }
    }
}
