import  java.util.ArrayList;
import  java.util.List;
import java.util.concurrent.*;

public class CallableFutures2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Callable<Integer>>taskList = new ArrayList<>();
        for(int i=0;i<20;++i){
            taskList.add(new MyCallable());
        }
        List<Future<Integer>>futureList = executor.invokeAll(taskList);
        int sum = 0;
        for(Future future : futureList){
            sum+=(Integer) future.get();
        }
        System.out.println(sum);
        executor.shutdown();

    }
}
