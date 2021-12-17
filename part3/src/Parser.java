import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parser {
    private final Iterator<Symbol> nextToken;
    private Symbol currentToken;
    private final ArrayList<String> rules;
    private final Symbol epsilon = new Symbol(null, "$\\epsilon$");
    private final Symbol code = new Symbol(null, "$<$Code$>$");
    private final Symbol program = new Symbol(null, "$<$Program$>$");
    private final Symbol instList = new Symbol(null, "$<$InstList$>$");
    private final Symbol instListTail = new Symbol(null, "$<$InstListTail$>$");
    private final Symbol instruction = new Symbol(null, "$<$Instruction$>$");
    private final Symbol assign = new Symbol(null, "$<$Assign$>$");
    private final Symbol exprArith = new Symbol(null, "$<$ExprArith$>$");
    private final Symbol C = new Symbol(null, "C");
    private final Symbol D = new Symbol(null, "D");
    private final Symbol A = new Symbol(null, "A");
    private final Symbol E = new Symbol(null, "E");
    private final Symbol F = new Symbol(null, "F");
    private final Symbol B = new Symbol(null, "B");
    private final Symbol If = new Symbol(null, "$<$If$>$");
    private final Symbol ifTail = new Symbol(null, "$<$IfTail$>$");
    private final Symbol cond = new Symbol(null, "$<$Cond$>$");
    private final Symbol simpleCond = new Symbol(null, "$<$SimpleCond$>$");
    private final Symbol comp = new Symbol(null, "$<$Comp$>$");
    private final Symbol While = new Symbol(null, "$<$While$>$");
    private final Symbol For = new Symbol(null, "$<$For$>$");
    private final Symbol print = new Symbol(null, "$<$Print$>$");
    private final Symbol read = new Symbol(null, "$<$Read$>$");

    /**
     * This is the only necessary constructor. It creates an iterator object on
     * the list of tokens given as argument and initialises the list of grammar
     * rules that will be printed at the end of the execution.
     * @param tokenSequence the list of tokens extracted by the lexer.
     */
    public Parser(ArrayList<Symbol> tokenSequence){
        nextToken = tokenSequence.iterator();
        rules = new ArrayList<>();
        getNextToken();
    }

    /**
     * This simple function allows to iterate on each element of the list of tokens
     */
    private void getNextToken() {
        if (nextToken.hasNext()){
            currentToken = nextToken.next();
        }
    }

    /**
     * This function creates the symbol "<Program>" to appear in the parse
     * tree. It then applies the grammar rules according to this variable
     * of the grammar and builds the highest root of the parse tree. This*
     * function is only called once per execution.
     * @return string of the parse tree in LaTeX format
     * @throws Exception calls syntaxError() if a syntax error is encountered
     */
    ParseTree Program() throws Exception {
        ArrayList<ParseTree> highestRoot = new ArrayList<>();
        if (currentToken.getType() == LexicalUnit.BEG) {
            rules.add("1 ");
            match(LexicalUnit.BEG, highestRoot);
            Code(highestRoot);
            match(LexicalUnit.END, highestRoot);
        }
        else {
        	syntax_error(currentToken);
        }
        ParseTree parseTree = new ParseTree(program, highestRoot);
        return parseTree;
    }

    /**
     * This function creates the symbol "<Code>" to appear in the parse tree.
     * Similarly to Program(), it then applies the grammar rules according to
     * this variable of the grammar. Lastly, it creates a parse tree that will
     * be added to the list subTree.
     * @param subTree the list of parse trees that will be added to the highest root
     */
    void Code(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        switch (currentToken.getType()) {
            case END:
            case ENDIF:
            case ELSE:
            case ENDWHILE:
            case ENDFOR: {
                rules.add("2 ");
                ParseTree leaf = new ParseTree(epsilon);
                leafs.add(leaf);
                ParseTree child = new ParseTree(code, leafs);
                subTree.add(child);
                return;
            }
        }
        rules.add("3 ");
        InstList(leafs);
        ParseTree child = new ParseTree(code, leafs);
        subTree.add(child);
    }

    /**
     * Functions corresponding to a variable of the grammar are all similar, cf. Code() doc
     */
    void InstList(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("4 ");
        Instruction(leafs);
        InstListTail(leafs);
        ParseTree child = new ParseTree(instList, leafs);
        subTree.add(child);
    }

    void InstListTail(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        switch (currentToken.getType()) {
            case END:
            case ENDIF:
            case ELSE:
            case ENDWHILE:
            case ENDFOR: {
                rules.add("6 ");
                ParseTree leaf = new ParseTree(epsilon);
                leafs.add(leaf);
                ParseTree child = new ParseTree(instListTail, leafs);
                subTree.add(child);
                return;
            }
        }
        rules.add("5 ");
        match(LexicalUnit.SEMICOLON, subTree);
        InstList(leafs);
        ParseTree child = new ParseTree(instListTail, leafs);
        subTree.add(child);
    }

    void Instruction(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        switch (currentToken.getType()) {
            case VARNAME : {
                rules.add("7 ");
                Assign(leafs);
                ParseTree child = new ParseTree(instruction, leafs);
                subTree.add(child);
                return;
            }
            case IF : {
                rules.add("8 ");
                If(leafs);
                ParseTree child = new ParseTree(instruction, leafs);
                subTree.add(child);
                return;
            }
            case WHILE : {
                rules.add("9 ");
                ParseTree leaf = new ParseTree(instruction);
                leafs.add(leaf);
                While(leafs);
                ParseTree child = new ParseTree(instruction, leafs);
                subTree.add(child);
                return;
            }
            case FOR : {
                rules.add("10 ");
                For(leafs);
                ParseTree child = new ParseTree(instruction, leafs);
                subTree.add(child);
                return;
            }
            case PRINT : {
                rules.add("11 ");
                Print(leafs);
                ParseTree child = new ParseTree(instruction, leafs);
                subTree.add(child);
                return;
            }
            case READ : {
                rules.add("12 ");
                Read(leafs);
                ParseTree child = new ParseTree(instruction, leafs);
                subTree.add(child);
                return;
            }
            default : syntax_error(currentToken);
        }
    }

    void Assign(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("13 ");
        match(LexicalUnit.VARNAME, leafs);
        match(LexicalUnit.ASSIGN, leafs);
        ExprArith(leafs);
        ParseTree child = new ParseTree(assign, leafs);
        subTree.add(child);
    }

    void ExprArith(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("14 ");
        C(leafs);
        D(leafs);
        ParseTree child = new ParseTree(exprArith, leafs);
        subTree.add(child);
    }

    void C(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("15 ");
        A(leafs);
        ParseTree child = new ParseTree(C, leafs);
        subTree.add(child);
    }

    void D(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        switch (currentToken.getType()) {
            case END:
            case SEMICOLON:
            case EQUAL:
            case SMALLER:
            case GREATER:
            case THEN:
            case DO:
            case BY:
            case TO: {
                rules.add("18 ");
                ParseTree leaf = new ParseTree(epsilon);
                leafs.add(leaf);
                ParseTree child = new ParseTree(D, leafs);
                subTree.add(child);
                return;
            }
            case PLUS : {
                rules.add("16 ");
                match(LexicalUnit.PLUS, leafs);
                A(leafs);
                D(leafs);
                ParseTree child = new ParseTree(D, leafs);
                subTree.add(child);
                return;
            }
            case MINUS : {
                rules.add("17 ");
                match(LexicalUnit.MINUS, leafs);
                A(leafs);
                D(leafs);
                ParseTree child = new ParseTree(D, leafs);
                subTree.add(child);
            }
        }
    }

    void A(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("19 ");
        E(leafs);
        F(leafs);
        ParseTree child = new ParseTree(A, leafs);
        subTree.add(child);
    }

    void E(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("20 ");
        B(leafs);
        ParseTree child = new ParseTree(E, leafs);
        subTree.add(child);
    }

    void F(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        switch (currentToken.getType()) {
            case END:
            case PLUS:
            case MINUS:
            case SEMICOLON:
            case EQUAL:
            case SMALLER:
            case GREATER:
            case THEN:
            case DO:
            case BY:
            case TO: {
                rules.add("23 ");
                ParseTree leaf = new ParseTree(epsilon);
                leafs.add(leaf);
                ParseTree child = new ParseTree(F, leafs);
                subTree.add(child);
                return;
            }
            case TIMES: {
                rules.add("21 ");
                match(LexicalUnit.TIMES, leafs);
                B(leafs);
                F(leafs);
                ParseTree child = new ParseTree(F, leafs);
                subTree.add(child);
                return;
            }
            case DIVIDE: {
                rules.add("22 ");
                match(LexicalUnit.DIVIDE, leafs);
                B(leafs);
                F(leafs);
                ParseTree child = new ParseTree(F, leafs);
                subTree.add(child);
            }
        }
    }

    void B(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        switch (currentToken.getType()) {
            case VARNAME : {
                rules.add("26 ");
                match(LexicalUnit.VARNAME, leafs);
                ParseTree child = new ParseTree(B, leafs);
                subTree.add(child);
                return;
            }
            case LPAREN : {
                rules.add("25 ");
                match(LexicalUnit.LPAREN, leafs);
                B(leafs);
                match(LexicalUnit.RPAREN, leafs);
                ParseTree child = new ParseTree(B, leafs);
                subTree.add(child);
                return;
            }
            case MINUS : {
                rules.add("24 ");
                match(LexicalUnit.MINUS, leafs);
                B(leafs);
                ParseTree child = new ParseTree(B, leafs);
                subTree.add(child);
                return;
            }
            case NUMBER : {
                rules.add("27 ");
                match(LexicalUnit.NUMBER, leafs);
                ParseTree child = new ParseTree(B, leafs);
                subTree.add(child);
                return;
            }
            default : syntax_error(currentToken);
        }
    }

    void If(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("28 ");
        match(LexicalUnit.IF, leafs);
        Cond(leafs);
        match(LexicalUnit.THEN, leafs);
        Code(leafs);
        IfTail(leafs);
        ParseTree child = new ParseTree(If, leafs);
        subTree.add(child);
    }

    void IfTail(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        switch (currentToken.getType()) {
            case ENDIF : {
                rules.add("29 ");
                match(LexicalUnit.ENDIF, leafs);
                ParseTree child = new ParseTree(ifTail, leafs);
                subTree.add(child);
                return;
            }
            case ELSE : {
                rules.add("30 ");
                match(LexicalUnit.ELSE, leafs);
                Code(leafs);
                match(LexicalUnit.ENDIF, leafs);
                ParseTree child = new ParseTree(ifTail, leafs);
                subTree.add(child);
                return;
            }
            default : syntax_error(currentToken);
        }
    }

    void Cond(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        switch (currentToken.getType()) {
            case NOT : {
                rules.add("31 ");
                match(LexicalUnit.NOT, leafs);
                Cond(leafs);
                ParseTree child = new ParseTree(cond, leafs);
                subTree.add(child);
                return;
            }
            case VARNAME:
            case LPAREN:
            case MINUS:
            case NUMBER: {
                rules.add("32 ");
                SimpleCond(leafs);
                ParseTree child = new ParseTree(cond, leafs);
                subTree.add(child);
                return;
            }
            default : syntax_error(currentToken);
        }
    }

    void SimpleCond(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("33 ");
        ExprArith(leafs);
        Comp(leafs);
        ExprArith(leafs);
        ParseTree child = new ParseTree(simpleCond, leafs);
        subTree.add(child);
    }

    void Comp(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        switch (currentToken.getType()) {
            case EQUAL : {
                rules.add("34 ");
                match(LexicalUnit.EQUAL, leafs);
                ParseTree child = new ParseTree(comp, leafs);
                subTree.add(child);
                return;
            }
            case GREATER : {
                rules.add("35 ");
                match(LexicalUnit.GREATER, leafs);
                ParseTree child = new ParseTree(comp, leafs);
                subTree.add(child);
                return;
            }
            case SMALLER : {
                rules.add("36 ");
                match(LexicalUnit.SMALLER, leafs);
                ParseTree child = new ParseTree(comp, leafs);
                subTree.add(child);
                return;
            }
            default : syntax_error(currentToken);
        }
    }

    void While(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("37 ");
        match(LexicalUnit.WHILE, leafs);
        Cond(leafs);
        match(LexicalUnit.DO, leafs);
        Code(leafs);
        match(LexicalUnit.ENDWHILE, leafs);
        ParseTree child = new ParseTree(While, leafs);
        subTree.add(child);
    }

    void For(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("38 ");
        match(LexicalUnit.FOR, leafs);
        match(LexicalUnit.VARNAME, leafs);
        match(LexicalUnit.FROM, leafs);
        ExprArith(leafs);
        match(LexicalUnit.BY, leafs);
        ExprArith(leafs);
        match(LexicalUnit.TO, leafs);
        ExprArith(leafs);
        match(LexicalUnit.DO, leafs);
        Code(leafs);
        match(LexicalUnit.ENDFOR, leafs);
        ParseTree child = new ParseTree(For, leafs);
        subTree.add(child);
    }

    void Print(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("39 ");
        match(LexicalUnit.PRINT, leafs);
        match(LexicalUnit.LPAREN, leafs);
        match(LexicalUnit.VARNAME, leafs);
        match(LexicalUnit.RPAREN, leafs);
        ParseTree child = new ParseTree(print, leafs);
        subTree.add(child);
    }

    void Read(ArrayList<ParseTree> subTree) throws Exception {
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("40 ");
        match(LexicalUnit.READ, leafs);
        match(LexicalUnit.LPAREN, leafs);
        match(LexicalUnit.VARNAME, leafs);
        match(LexicalUnit.RPAREN, leafs);
        ParseTree child = new ParseTree(read, leafs);
        subTree.add(child);
    }

    /**
     * @param token
     * @param tree
     */
    void match(LexicalUnit token, ArrayList<ParseTree> tree) throws Exception {
        if (currentToken.getType() == token) {
            ParseTree parseTree = new ParseTree(currentToken);
            tree.add(parseTree);
            getNextToken();
        }
        else {
            System.out.println("error du match");
            syntax_error(currentToken);
        }
    }

    /**
     * This method calls the Program() method, which then makes the
     * parser follow the grammar each rule one after the other. Whenever
     * a rule is applied, it is added to the list "rules".
     * @return
     * @throws Exception
     */
    public ParseTree startParsing() throws Exception {
        ParseTree parseTree = Program();
        for (String i : rules) {
            System.out.printf(i);
        }
        System.out.println(" ");
        ArrayList<Symbol> symbolList = new ArrayList<>();
        symbolList.add(program);
        extractNeededSymbols(parseTree, symbolList);
        for(int i = 0; i < symbolList.size(); i++){
            System.out.println(symbolList.get(i).getValue());
        }
        return parseTree;
    }

    /**
     * This function allows to throw exception when the program identifies
     * a syntax error. In case of a missing ";", the program states that the
     * it is the token following the missing ";" that raises the issue.
     * @param token the token that raised the issue
     */
    public void syntax_error(Symbol token) throws Exception {
        throw new Exception("Syntax error at line " + token.getLine() + " generated by the token " + token.getValue());
    }

    public void buildSymbolList(Symbol labelToCheck, ArrayList<Symbol> listToBuild){
        if(labelToCheck.getValue() == program.getValue()){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getValue() == code.getValue()){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getValue() == exprArith.getValue()){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getValue() == assign.getValue()){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getValue() == If.getValue()){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getValue() == While.getValue()){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getValue() == For.getValue()){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getValue() == print.getValue()){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getValue() == read.getValue()){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getType() == LexicalUnit.PLUS){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getType() == LexicalUnit.MINUS){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getType() == LexicalUnit.TIMES){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getType() == LexicalUnit.DIVIDE){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getType() == LexicalUnit.LPAREN){
            listToBuild.add(labelToCheck);
        }
        else if(labelToCheck.getType() == LexicalUnit.RPAREN){
            listToBuild.add(labelToCheck);
        }
    }

    public void extractNeededSymbols(ParseTree parseTree, ArrayList<Symbol> listToBuild){
        List<ParseTree> children = parseTree.getChildren();
        for(int i = 0; i < children.size(); i++){
            buildSymbolList(children.get(i).getLabel(), listToBuild);
            if(children.get(i).getLabel().getValue() == "B"){
                Symbol number = children.get(i).getChildren().get(0).getLabel();
                listToBuild.add(number);
            }
            if(children.get(i).getChildren().size() != 0){
                extractNeededSymbols(children.get(i), listToBuild);
            }
        }
    }
}