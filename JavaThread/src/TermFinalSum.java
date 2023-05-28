class DetermineSum implements Runnable{
    private int[] numbers;
    private int startIndex;
    private int number;
    private static final int NUMBER_COUNT = 10;
    private int sum;

    DetermineSum(int[] numbers,int startIndex){
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.sum = 0;
    }

    DetermineSum(int number){
        this.number = number;
    }

    public void add(int k){
        sum+=k;
    }

    public void setStartIndex(int startIndex){
        this.startIndex = startIndex;
    }

    public int getStartIndex(){
        return this.startIndex;
    }

    public int getSum(){
        return this.sum;
    }

    @Override

    public void run(){
        for(int i=startIndex; i<=(startIndex+NUMBER_COUNT);++i){
            add(i);
        }
    }
}

public class TermFinalSum {

    public static void main(String[] args) {
        final int NUMBER_COUNT = 100;
        final int THREAD_COUNT = 10;
        int[] numbers = new int[NUMBER_COUNT];
        int startIndex = 1;
        int totalSum = 0;
        //static int k = 1;
        int k = 1;
        for(int i=0; i<numbers.length; ++i){
            numbers[i] = k++;
        }

        DetermineSum[] determineSum = new DetermineSum[THREAD_COUNT];
        Thread[] threads = new Thread[THREAD_COUNT];

        for(int i=0; i<THREAD_COUNT; ++i){
            determineSum[i] = new DetermineSum(numbers,startIndex);
            Runnable r = determineSum[i];
            threads[i] = new Thread(r);
            threads[i].start();
            try{
                threads[i].join();
            }catch(InterruptedException e){
                System.out.println(e);
            }
            totalSum+=determineSum[i].getSum();
            startIndex+=NUMBER_COUNT/THREAD_COUNT;
            determineSum[i].setStartIndex(startIndex);
        }

        System.out.println("Total number of square numbers : "+totalSum);

    }
}
