class NewThread implements Runnable{
    Thread t;
    NewThread(){
        t = new Thread(this);
        t.start();
    }
    public void run(){
        try{
            for(int i=5; i>0; i--){
                System.out.println("Child Thread : " + i);
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}
public class ImplementsThread {
    public static void main(String[] args) throws Exception {
        new NewThread();
        for(int i=10; i>0; --i){
            System.out.println("Main Thread : " + i);
            Thread.sleep(500);
        }
    }
}
