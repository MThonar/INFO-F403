import java.util.ArrayList;
import java.util.Iterator;

public class Parser {
    private final Iterator<Symbol> nextToken;
    private Symbol currentToken;
    private final ArrayList<String> rules;
    private final Symbol epsilon = new Symbol(null, "$\\epsilon$");


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
    String Program() throws Exception {
        Symbol program = new Symbol(null, "$<$Program$>$");
        ArrayList<ParseTree> highestRoot = new ArrayList<>();
        if (currentToken.getType() == LexicalUnit.BEG) {
            rules.add("1 ");
            match(LexicalUnit.BEG, highestRoot);
            Code(highestRoot);
            match(LexicalUnit.END, highestRoot);
        }
        ParseTree parseTree = new ParseTree(program, highestRoot);
        return parseTree.toLaTeX();
    }

    /**
     * This function creates the symbol "<Code>" to appear in the parse tree.
     * Similarly to Program(), it then applies the grammar rules according to
     * this variable of the grammar. Lastly, it creates a parse tree that will
     * be added to the list subTree.
     * @param subTree the list of parse trees that will be added to the highest root
     */
    void Code(ArrayList<ParseTree> subTree) throws Exception {
        Symbol code = new Symbol(null, "$<$Code$>$");
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
        Symbol instList = new Symbol(null, "$<$InstList$>$");
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("4 ");
        Instruction(leafs);
        InstListTail(leafs);
        ParseTree child = new ParseTree(instList, leafs);
        subTree.add(child);
    }

    void InstListTail(ArrayList<ParseTree> subTree) throws Exception {
        Symbol instListTail = new Symbol(null, "$<$InstListTail$>$");
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
        Symbol instruction = new Symbol(null, "$<$Instruction$>$");
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
        Symbol assign = new Symbol(null, "$<$Assign$>$");
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("13 ");
        match(LexicalUnit.VARNAME, leafs);
        match(LexicalUnit.ASSIGN, leafs);
        ExprArith(leafs);
        ParseTree child = new ParseTree(assign, leafs);
        subTree.add(child);
    }

    void ExprArith(ArrayList<ParseTree> subTree) throws Exception {
        Symbol exprArith = new Symbol(null, "$<$ExprArith$>$");
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("14 ");
        C(leafs);
        D(leafs);
        ParseTree child = new ParseTree(exprArith, leafs);
        subTree.add(child);
    }

    void C(ArrayList<ParseTree> subTree) throws Exception {
        Symbol C = new Symbol(null, "C");
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("15 ");
        A(leafs);
        ParseTree child = new ParseTree(C, leafs);
        subTree.add(child);
    }

    void D(ArrayList<ParseTree> subTree) throws Exception {
        Symbol D = new Symbol(null, "D");
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
        Symbol A = new Symbol(null, "A");
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("19 ");
        E(leafs);
        F(leafs);
        ParseTree child = new ParseTree(A, leafs);
        subTree.add(child);
    }

    void E(ArrayList<ParseTree> subTree) throws Exception {
        Symbol E = new Symbol(null, "E");
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("20 ");
        B(leafs);
        ParseTree child = new ParseTree(E, leafs);
        subTree.add(child);
    }

    void F(ArrayList<ParseTree> subTree) throws Exception {
        Symbol F = new Symbol(null, "F");
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
        Symbol B = new Symbol(null, "B");
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
        Symbol If = new Symbol(null, "$<$If$>$");
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
        Symbol ifTail = new Symbol(null, "$<$IfTail$>$");
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
        Symbol cond = new Symbol(null, "$<$Cond$>$");
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
        Symbol simpleCond = new Symbol(null, "$<$SimpleCond$>$");
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("33 ");
        ExprArith(leafs);
        Comp(leafs);
        ExprArith(leafs);
        ParseTree child = new ParseTree(simpleCond, leafs);
        subTree.add(child);
    }

    void Comp(ArrayList<ParseTree> subTree) throws Exception {
        Symbol comp = new Symbol(null, "$<$Comp$>$");
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
        Symbol While = new Symbol(null, "$<$While$>$");
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
        Symbol For = new Symbol(null, "$<$For$>$");
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

    void Print(ArrayList<ParseTree> subTree){
        Symbol print = new Symbol(null, "$<$Print$>$");
        ArrayList<ParseTree> leafs = new ArrayList<>();
        rules.add("39 ");
        match(LexicalUnit.PRINT, leafs);
        match(LexicalUnit.LPAREN, leafs);
        match(LexicalUnit.VARNAME, leafs);
        match(LexicalUnit.RPAREN, leafs);
        ParseTree child = new ParseTree(print, leafs);
        subTree.add(child);
    }

    void Read(ArrayList<ParseTree> subTree){
        Symbol read = new Symbol(null, "$<$Read$>$");
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
    void match(LexicalUnit token, ArrayList<ParseTree> tree) {
        if (currentToken.getType() == token) {
            ParseTree parseTree = new ParseTree(currentToken);
            tree.add(parseTree);
            getNextToken();
        }
    }

    public String startParsing() throws Exception {
        String string = Program();
        for (String i : rules) {
            System.out.printf(i);
        }
        System.out.println(" ");
        return string;
    }

    public void syntax_error(Symbol token) throws Exception {
        throw new Exception("Syntax error at line " + token.getLine() + " generated by the token " + token.getValue());
    }
}
