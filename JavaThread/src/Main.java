//Complete the following Java code that finds the total number of square numbers from a 100000 random integers array using an array of 20 threads in parallel. You cannot write any more classes.

import java.util.Random;

class ParallelSquareCounter implements Runnable {
    private int[] numbers;
    private int startIndex;
    private int number;
    private static final int NUMBER_COUNT = 10;
    private int squareCount;
    // you are not allowed to add any more fields

    // you are not allowed to change this constructor, and you are not allowed to add any more constructor
    ParallelSquareCounter(int[] numbers, int startIndex) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.squareCount = 0;
    }
    ParallelSquareCounter(int number){
        this.number = number;
    }
    // your code here
    public boolean isSquare(int x){
        int y = (int)(Math.sqrt(x)+0.5);
        return y*y == x;
    }
    public void setStartIndex(int startIndex){
        this.startIndex = startIndex;
    }
    public int getStartIndex(){
        return this.startIndex;
    }
    public int getSquareCount(){
        return this.squareCount;
    }
    @Override

    public void run(){
        for(int i=startIndex; i<(startIndex+NUMBER_COUNT)-1; ++i){
            boolean m = isSquare(numbers[i]);
            if(m){
                this.squareCount++;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        final int NUMBER_COUNT = 200;
        final int THREAD_COUNT = 20;
        int number2 = 99;
        int[] numbers = new int[NUMBER_COUNT];
        int startIndex = 0;
        int totalSquare = 0;
        for (int i = 0; i < numbers.length; i++) {
            //numbers[i] = random.nextInt();
            numbers[i] = i+1;
        }
        ParallelSquareCounter[] parallelSquareCounters = new ParallelSquareCounter[THREAD_COUNT];
        Thread[] threads = new Thread[THREAD_COUNT];
        // your code here
        for(int i=0;i<THREAD_COUNT;++i){
            parallelSquareCounters[i] = new ParallelSquareCounter(numbers,startIndex);
            Runnable r = parallelSquareCounters[i];
            threads[i] = new Thread(r);
            threads[i].start();
            try{
                threads[i].join();
            }catch(InterruptedException e){
                System.out.println(e);
            }
            totalSquare+=parallelSquareCounters[i].getSquareCount();
            startIndex+=(NUMBER_COUNT/THREAD_COUNT);
            parallelSquareCounters[i].setStartIndex(startIndex);
        }
        System.out.println("Total number of square numbers : "+totalSquare);
        ParallelSquareCounter parallelSquareCounters1 = new ParallelSquareCounter(99);
        System.out.println(parallelSquareCounters1.isSquare(number2));
    }
}