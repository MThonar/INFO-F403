import java.util.ArrayList;

public class LLVMprinter {
    private int numberOfOperators = 0;
    private int globalIncrement = 1;
    private int plusIncrement = 1;
    private int timesIncrement = 1;
    private int intermediateIncrement = 1;
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

    public boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    public String getLLVMcode(){
        String LLVMcode = "";
        for(int i = 0; i < AST.size(); i++){
            if(AST.get(i).getValue() == program.getValue()){
                LLVMcode += "@.strR = private unnamed_addr constant [3 x i8] c\"%d\\00\", align 1\n" +
                        "\n" +
                        "define i32* @readInt() {\n" +
                        "  %1 = alloca i32, align 4\n" +
                        "  %2 = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.strR, i32" +
                        " 0, i32 0), i32* %1)\n" +
                        "  ret i32* %1\n" +
                        "}\n" +
                        "\n" +
                        "declare i32 @scanf(i8*, ...)\n" +
                        "\n" +
                        "@.strP = private unnamed_addr constant [4 x i8] c\"%d\\0A\\00\", align 1\n" +
                        "\n" +
                        "define void @println(i32 %x) {\n" +
                        "  %1 = alloca i32, align 4\n" +
                        "  store i32 %x, i32* %1, align 4\n" +
                        "  %2 = load i32, i32* %1, align 4\n" +
                        "  %3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %2)\n" +
                        "  ret void\n" +
                        "}\n" +
                        "\n" +
                        "declare i32 @printf(i8*, ...)\n" +
                        "\n" +
                        "define i32* @assign1(i32 %x) {\n" +
                        "  %a = alloca i32\n" +
                        "  store i32 %x, i32* %a\n" +
                        "  ret i32* %a\n" +
                        "}\n" +
                        "\n" +
                        "define i32* @assign2(i32* %x) {\n" +
                        "  %a = alloca i32\n" +
                        "  %1 = load i32, i32* %x\n" +
                        "  store i32 %1, i32* %a\n" +
                        "  ret i32* %a\n" +
                        "}\n" +
                        "\n" +
                        "define i32 @plus(i32 %x, i32 %y) {\n" +
                        "  %plus = alloca i32\n" +
                        "  %intermediate1 = alloca i32\n" +
                        "  store i32 %x, i32* %intermediate1\n" +
                        "  %1 = load i32, i32* %intermediate1\n" +
                        "  %intermediate2 = alloca i32\n" +
                        "  store i32 %y, i32* %intermediate2\n" +
                        "  %2 = load i32, i32* %intermediate2\n" +
                        "  %3 = add i32 %1,%2\n" +
                        "  ret i32 %3\n" +
                        "}\n" +
                        "\n" +
                        "define void @if(i32 %a, i32 %b) {\n" +
                        "%cond = icmp slt i32 %a, %b\n" +
                        "br i1 %cond, label %if, label %else\n" +
                        "if:\n" +
                        "call void @println(i32 %a)\n" +
                        "ret void\n" +
                        "else:\n" +
                        "call void @println(i32 %b)\n" +
                        "ret void\n" +
                        "}\n" +
                        "\n" +
                        "define void @for(i32 %a, i32 %x, i32 %y) {\n" +
                        "%i = alloca i32\n" +
                        "store i32 %a, i32* %i\n" +
                        "br label %forLoop\n" +
                        "forLoop:\n" +
                        "%1 = load i32, i32* %i\n" +
                        "%2 = icmp slt i32 %1, %y\n" +
                        "br i1 %2, label %innerFor, label %endFor\n" +
                        "innerFor:\n" +
                        "call void @println(i32 %1)\n" +
                        "%3 = add i32 %1,%x\n" +
                        "store i32 %3, i32* %i\n" +
                        "br label %forLoop\n" +
                        "endFor:\n" +
                        "ret void\n" +
                        "}\n" +
                        "\n" +
                        "define i32 @main(){\n";
            }
            else if(AST.get(i).getValue() == read.getValue()){
                LLVMcode += read(i);
            }
            else if(AST.get(i).getValue() == print.getValue()){
                LLVMcode += print(i);
            }
            else if(AST.get(i).getValue() == assign.getValue()){
                LLVMcode += assign(i);
            }
            else if(AST.get(i).getValue() == If.getValue()){
                LLVMcode += If(i);
                i += 12;
                System.out.println(i);
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
        String codeFragment = "";
        String leftTree = AST.get(i+1).getValue().toString();
        if(isAnOperator(AST.get(i+3))){
            ArrayList<Symbol> inExprArith = new ArrayList<>();
            int j = i+3;
            while(isInExprArith(AST.get(j))){
                inExprArith.add(AST.get(j));
                j++;
            }
            String rightTree = exprArith(inExprArith);
            codeFragment += rightTree;
            codeFragment += "%" + leftTree + " = call i32* @assign1(i32 %plus" + plusIncrement + ")\n";
            plusIncrement++;
        }
        else if(isNumeric(AST.get(i+3).getValue().toString())){
            String rightTree = AST.get(i+3).getValue().toString();
            codeFragment += "%" + leftTree + " = call i32* @assign1(i32 " + rightTree + ")\n%" + globalIncrement +
                    " = load i32, i32* %" + leftTree + "\n";
            globalIncrement++;
        }
        else if(!isNumeric(AST.get(i+3).getValue().toString())){
            String rightTree = AST.get(i+3).getValue().toString();
            codeFragment += "%" + leftTree + " = call i32* @assign2(i32* %" + rightTree + ")\n%" + globalIncrement +
                    " = load i32, i32* %" + leftTree + "\n";
            globalIncrement++;
        }
        return codeFragment;
    }

    public void cond(){

    }

    public String exprArith(ArrayList<Symbol> exprArith){
        String codeFragment = "";
        int counter = 0;
        if(exprArith.get(0).getType() == LexicalUnit.PLUS){
            for(Symbol symbol : exprArith){
                if(isAnOperator(symbol)){
                    counter++;
                }
            }
            numberOfOperators = counter - 2;
            codeFragment += plus(exprArith);
        }
        else if(exprArith.size() == 1){
            codeFragment += "%intermediate" + intermediateIncrement + " = alloca i32\nstore i32 "
                    + exprArith.get(0).getValue().toString() +
                    ", i32* %intermediate" + intermediateIncrement + "\n%" + globalIncrement +
                    " = load i32, i32* %intermediate" + intermediateIncrement + "\n";
            intermediateIncrement++;
            globalIncrement++;
        }
        return codeFragment;
    }

    public String plus(ArrayList<Symbol> exprArith){
        String codeFragment = "";
        String leftTree = "";
        String rightTree = "";
        if( !(isAnOperator(exprArith.get(1))) && (exprArith.get(2).getType() == LexicalUnit.PLUS) ){
            leftTree = exprArith.get(1).getValue().toString();
            ArrayList<Symbol> newExprArith = new ArrayList<>();
            for(int i = 2; i < exprArith.size(); i++){
                newExprArith.add(exprArith.get(i));
            }
            codeFragment += "%plus" + plusIncrement + " = alloca i32\n%intermediate" + intermediateIncrement +
                    " = alloca i32\nstore i32 " + leftTree + ", i32* %intermediate" + intermediateIncrement + "\n%" +
                    globalIncrement + " = load i32, i32* %intermediate" + intermediateIncrement + "\n";
            globalIncrement++;
            intermediateIncrement++;
            plusIncrement++;
            rightTree = plus(newExprArith);
            codeFragment += rightTree;
            codeFragment += "%" + (globalIncrement + 1) + " = add i32 %" + (numberOfOperators+1) +
                    ",%" + globalIncrement + "\n";
            globalIncrement++;
            numberOfOperators--;
        }
        else if( !(isAnOperator(exprArith.get(1))) && (exprArith.get(2).getType() == LexicalUnit.TIMES) ){
            leftTree = exprArith.get(1).getValue().toString();
            ArrayList<Symbol> newExprArith = new ArrayList<>();
            for(int i = 2; i < exprArith.size(); i++){
                newExprArith.add(exprArith.get(i));
            }
            codeFragment += "%plus" + plusIncrement + " = alloca i32\n%intermediate" + intermediateIncrement +
                    " = alloca i32\nstore i32 " + leftTree + ", i32* %intermediate" + intermediateIncrement + "\n%" +
                    globalIncrement + " = load i32, i32* %intermediate" + intermediateIncrement + "\n";
            globalIncrement++;
            intermediateIncrement++;
            plusIncrement++;
            rightTree = times(newExprArith);
            codeFragment += rightTree;
            codeFragment += "%" + (globalIncrement + 1) + " = add i32 %" + (numberOfOperators+1) +
                    ",%" + globalIncrement + "\n";
            globalIncrement++;
            numberOfOperators--;
        }
        /*else if( (exprArith.get(1).getType() == LexicalUnit.TIMES) && (exprArith.get(4).getType() == LexicalUnit.PLUS) ){
            ArrayList<Symbol> newExprArith = new ArrayList<>();
            for(int i = 2; i < exprArith.size(); i++){
                newExprArith.add(exprArith.get(i));
            }
            codeFragment += "%plus" + plusIncrement + " = alloca i32\n";
            leftTree = exprArith.get(1).getValue().toString();
            codeFragment += leftTree;
            rightTree = times(newExprArith);
            codeFragment += rightTree;
            codeFragment += "%" + (globalIncrement + 1) + " = add i32 %" + numberOfOperators +
                    ",%" + globalIncrement + "\n";
            globalIncrement++;
            numberOfOperators--;
        }*/
        else if( (!isAnOperator(exprArith.get(1))) && (!isAnOperator(exprArith.get(2))) ){
            leftTree = exprArith.get(1).getValue().toString();
            rightTree = exprArith.get(2).getValue().toString();
            codeFragment += "%plus" + plusIncrement + " = call i32 @plus(i32 " + leftTree + ", i32 " + rightTree + ")\n";
            /*codeFragment += "%plus" + plusIncrement + " = alloca i32\n%intermediate" + intermediateIncrement +
                    " = alloca i32\nstore i32 " + leftTree + ", i32* %intermediate" +
                    intermediateIncrement + "\n%" + globalIncrement + " = load i32, i32* %intermediate"
                    + intermediateIncrement + "\n";
            globalIncrement++;
            plusIncrement++;
            intermediateIncrement++;
            codeFragment += "%intermediate" + intermediateIncrement + " = alloca i32\nstore i32 " +
                    rightTree + ", i32* %intermediate" + intermediateIncrement + "\n%" +
                    globalIncrement + " = load i32, i32* %intermediate" +
                    intermediateIncrement + "\n";
            globalIncrement++;
            intermediateIncrement++;
            codeFragment += "%" + globalIncrement + " = add i32 %" + (globalIncrement-2) +
                    ",%" + (globalIncrement-1) + "\n";*/
        }
        return codeFragment;
    }

    public String times(ArrayList<Symbol> exprArith){
        String codeFragment = "";
        String leftTree = "";
        String rightTree = "";
        if( (!isAnOperator(exprArith.get(1))) && (!isAnOperator(exprArith.get(2))) ){
            leftTree = exprArith.get(1).getValue().toString();
            rightTree = exprArith.get(2).getValue().toString();
            codeFragment += "%times" + timesIncrement + " = alloca i32\n%intermediate" + intermediateIncrement +
                    " = alloca i32\nstore i32 " + leftTree + ", i32* %intermediate" +
                    intermediateIncrement + "\n%" + globalIncrement + " = load i32, i32* %intermediate"
                    + intermediateIncrement + "\n";
            globalIncrement++;
            timesIncrement++;
            intermediateIncrement++;
            codeFragment += "%intermediate" + intermediateIncrement + " = alloca i32\nstore i32 " +
                    rightTree + ", i32* %intermediate" + intermediateIncrement + "\n%" +
                    globalIncrement + " = load i32, i32* %intermediate" +
                    intermediateIncrement + "\n";
            globalIncrement++;
            intermediateIncrement++;
            codeFragment += "%" + globalIncrement + " = mul i32 %" + (globalIncrement-2) +
                    ",%" + (globalIncrement-1) + "\n";
        }
        return codeFragment;
    }

    public String If(int i){
        String codeFragment = "";

        return codeFragment;
    }

    public void While(){

    }

    public void For(){

    }

    public String print(int i){
        String codeFragment = "call void @println(i32 %" + globalIncrement + ")\n";
        globalIncrement++;
        return codeFragment;
    }

    public String read(int i){
        String codeFragment = "%" + AST.get(i+1).getValue().toString() + " = call i32* @readInt()\n";
        codeFragment += "%" + globalIncrement + " = load i32, i32* %" + AST.get(i+1).getValue().toString() + "\n";
        globalIncrement++;
        return codeFragment;
    }
}
