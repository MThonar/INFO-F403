import java.util.ArrayList;

public class LLVMprinter {
    private ArrayList<Symbol> AST;
    private int globalIncrement = 0;
    private int intermediateIncrement = 1;
    private boolean plusAlreadyUsed = false;
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
        codeFragment += "%" + leftTree + " = alloca i32\n" + rightTree + "store i32 %" + globalIncrement + ", i32* %"
                + leftTree + "\n";
        globalIncrement++;
        return codeFragment;
    }

    public void cond(){

    }

    public String exprArith(ArrayList<Symbol> exprArith){
        String codeFragment = "";
        String leftTree = exprArith.get(1).getValue().toString();
        String rightTree = exprArith.get(2).getValue().toString();
        if(exprArith.get(0).getType() == LexicalUnit.PLUS){
            if(!plusAlreadyUsed){
                codeFragment += "%plus = alloca i32\nintermediate" + intermediateIncrement +
                        " = alloca i32\nstore i32 " + leftTree + ", i32* %intermediate" +
                        intermediateIncrement + "\n";
                intermediateIncrement++;
                codeFragment += "intermediate" + intermediateIncrement + " = alloca i32\nstore i32 " +
                        rightTree + ", i32* intermediate" + intermediateIncrement + "\n%" +
                        globalIncrement + " = load i32, i32* %intermediate" +
                        (intermediateIncrement-1) + "\n";
                globalIncrement++;
                codeFragment += "%" + globalIncrement + " = load i32, i32* %intermediate" +
                        (intermediateIncrement) + "\n";
                globalIncrement++;
                codeFragment += "%" + globalIncrement + " = add i32 %" + (globalIncrement-2) +
                        ",%" + (globalIncrement-1) + "\nstore i32 %" + globalIncrement + ", i32* %plus\n";
                globalIncrement++;
                codeFragment += "%" + globalIncrement + " = load i32, i32* %plus";
                plusAlreadyUsed = true;
            }
            else{
                codeFragment += "store i32 " + leftTree + ", i32* %add\n" + "%" + globalIncrement + " = load i32, i32* %add\n";
                globalIncrement++;
                codeFragment += "%" + globalIncrement + " = add i32 %" + (globalIncrement-1) + "," + rightTree + "\n";
            }
        }
        return codeFragment;
    }

    public void plus(ArrayList<Symbol> exprArith){

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
