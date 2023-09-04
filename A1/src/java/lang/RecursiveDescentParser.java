package lang; 
import lang.ast.LangParser; 
import lang.ast.LangScanner; 
import static lang.ast.LangParser.Terminals.*; 

/** 
     * Abstract base class for recursive decent parsers. 
     ** You should implement the parseProgram() method to parse a MiniS program. 
 * */

public abstract class RecursiveDescentParser { 
    public LangScanner scanner;  /* note to self: changed from private */
    private beaver.Symbol currentToken;

    /** Initialize the parser and start parsing via the parseProgram() method.*/ 
    public void parse(LangScanner scanner) {
        this.scanner = scanner; 
        parseProgram(); 
        accept(EOF); //Ensure all input is processed. 
    }

    protected void parseProgram() {
        int currToken = peek();
        if(currToken == FOR) {
            parseFor();
        }
        else if(currToken == IF) {
            parseIf();
        }
        else if(currToken == ID) {
            parseAssignment();
        }
        else {
            error("Invalid token");
        }
    }

    protected void parseFor() {
        accept(FOR);
        parseAssignment();
        accept(UNTIL);
        parseExpr();
        accept(DO);
        parseProgram();
        accept(OD);
    }
    
    protected void parseIf() {
        accept(IF);
        parseExpr();
        accept(THEN);
        parseProgram();
        accept(FI);
    }

    protected void parseAssignment() {
        accept(ID);
        accept(ASSIGN);
        parseExpr();
    }

    protected void parseExpr() {
        int currToken = peek();
        switch (currToken) {
            case ID:
                accept(ID);
                break;
            case NUMERAL:
                accept(NUMERAL);
                break;
            case NOT:
                accept(NOT);
                parseExpr();
                break;
            default:
                error("Invalid token");
                break;
        }
    }

    /** Returns the current token without proceeding to the next.*/ 
    protected int peek(){ 
        if (currentToken == null) accept(); 
        return currentToken.getId(); 
    }

    /** Read the next token from the scanner.*/ 
    protected void accept() { 
        try { 
            currentToken = scanner.nextToken(); 
         } catch(Exception e){ 
            throw new RuntimeException(e);
        } 
    }

    /** Ensure the current token is of a certain type; then read the next.*/ 
    protected void accept(int expectedToken) { 
        if(peek()!=expectedToken){ 
            error("expected token " + 
            LangParser.Terminals.NAMES[expectedToken]+ 
            " got token " + 
            LangParser.Terminals.NAMES[currentToken.getId()]);
        } 
        accept(); 
    }

    protected static void error(String message) { 
        throw new RuntimeException(message); 
    } 
}