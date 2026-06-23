class Star{
    public static void printStar(int n){
        for(int i=0; i<n ; i++){
            System.out.print("*");
        }
        System.out.println();
    }
}
public class Method{
    public static void main (String args[]){
        Star.printStar(4);
        Star.printStar(6);
        Star.printStar(8);
    }
}