import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        Integer t,s,booth1Finish = 0,booth2Finish = 0;       //t -> time customer enters the bank     s-> service time
        n = input.nextInt();
        Queue<Integer> boothq1 = new LQueue<Integer>(n);
        Queue<Integer>boothq2 = new LQueue<Integer>(n);
        int count = n;
        while(count > 0){
            if(count == 0){
                System.exit(0);
            }
            t= input.nextInt();
            s = input.nextInt();
            if(count == n){
                Integer temp = t + s;
                booth1Finish +=(t+s);
                boothq1.enqueue(temp);
            }
            else if(count == n - 1){
                Integer temp = t + s;
                booth2Finish +=(t+s);
                boothq2.enqueue(temp);
            }
            else if(count < n - 1){
                Integer temp1 = boothq1.frontValue();
                Integer temp2 = boothq2.frontValue();
                if(temp1 < temp2){
                    Integer temp3 = temp1 + s;
                    booth1Finish += s;
                    boothq1.enqueue(booth1Finish);
                    Integer m = boothq1.dequeue();
                }
                else if(temp1 > temp2){
                    Integer temp3 = temp2 + s;
                    booth2Finish += s;
                    boothq2.enqueue(booth2Finish);
                    Integer m = boothq2.dequeue();
                }
                else{
                    Integer temp3 = temp1 + s;
                    booth1Finish += s;
                    boothq1.enqueue(booth1Finish);
                    Integer m = boothq1.dequeue();
                }
            }
            count--;
        }
        System.out.println("Booth 1 finishes service at t = "+booth1Finish);
        System.out.println("Booth 2 finishes service st t = "+booth2Finish);
    }
}
