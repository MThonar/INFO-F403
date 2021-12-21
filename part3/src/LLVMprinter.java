import java.util.ArrayList;

public class LLVMprinter {
    private int numberOfRecursion = 0;
    private final ArrayList<Symbol> AST;
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
        codeFragment += "%" + leftTree + " = alloca i32\n" + rightTree + "store i32 %"
                + (inExprArith.size()-1) + ", i32* %" + leftTree + "\n";
        return codeFragment;
    }

    public void cond(){

    }

    public String exprArith(ArrayList<Symbol> exprArith){
        String codeFragment = "";
        int counter = 0;
        if(exprArith.get(0).getType() == LexicalUnit.PLUS){
            for(Symbol symbol : exprArith){
                if(symbol.getType() == LexicalUnit.PLUS){
                    counter++;
                }
            }
            numberOfRecursion = counter - 2;
            codeFragment += plus(exprArith);
        }
        return codeFragment;
    }

    public String plus(ArrayList<Symbol> exprArith){
        String codeFragment = "";
        int localIncrement = 0;
        String leftTree = "";
        String rightTree = "";
        if( !(isAnOperator(exprArith.get(1))) && (exprArith.get(2).getType() == LexicalUnit.PLUS) ){
            leftTree = exprArith.get(1).getValue().toString();
            ArrayList<Symbol> newExprArith = new ArrayList<>();
            for(int i = 2; i < exprArith.size(); i++){
                newExprArith.add(exprArith.get(i));
            }
            codeFragment += "%plus" + localIncrement + " = alloca i32\n%intermediate" + localIncrement +
                    " = alloca i32\nstore i32 " + leftTree + ", i32* %intermediate" + localIncrement + "\n%" +
                    localIncrement + " = load i32, i32* intermediate" + localIncrement + "\n";
            rightTree = plus(newExprArith);
            codeFragment += rightTree;
            localIncrement += newExprArith.size();
            codeFragment += "%" + (localIncrement+1) + " = add i32 %" + 0 +
                    ",%" + localIncrement + "\n";
        }
        else if( (!isAnOperator(exprArith.get(1))) && (!isAnOperator(exprArith.get(2))) ){
            leftTree = exprArith.get(1).getValue().toString();
            rightTree = exprArith.get(2).getValue().toString();
            codeFragment += "%plus" + (1+numberOfRecursion) + " = alloca i32\n%intermediate" + (1+numberOfRecursion) +
                    " = alloca i32\nstore i32 " + leftTree + ", i32* %intermediate" +
                    (1+numberOfRecursion) + "\n%" + (1+numberOfRecursion) + " = load i32, i32* intermediate"
                    + (1+numberOfRecursion) + "\n";
            codeFragment += "%intermediate" + (2+numberOfRecursion) + " = alloca i32\nstore i32 " +
                    rightTree + ", i32* %intermediate" + (2+numberOfRecursion) + "\n%" +
                    (2+numberOfRecursion) + " = load i32, i32* %intermediate" +
                    (2+numberOfRecursion) + "\n";
            codeFragment += "%" + (3+numberOfRecursion) + " = add i32 %" + (1+numberOfRecursion) +
                    ",%" + (2+numberOfRecursion) + "\n";
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
