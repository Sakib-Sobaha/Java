public class StringConstructorTest {
    public static void main(String[] args) {
        char charArray[] = {'b','i','r','t','h',' ','d','a','y'};
        byte byteArray[] = {(byte) 'n',(byte)'e',(byte)'w',(byte)' ',(byte)'y',(byte)'e',(byte)'a',(byte)'r'};

        String s = new String("Hello");
        String s1 = new String();
        String s2 = new String(s);
        String s3 = new String(charArray);
        String s4 = new String(charArray,6,3);
        String s5 = new String(byteArray,4,4);
        String s6 = new String(byteArray);
        String s7 = "Wel" + "come";
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s6);
        System.out.println(s7);
    }
}
