public class Platypus extends Mammal implements Venomous{
    Platypus(String name,int age){
        super(name,age);
    }

    @Override
    public boolean isLethalToAdultHumans() {
        return false;
    }
}
