package Week1.CODE;

import java.lang.StringBuilder;
public class Mainnn {
    public static void main(String[] args) {
        String a = "Chandra";
        StringBuilder ab = new StringBuilder(a);
        ab.reverse();
        ab.deleteCharAt(2);
        System.out.println( a);
        System.out.println(ab);
    }
}
