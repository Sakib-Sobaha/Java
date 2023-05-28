
public class Student {
    int id;
    String name;
    double cgpa;

    Student(int id,String name,double cgpa){
        this.id = id;
        this.name= name;
        this.cgpa = cgpa;
    }

    void setId(int id){
        this.id = id;
    }

    int getId(){
        return this.id;
    }

    void setName(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }

    void setCgpa(double cgpa){
        this.cgpa = cgpa;
    }

    double getCgpa(){
        return this.cgpa;
    }

    public static void main(String[] args) {
        Student studentinfo = new Student(1905021,"Rafi",4.00);
        System.out.println(studentinfo.getName());
        System.out.println(studentinfo.getId());
        System.out.println(studentinfo.getCgpa());

    }

}
