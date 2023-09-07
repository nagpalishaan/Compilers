package p;

import p.A;

public class B { 
    public static void main(String[] args) { 
        
        if (args.length > 0) {
            System.out.println("Hello" + args[0] + "!"); 
        }
        else {
            System.out.println("Hello!"); 
        }

        A a = new A();
        System.out.println(a.toString());

        // while(true) {
        //     System.out.println("Foo"); 
        // }
    } 
}