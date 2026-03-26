import java.util.Scanner;

public class Numbers {
    int sum(int a, int b){
        return a+b;
    }
    int sum(int a, int c ,int b){
        return a+b+c;

    }
    public static void main(String[] args){
        Numbers obj =new Numbers();
        obj.sum(4, 9);
        obj.sum(6, 8, 05);
         // obj.sum(4,6,6,7);compile time err
        System.out.println();
    } 
}
