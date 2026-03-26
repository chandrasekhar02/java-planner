package Week1.CODE;

public class Innerclass {
   static  class test{
    String name;
    public test(String name){
        this.name=name;
    }
    }
    public static void main(String[] args) {
         test a =new test("lala");
        test b = new test("biaka");

        System.out.println(a.name);
        System.out.println(b.name);
    }
}
