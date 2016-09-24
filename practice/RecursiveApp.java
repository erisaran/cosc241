package practice1;

public class RecursiveApp{


    public static void main(String[]args){
        System.out.println(digits(-2574324));
        System.out.println(sumOfDigits(-257));

    }//end main
    
    public static long digits(long n){
        if (n ==  0){
            return 0;
        }
        return 1 + digits(n/10);
    }

    public static long sumOfDigits(long n){
        int neg = 1;
        if (n<0){
            neg *= -1;
            n *= -1;
        }else if(n<10){
            return (n%10)*neg;
        }
        return (n%10)*neg + sumOfDigits((n/10)*neg);
    }

}
