//Complete the following Java code that finds the total number of prime numbers from a 100000 random integers array using an array of 10 threads in parallel. You cannot write any more classes.

import java.util.Random;

class ParallelPrimeCounter implements Runnable {
    private int[] numbers;
    private int startIndex;
    private static final int NUMBER_COUNT = 10000;
    private int primeCount;
    // you are not allowed to add any more fields

    // you are not allowed to change this constructor, and you are not allowed to add any more constructor
    ParallelPrimeCounter(int[] numbers, int startIndex) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.primeCount = 0;
    }
    // your code here
    public void run() {
        System.out.println("I am here.....");
        for(int i=startIndex; i< (startIndex+(NUMBER_COUNT/10)); ++i) {
            boolean k = isPrime(this.numbers[i]);
            if (k) {
                this.primeCount++;
                //System.out.println("Increasing.......");
            }
        }
    }
    public boolean isPrime(int x){
        if(x == 1 || x < 1){
            return false;
        }
        else if(x == 2){
            return true;
        }
        else if(x %2 == 0){
            return false;
        }
        for(int i = 3; i<=x; i+=2){
            if(x%i == 0){
                return false;
                //break;
            }
        }
        return true;
    }
    public void setNumbers(int[] numbers){
        this.numbers = numbers;
    }
    public void setStartIndex(int startIndex){
        this.startIndex = startIndex;
    }
    public int getStartIndex(){
        return this.startIndex;
    }
    public void setPrimeCount(int primeCount){
        this.primeCount = primeCount;
    }
    public int getPrimeCount(){
        return this.primeCount;
    }

}

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        final int NUMBER_COUNT = 100000;
        final int THREAD_COUNT = 10;
        int[] numbers = new int[NUMBER_COUNT];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt();
        }
        int totalprime = 0;
        ParallelPrimeCounter[] parallelPrimeCounters = new ParallelPrimeCounter[THREAD_COUNT];
        Thread[] threads = new Thread[THREAD_COUNT];
        // your code here
        int startIndex = 0;
        for(int i=0; i<THREAD_COUNT;++i){
          //  Runnable r = parallelPrimeCounters[i];
            parallelPrimeCounters[i] = new ParallelPrimeCounter(numbers,startIndex);
            Runnable r = parallelPrimeCounters[i];
            threads[i] = new Thread(r);
            threads[i].start();
            System.out.println(parallelPrimeCounters[i].getPrimeCount());
            totalprime+=parallelPrimeCounters[i].getPrimeCount();
            startIndex+=(NUMBER_COUNT/THREAD_COUNT);
            //parallelPrimeCounters[i].setStartIndex(startIndex);
        }
        System.out.println("Total numbers of prime number is : "+totalprime);
    }
}