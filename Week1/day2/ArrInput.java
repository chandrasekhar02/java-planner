package Week1.day2;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrInput {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<Integer> list = new ArrayList<>();
for (int i=0;i<5;i++){
    list.add(in.nextInt());
}
System.out.println(list);
    
        }
    }

