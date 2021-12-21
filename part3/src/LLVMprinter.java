import java.util.ArrayList;

public class LLVMprinter {
    private ArrayList<Symbol> AST;
    private int globalIncrement = 0;
    private int intermediateIncrement = 1;
    private int plusIncrement = 0;
    private final Symbol program = new Symbol(null, "$<$Program$>$");
    private final Symbol code = new Symbol(null, "$<$Code$>$");
    private final Symbol assign = new Symbol(null, "$<$Assign$>$");
    private final Symbol exprArith = new Symbol(null, "$<$ExprArith$>$");
    private final Symbol If = new Symbol(null, "$<$If$>$");
    private final Symbol cond = new Symbol(null, "$<$Cond$>$");
    private final Symbol comp = new Symbol(null, "$<$Comp$>$");
    private final Symbol While = new Symbol(null, "$<$While$>$");
    private final Symbol For = new Symbol(null, "$<$For$>$");
    private final Symbol print = new Symbol(null, "$<$Print$>$");
    private final Symbol read = new Symbol(null, "$<$Read$>$");

    public LLVMprinter(ArrayList<Symbol> AST){
        this.AST = AST;
    }

    public String getLLVMcode(){
        String LLVMcode = "";
        for(int i = 0; i < AST.size(); i++){
            if(AST.get(i).getValue() == program.getValue()){
                LLVMcode += "define i32 @main() {\nentry:\n";
            }
            else if(AST.get(i).getValue() == assign.getValue()){
                LLVMcode += assign(i);
            }
            else if(AST.get(i).getType() == LexicalUnit.END){
                LLVMcode += "ret i32 0\n}";
            }
        }
        return LLVMcode;
    }

    public boolean isInExprArith(Symbol symbol){
        boolean res = true;
        if(symbol.getType() == program.getValue()
                || symbol.getValue() == cond.getValue()
                || symbol.getValue() == code.getValue()
                || symbol.getValue() == exprArith.getValue()
                || symbol.getValue() == assign.getValue()
                || symbol.getValue() == If.getValue()
                || symbol.getValue() == While.getValue()
                || symbol.getValue() == For.getValue()
                || symbol.getValue() == print.getValue()
                || symbol.getValue() == read.getValue()
                || symbol.getType() == LexicalUnit.END
                || symbol.getType() == LexicalUnit.EQUAL
                || symbol.getType() == LexicalUnit.NOT
                || symbol.getType() == LexicalUnit.GREATER
                || symbol.getType() == LexicalUnit.SMALLER){
            res = false;
        }
        return res;
    }

    public boolean isAnOperator(Symbol symbol){
        boolean res = false;
        if(symbol.getType() == LexicalUnit.PLUS
                || symbol.getType() == LexicalUnit.MINUS
                || symbol.getType() == LexicalUnit.TIMES
                || symbol.getType() == LexicalUnit.DIVIDE){
            res = true;
        }
        return res;
    }

    /*public String program(){
    }*/

    /*public void code(){
        //sert à rien
    }*/

    public String assign(int i){
        ArrayList<Symbol> inExprArith = new ArrayList<>();
        String codeFragment = "";
        String leftTree = AST.get(i+1).getValue().toString();
        int j = i+3;
        while(isInExprArith(AST.get(j))){
            inExprArith.add(AST.get(j));
            j++;
        }
        String rightTree = exprArith(inExprArith);
        codeFragment += "%" + leftTree + " = alloca i32\n" + "\n\nNOUS SOMMES DANS LE ASSIGN, rightTree = " + rightTree + "\n\n\n" + "\nstore i32 %"
                + globalIncrement + ", i32* %" + leftTree + "\n";
        plusIncrement++;
        globalIncrement++;
        return codeFragment;
    }

    public void cond(){

    }

    public String exprArith(ArrayList<Symbol> exprArith){
        String codeFragment = "";
        if(exprArith.get(0).getType() == LexicalUnit.PLUS){
            codeFragment += plus(exprArith);
        }
        return codeFragment;
    }

    public String plus(ArrayList<Symbol> exprArith){
        String codeFragment = "";
        String leftTree = "";
        String rightTree = "";
        System.out.println(!(isAnOperator(exprArith.get(1))));
        System.out.println((exprArith.get(2).getType() == LexicalUnit.PLUS));
        if( !(isAnOperator(exprArith.get(1))) && (exprArith.get(2).getType() == LexicalUnit.PLUS) ){
            System.out.println("dans le if");
            leftTree = exprArith.get(1).getValue().toString();
            ArrayList<Symbol> newExprArith = new ArrayList<>();
            for(int i = 2; i < exprArith.size(); i++){
                newExprArith.add(exprArith.get(i));
            }
            rightTree = plus(newExprArith);
            codeFragment += "\n\nNOUS SOMMES DANS LE IF, leftTree = " + leftTree + "\nrightTree = " + rightTree + "\n\n\n" + "%plus" + plusIncrement + " = alloca i32\n%intermediate" + intermediateIncrement +
                    " = alloca i32\nstore i32 " + leftTree + ", i32* %intermediate" +
                    intermediateIncrement + "\n%" + globalIncrement + " = load i32, i32* intermediate"
                    + intermediateIncrement + "\n";
            intermediateIncrement++;
            globalIncrement++;
            codeFragment += "%intermediate" + intermediateIncrement + " = alloca i32\nstore i32 " +
                    rightTree + ", i32* %intermediate" + intermediateIncrement + "\n%" +
                    globalIncrement + " = load i32, i32* %intermediate" +
                    (intermediateIncrement-1) + "\n";
            globalIncrement++;
            codeFragment += "%" + globalIncrement + " = add i32 %" + (globalIncrement-2) +
                    ",%" + (globalIncrement-1) + "\nstore i32 %" + globalIncrement + ", i32* %plus" + plusIncrement + "\n";
            globalIncrement++;
            codeFragment += "%" + globalIncrement + " = load i32, i32* %plus" + plusIncrement;
        }
        else if( (!isAnOperator(exprArith.get(1))) && (!isAnOperator(exprArith.get(2))) ){
            leftTree = exprArith.get(1).getValue().toString();
            rightTree = exprArith.get(2).getValue().toString();
            System.out.println("dans le else if");
            codeFragment += "%plus" + plusIncrement + " = alloca i32\n%intermediate" + intermediateIncrement +
                    " = alloca i32\nstore i32 " + leftTree + ", i32* %intermediate" +
                    intermediateIncrement + "\n%" + globalIncrement + " = load i32, i32* intermediate"
                    + intermediateIncrement + "\n";
            intermediateIncrement++;
            globalIncrement++;
            codeFragment += "%intermediate" + intermediateIncrement + " = alloca i32\nstore i32 " +
                    rightTree + ", i32* %intermediate" + intermediateIncrement + "\n%" +
                    globalIncrement + " = load i32, i32* %intermediate" +
                    (intermediateIncrement-1) + "\n";
            globalIncrement++;
            codeFragment += "%" + globalIncrement + " = add i32 %" + (globalIncrement-2) +
                    ",%" + (globalIncrement-1) + "\nstore i32 %" + globalIncrement + ", i32* %plus" + plusIncrement + "\n";
            globalIncrement++;
            codeFragment += "%" + globalIncrement + " = load i32, i32* %plus" + plusIncrement;
        }
        else{
            System.out.println("on est pas rentré");
        }
        return codeFragment;
    }

    public void If(){

    }

    public void While(){

    }

    public void For(){

    }

    public void print(){

    }

    public void read(){

    }

}
