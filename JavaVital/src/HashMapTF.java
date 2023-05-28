import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class HashMapTF {
    public static void main(String[] args) {
        HashMap<String,Integer>salary = new HashMap<>();
        String str;
        int sal;

        salary.put("Sakib Al Hasan",300000);
        salary.put("Mashrafe Bin Mortaza",null);
        salary.put("Mushfiqur Rahim",250000);

        Set<String>set = salary.keySet();

        Iterator<String>itr = set.iterator();
        String name = "Sakib Al Hasan";
        sal = salary.get(name);
        salary.put(name,sal+250000);
        System.out.println(name+" New Balance of Sakib :"+salary.get(name));

        while(itr.hasNext()){
            str = itr.next();
            System.out.println(str+" : "+salary.get(str));
        }


    }
}
