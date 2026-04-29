package Week1.CODE;


public class Prime {
    public static void main(String[] args) {
int num =2;


System.out.println(num + " is prime "+ isPrime (num));
        isOddorEven(num);
}
        static boolean isPrime(int n){
            if (n<=1)return false;
            for(int i=2;i*i<=n;i++){
                if (n%i==0) return false;
            }
            return true;
        }    
    static void isOddorEven(int a){
    if(a %2==0){
        System.out.println("even");

    }
    else{
        System.out.println("odd");
    }
    }




    }
