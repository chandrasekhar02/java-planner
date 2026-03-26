 
class BankHdfc{
    private int mybalance;
    private String name;
public void setmybalance(int balance){
    mybalance=balance;
}
    public int getmybalance(){
        return mybalance;
    }
    public void setname(String name){
        this.name=name;
    }
    public String getname(){
        return name;
    }
}
 public class Enscapsulation{
    public static void main (String []args){
        BankHdfc b1=new BankHdfc();
        b1.setmybalance(2000);
        b1.setname("Chandrasekhar");
        System.out.println("NAME:"+b1.getname());
        System.out.println("balane:"+b1.getmybalance());
    }
 }