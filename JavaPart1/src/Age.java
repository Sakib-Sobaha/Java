public class Age {
    private int age;

    public Age(int age) {
        this.age = age;
    }

    public void Age(int age){
        this.age = age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }

    @Override
    public String toString(){
        //this.age.toString();
        return this.age+"  ";
    }
}
