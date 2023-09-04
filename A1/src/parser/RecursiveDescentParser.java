packagelang; 
import lang.ast.LangParser; 
import lang.ast.LangScanner; 
import staticlang.ast.LangParser.Terminals.*; 

/** 
     * Abstract base class for recursive decent parsers. 
     * *You should implement the parseProgram() method to parse a MiniS program. 
 * */

public abstract class RDPTemplate{ 
    privateLangScannerscanner; 
    privatebeaver.SymbolcurrentToken;

    /** Initialize the parser and start parsing via the parseProgram() method.*/ 
    public void parse(LangScanner scanner) {
        this.scanner=scanner; 
        parseProgram(); 
        accept(EOF); //Ensureallinputisprocessed. 
    }

    %terminals FOR, UNTIL, DO, OD, IF, THEN, FI, NOT, ASSIGN, ID, NUMERAL;

    // TO DO
    protected abstract void parseProgram() {
        int currentToken = peek();
        if(currentToken == FOR) {
            parseFor();
        }
        else if(currentToken == IF) {
            parseIf();
        }
        else if(currentToken == ID) {
            parseAssignment();
        }
        else {
            error("Invalid token");
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
            currentToken=scanner.nextToken(); 
        }catch(Exception e){ 
            throw ne RuntimeException(e);
        } 
    }

    /** Ensure the current token is of a certain type; then read the next.*/ 
    protected void accept(int expectedToken) { 
        if(peek()!=expectedToken){ 
            error("expected token"+ 
            LangParser.Terminals.NAMES[expectedToken]+ 
            "got token"+ 
            LangParser.Terminals.NAMES[currentToken.getId()]);
        } 
        accept(); 
    }

    protected static void error(Stringmessage) { 
        thrownewRuntimeException(message); 
    } 
}