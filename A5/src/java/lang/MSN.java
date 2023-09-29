package lang;

import lang.ast.*;
import java.util.TreeSet;

/**
 * Calculates the MSN depth of a SimpliC file
 */
public class MSN extends TraversingVisitor{
    private int counter;
    private TreeSet<Integer> maxMSN = new TreeSet<>();

    private void add(int n) {
        if (counter < n) counter = n;
        maxMSN.add(++n);
    }

    public static int result(ASTNode n) {
        
        MSN msn = new MSN();
        n.accept(msn, 0);
        Integer[] msnArray = msn.maxMSN.toArray(new Integer[msn.maxMSN.size()]);
        //subtract 1 bc it is always 1 extra
        return msnArray[msnArray.length-1]-1;
    }


    public Object visit(Function node, Object data) {
        int currentMSN = (int) data;
        add(++currentMSN);
        return visitChildren(node, currentMSN);
    }


    public Object visit(While node, Object data) {
        int currentMSN = (int) data;
        add(++currentMSN);
        return visitChildren(node, currentMSN);
    }

    public Object visit(IfElse node, Object data) {
        int currentMSN = (int) data;
        add(++currentMSN);
        return visitChildren(node, currentMSN);
    }
}