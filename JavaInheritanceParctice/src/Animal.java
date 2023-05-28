public class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }
    @Override
    public String toString(){
        getClass().getSimpleName();

        //return null;
        return this.name+ " " + "is a "+this.name+","+"aged "+this.age+" ";
    }

}