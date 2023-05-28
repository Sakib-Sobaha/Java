import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Input.txt");
        Scanner read = new Scanner(file);
        BinarySearchTree<Integer,Integer> bst = new BinarySearchTree<Integer,Integer>();
        while(read.hasNextLine()){
            String inp = read.nextLine();
            StringTokenizer st = new StringTokenizer(inp," ");
            String s1 = st.nextToken();
            if(s1.equals("F")){
                String s2 = st.nextToken();
                int item = Integer.parseInt(s2);
                bst.findItem(item);
            }
            else if(s1.equals("I")){
                String s2 = st.nextToken();
                int item = Integer.parseInt(s2);
                bst.insertItem(item,item);
                bst.printBST();
                System.out.print("\n");
            }
            else if(s1.equals("D")){
                String s2 = st.nextToken();
                int item = Integer.parseInt(s2);
                bst.deleteItem(item);
                System.out.print("\n");
                //bst.printBST();
            }
            else if(s1.equals("T")){
                String s2 = st.nextToken();
                if(s2.equals("Pre")){
                    bst.printPreOrder();
                    System.out.print("\n");
                }
                else if(s2.equals("In")){
                    bst.printInOrder();
                    System.out.print("\n");
                }
                else if(s2.equals("Post")){
                    bst.printPostOrder();
                    System.out.print("\n");

                }
            }
            /*System.out.print(s1);
            String s2 = st.nextToken();
            System.out.println(s2);*/
            //System.out.println(read.nextLine());
        }
    }

}


