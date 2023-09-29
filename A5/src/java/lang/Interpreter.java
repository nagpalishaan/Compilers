public class Interpreter {

    // Parses in an AST
    public static void main(String[] args) {

        // Define program AST
        Program program = ??;

        // Check for compile-time errors
        if (??) {
            System.exit(1);
        }
        else {
            // Runs by calling eval on a program AST
            program.eval();
        }

    }
}


