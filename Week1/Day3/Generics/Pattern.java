

import java.util.Scanner;

public class Pattern {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the numbers");
        int n = in.nextInt();

        
        int top = (n + 1) / 2;    
        int bottom = n - top; 

        for (int i = 1; i <= top; i++) {
            for (int j = 1; j <= top - i; j++) {// I DO THIS LOOP FOR SPACES
                System.out.print(" ");
            }
            for (int j = 1; j <= (2 * i - 1); j++) {//STAR PRINT
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = bottom; i >= 1; i--) {
            for (int j = 1; j <= top - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (2 * i - 1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        in.close();
    }
}