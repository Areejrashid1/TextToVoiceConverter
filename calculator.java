class Product{
    public static int multiply(int a , int b){
        int num = a*b; 
       return num;
    }
}
public class calculator{
    public static void main (String args []){
        int result = Product.multiply (7,8);
        System.out.println("Answer: "+ result);
        System.out.println("Answer: "+ Product.multiply(2,4));
        System.out.println("Answer: "+ Product.multiply (5,10));
    }
}