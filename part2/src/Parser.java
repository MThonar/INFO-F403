import java.util.ArrayList;
import java.util.Iterator;

public class Parser {
    private final Iterator<Symbol> nextToken;
    private Symbol currentToken;
    private final ArrayList<String> rules;

    public Parser(ArrayList<Symbol> tokenSequence){
        nextToken = tokenSequence.iterator();
        rules = new ArrayList<>();
        getNextToken();
    }

    private void getNextToken() {
        if (nextToken.hasNext()){
            currentToken = nextToken.next();
        }
    }

    void Program() throws Exception {
        if (currentToken.getType() == LexicalUnit.BEG) {
            rules.add("1 ");
            match(LexicalUnit.BEG); Code(); match(LexicalUnit.END);
        }
    }

    void Code() throws Exception {
        switch (currentToken.getType()) {
            case END:
            case ENDIF:
            case ELSE:
            case ENDWHILE:
            case ENDFOR: {
                rules.add("2 ");
                return;
            }
        }
        rules.add("3 ");
        InstList();

    }

    void InstList() throws Exception {
        rules.add("4 ");
        Instruction(); InstListTail();
    }

    void InstListTail() throws Exception {
        switch (currentToken.getType()) {
            case END:
            case ENDIF:
            case ELSE:
            case ENDWHILE:
            case ENDFOR: {
                rules.add("6 ");
                return;
            }
        }
        rules.add("5 ");
        match(LexicalUnit.SEMICOLON); InstList();
    }

    void Instruction() throws Exception {
        switch (currentToken.getType()) {
            case VARNAME : {
                rules.add("7 ");
                Assign();
                return;
            }
            case IF : {
                rules.add("8 ");
                If();
                return;
            }
            case WHILE : {
                rules.add("9 ");
                While();
                return;
            }
            case FOR : {
                rules.add("10 ");
                For();
                return;
            }
            case PRINT : {
                rules.add("11 ");
                Print();
                return;
            }
            case READ : {
                rules.add("12 ");
                Read();
                return;
            }
            default : syntax_error(currentToken.getType());
        }
    }

    void Assign() throws Exception {
        rules.add("13 ");
        match(LexicalUnit.VARNAME); match(LexicalUnit.ASSIGN); ExprArith();
    }

    void ExprArith() throws Exception {
        rules.add("14 ");
        C(); D();
    }

    void C() throws Exception {
        rules.add("15 ");
        A();
    }

    void D() throws Exception {
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
                return;
            }
            case PLUS : {
                rules.add("16 ");
                match(LexicalUnit.PLUS);
                A();
                D();
                return;
            }
            case MINUS : {
                rules.add("17 ");
                match(LexicalUnit.MINUS);
                A();
                D();
            }
        }
    }

    void A() throws Exception {
        rules.add("19 ");
        E(); F();
    }

    void E() throws Exception {
        rules.add("20 ");
        B();
    }

    void F() throws Exception {
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
                return;
            }
            case TIMES: {
                rules.add("21 ");
                match(LexicalUnit.TIMES);
                B();
                F();
                return;
            }
            case DIVIDE: {
                rules.add("22 ");
                match(LexicalUnit.DIVIDE);
                B();
                F();
            }
        }
    }

    void B() throws Exception {
        switch (currentToken.getType()) {
            case VARNAME : {
                rules.add("26 ");
                match(LexicalUnit.VARNAME);
                return;
            }
            case LPAREN : {
                rules.add("25 ");
                match(LexicalUnit.LPAREN);
                B();
                match(LexicalUnit.RPAREN);
                return;
            }
            case MINUS : {
                rules.add("24 ");
                match(LexicalUnit.MINUS);
                B();
                return;
            }
            case NUMBER : {
                rules.add("27 ");
                match(LexicalUnit.NUMBER);
                return;
            }
            default : syntax_error(currentToken.getType());
        }
    }

    void If() throws Exception {
        rules.add("28 ");
        match(LexicalUnit.IF); Cond(); match(LexicalUnit.THEN); Code(); IfTail();
    }

    void IfTail() throws Exception {
        switch (currentToken.getType()) {
            case ENDIF : {
                rules.add("29 ");
                match(LexicalUnit.ENDIF);
                return;
            }
            case ELSE : {
                rules.add("30 ");
                match(LexicalUnit.ELSE);
                Code();
                match(LexicalUnit.ENDIF);
                return;
            }
            default : syntax_error(currentToken.getType());
        }
    }

    void Cond() throws Exception {
        switch (currentToken.getType()) {
            case NOT : {
                rules.add("31 ");
                match(LexicalUnit.NOT);
                Cond();
                return;
            }
            case VARNAME:
            case LPAREN:
            case MINUS:
            case NUMBER: {
                rules.add("32 ");
                SimpleCond();
                return;
            }
            default : syntax_error(currentToken.getType());
        }
    }

    void SimpleCond() throws Exception {
        rules.add("33 ");
        ExprArith(); Comp(); ExprArith();
    }

    void Comp() throws Exception {
        switch (currentToken.getType()) {
            case EQUAL : {
                rules.add("34 ");
                match(LexicalUnit.EQUAL);
                return;
            }
            case GREATER : {
                rules.add("35 ");
                match(LexicalUnit.GREATER);
                return;
            }
            case SMALLER : {
                rules.add("36 ");
                match(LexicalUnit.SMALLER);
                return;
            }
            default : syntax_error(currentToken.getType());
        }
    }

    void While() throws Exception {
        rules.add("37 ");
        match(LexicalUnit.WHILE); Cond(); match(LexicalUnit.DO); Code(); match(LexicalUnit.ENDWHILE);
    }

    void For() throws Exception {
        rules.add("38 ");
        match(LexicalUnit.FOR); match(LexicalUnit.VARNAME); match(LexicalUnit.FROM); ExprArith(); match(LexicalUnit.BY);
        ExprArith(); match(LexicalUnit.TO); ExprArith(); match(LexicalUnit.DO); Code(); match(LexicalUnit.ENDFOR);
    }

    void Print(){
        rules.add("39 ");
        match(LexicalUnit.PRINT); match(LexicalUnit.LPAREN); match(LexicalUnit.VARNAME); match(LexicalUnit.RPAREN);
    }

    void Read(){
        rules.add("40 ");
        match(LexicalUnit.READ); match(LexicalUnit.LPAREN); match(LexicalUnit.VARNAME); match(LexicalUnit.RPAREN);
    }

    void match(LexicalUnit token) {
        if (currentToken.getType() == token) {
            //faire fonctionner le parseTree
            getNextToken();
        }
    }

    public void startParsing() throws Exception {
        Program();
        for (String i : rules) {
            System.out.printf(i);
        }
        System.out.println(" ");
    }

    public void syntax_error(LexicalUnit token) throws Exception {
        throw new Exception("The token " + token + " raises an issue");
    }
}
