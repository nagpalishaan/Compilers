package p;
import p.A;


public class B {
    public static void main(String[] args) {
        A a = new A();
        System.out.println("Hello " + args[0] + " !");
        a.toString();
        while (true) { 
            System.out.println("Foo"); 
        }

    }
}