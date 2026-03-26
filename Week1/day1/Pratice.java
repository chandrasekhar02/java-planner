// do one opps problem:-enscapsulation, why we use-to hide the the data by using private variables
class Student{
    private String name;
    private int age; // data hiding

    public void setName (String aalu){
        name=aalu; //accessing the private variable by using set and get method    
        
    }
    public String getName(){
        return name;
    }
    public void setAge(int age){
        this.age=age;
    }
    public int getAge(){
        return age;
    }
}

public class Pratice {//tjhis is the main class
    public static void main(String[] args) { //this is the main method which will be called first
        Student s1=new Student();// the new keyword is used to create an object of the class Student
        s1.setName("John");
        s1.setAge(20);
        System.out.println("Name: "+s1.getName());
        System.out.println("Age: "+s1.getAge());

    }
}// now like cant use the private variable dirctly so i ahve to use getter and setter method
