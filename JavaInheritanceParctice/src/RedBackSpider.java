public class RedBackSpider extends Arachnid implements Venomous{
    RedBackSpider(String name,int age){
        super(name,age);
    }

    @Override
    public boolean isLethalToAdultHumans() {
        return false;
    }
}
