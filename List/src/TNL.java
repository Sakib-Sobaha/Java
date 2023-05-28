import java.util.Scanner;
import java.util.*;



public class TNL {
    public static<T> void printLine(List<T> list, int size){
        int k = list.length();
        list.moveToStart();
        int track = list.currPos();
        for(int i=0; i<size; i++){
            if(list.getValue().equals(i)){
                if(list.currPos()+1 == k){
                    System.out.print(list.getValue());
                }
                else {
                    System.out.print(list.getValue() + ",");
                    list.next();
                }
            }
            else{
                System.out.print(",");
            }
        }
        System.out.print("\n");
    }
    public static void main(String[] args) {
        int K, L, M,T;               //K = RS  L = BS M = TS T = TaskNumber
        Integer p =-1;
        List<Integer> rickL = new LList<>();
        List<Integer> busL = new LList<>();
        List<Integer> trainL = new LList<>();
        Scanner input = new Scanner(System.in);
        K = input.nextInt();
        L = input.nextInt();
        for(int i =0; i<K; i++){
            rickL.append(i);
        }
        for(int i=0; i<L; i++){
            busL.append(input.nextInt());
        }
        M = input.nextInt();
        for(int i=0; i<M; i++){
            trainL.append(input.nextInt());
        }




        printLine(rickL,K);

        printLine(busL,K);

        printLine(trainL,K);










    }


    }




