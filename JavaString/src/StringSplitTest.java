import java.util.StringTokenizer;

public class StringSplitTest {
    public static void main(String[] args) {
        String test = "abc,,def,123";
        String[] out = test.split(",");
        System.out.println(out.length);
        for(int i=0; i<out.length; ++i){
            System.out.println(out[i]);
        }
        String t = new String("abc,,def,123");
        StringTokenizer tokens = new StringTokenizer(t,",");
        System.out.println(tokens.countTokens());
        while(tokens.hasMoreTokens()){
            System.out.println(tokens.nextToken());
        }

    }
}
