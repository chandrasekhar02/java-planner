package Week1.day2;

import java.util.ArrayList;

public class Arrylistexample {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        
        list.add(56);
        list.add(59);
        list.add(54);
        list.add(51);
        list.add(58);
        list.add(57);
        
        list.add(56);
        list.add(59);
        list.add(54);
        list.add(51);
        list.add(58);
        list.add(57);
        System.out.println(list.contains(45));
        list.set(0,99);
        System.out.println(list);
        // list.remove(2);
        System.out.println(list);
        
    }
}
