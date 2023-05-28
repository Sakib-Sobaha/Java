import java.util.Scanner;
public class ScannerTest {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter an integer : ");
        int a = scn.nextInt();
        System.out.println("Enter a string");
        String str = scn.next();
        System.out.println("You entered : ");
        System.out.println(a);
        System.out.println(str);
        System.out.println("Enter a series of integers : Ctrl-D to stop ");
        while(scn.hasNextInt()){
            System.out.println(scn.nextInt());

        }
    }
}
