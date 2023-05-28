public class Player {
    public static int length;
    private int number;
    private String name;
    private double salary;

    // you are not allowed to write any other constructor
    public Player(int number, String name) {
        setNumber(number);
        setName(name);
    }

    // add your code here
    public void setNumber(int number){
        this.number = number;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSalary(double salary){
        double salary1 = salary;
        this.salary = salary1;
    }
    public double getSalary(){
        return this.salary;
    }
    public String getName(){
        return this.name;
    }
}
