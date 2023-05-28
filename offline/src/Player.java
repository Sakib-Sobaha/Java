public class Player {
    private int number;
    private String name;
    private double salary;

    // you are not allowed to write any other constructor
    public Player(int number, String name) {
        setNumber(number);
        setName(name);
    }
    public void setNumber(int num){
        number = num;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }

    // add your code here
    public double getSalary(){
        double salary = this.salary;
        return salary;
    }
}
