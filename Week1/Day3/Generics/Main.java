class A{

    public void hello1(Object o){
        System.out.println("hello1");
    }
      public void hello1(int s){
        System.out.println("hello2");
    }
}
public class Main{


 public static void main(String[] args) {
    A a = new A();
    a.hello1(null);

}
}
