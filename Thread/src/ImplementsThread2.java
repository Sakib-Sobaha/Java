class NewThread3 implements Runnable{
    public void run(){
        try{
            for(int i=5;i>0;--i){
                System.out.println("Child Thread : "+i);
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting Child Thread");
    }
}
public class ImplementsThread2 {
    public static void main(String[] args)  {
        Runnable r = new NewThread3();
        Thread t = new Thread(r);
        t.start();

    }
}
