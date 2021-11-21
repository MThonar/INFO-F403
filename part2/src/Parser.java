import java.util.ArrayList;
import java.util.Iterator;

public class Parser {
    private final ArrayList<Symbol> tokenSequence;
    private Iterator<Symbol> nextToken;
    private Symbol currentToken;

    public Parser(ArrayList<Symbol> tokenSequence){
        this.tokenSequence = tokenSequence;
        nextToken = tokenSequence.iterator();
        getNextToken();
    }

    private void getNextToken() {
        if (nextToken.hasNext()){
            currentToken = nextToken.next();
        }
    }

    void Program() throws Exception {
        if (currentToken.getType() == LexicalUnit.BEG) {
            System.out.println("1 ");
            match(LexicalUnit.BEG); Code(); match(LexicalUnit.END);
        }
    }

    void Code() throws Exception {
        if (currentToken.getType() == LexicalUnit.END) {
            System.out.println("2 ");
            return;
        }
        System.out.println("3 ");
        InstList();

    }

    void InstList() throws Exception {
        System.out.println("4 ");
        Instruction(); InstListTail();
    }

    void InstListTail() throws Exception {
        if (currentToken.getType() == LexicalUnit.END) {
            System.out.println("6 ");
            return;
        }
        System.out.println("5 ");
        match(LexicalUnit.SEMICOLON); InstList();
    }

    void Instruction() throws Exception {
        switch (currentToken.getType()) {
            case VARNAME : {
                System.out.println("7 ");
                Assign();
            }
            case IF : {
                System.out.println("8 ");
                If();
            }
            case WHILE : {
                System.out.println("9 ");
                While();
            }
            case FOR : {
                System.out.println("10 ");
                For();
            }
            case PRINT : {
                System.out.println("11 ");
                Print();
            }
            case READ : {
                System.out.println("12 ");
                Read();
            }
            default : syntax_error(currentToken.getType());
        }
    }

    void Assign() throws Exception {
        System.out.println("13 ");
        match(LexicalUnit.VARNAME); match(LexicalUnit.ASSIGN); ExprArith();
    }

    void ExprArith() throws Exception {
        System.out.println("14 ");
        C(); D();
    }

    void C() throws Exception {
        System.out.println("15 ");
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
                System.out.println("18 ");
                return;
            }
            case PLUS : {
                System.out.println("16 ");
                match(LexicalUnit.PLUS);
                A();
                D();
            }
            case MINUS : {
                System.out.println("17 ");
                match(LexicalUnit.MINUS);
                A();
                D();
            }
        }
    }

    void A() throws Exception {
        System.out.println("19 ");
        E(); F();
    }

    void E() throws Exception {
        System.out.println("20 ");
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
                System.out.println("23 ");
                return;
            }
            case TIMES: {
                System.out.println("21 ");
                match(LexicalUnit.TIMES);
                B();
                F();
            }
            case DIVIDE: {
                System.out.println("22 ");
                match(LexicalUnit.DIVIDE);
                B();
                F();
            }
        }
    }

    void B() throws Exception {
        switch (currentToken.getType()) {
            case VARNAME : {
                System.out.println("26 ");
                match(LexicalUnit.VARNAME);
            }
            case LPAREN : {
                System.out.println("25 ");
                match(LexicalUnit.LPAREN);
                B();
                match(LexicalUnit.RPAREN);
            }
            case MINUS : {
                System.out.println("24 ");
                match(LexicalUnit.MINUS);
                B();
            }
            case NUMBER : {
                System.out.println("27 ");
                match(LexicalUnit.NUMBER);
            }
            default : syntax_error(currentToken.getType());
        }
    }

    void If() throws Exception {
        System.out.println("28 ");
        match(LexicalUnit.IF); Cond(); match(LexicalUnit.THEN); Code(); IfTail();
    }

    void IfTail() throws Exception {
        switch (currentToken.getType()) {
            case ENDIF : {
                System.out.println("29 ");
                match(LexicalUnit.ENDIF);
            }
            case ELSE : {
                System.out.println("30 ");
                match(LexicalUnit.ELSE);
                Code();
                match(LexicalUnit.ENDIF);
            }
            default : syntax_error(currentToken.getType());
        }
    }

    void Cond() throws Exception {
        switch (currentToken.getType()) {
            case NOT : {
                System.out.println("31 ");
                match(LexicalUnit.NOT);
                Cond();
            }
            case VARNAME:
            case LPAREN:
            case MINUS:
            case NUMBER: {
                System.out.println("32 ");
                SimpleCond();
            }
            default : syntax_error(currentToken.getType());
        }
    }

    void SimpleCond() throws Exception {
        System.out.println("33 ");
        ExprArith(); Comp(); ExprArith();
    }

    void Comp() throws Exception {
        switch (currentToken.getType()) {
            case EQUAL : {
                System.out.println("34 ");
                match(LexicalUnit.EQUAL);
            }
            case GREATER : {
                System.out.println("35 ");
                match(LexicalUnit.GREATER);
            }
            case SMALLER : {
                System.out.println("36 ");
                match(LexicalUnit.SMALLER);
            }
            default : syntax_error(currentToken.getType());
        }
    }

    void While() throws Exception {
        System.out.println("37 ");
        match(LexicalUnit.WHILE); Cond(); match(LexicalUnit.DO); Code(); match(LexicalUnit.ENDWHILE);
    }

    void For() throws Exception {
        System.out.println("38 ");
        match(LexicalUnit.FOR); match(LexicalUnit.VARNAME); match(LexicalUnit.FROM); ExprArith(); match(LexicalUnit.BY);
        ExprArith(); match(LexicalUnit.TO); ExprArith(); match(LexicalUnit.DO); Code(); match(LexicalUnit.ENDFOR);
    }

    void Print(){
        System.out.println("39 ");
        match(LexicalUnit.PRINT); match(LexicalUnit.LPAREN); match(LexicalUnit.VARNAME); match(LexicalUnit.RPAREN);
    }

    void Read(){
        System.out.println("40 ");
        match(LexicalUnit.READ); match(LexicalUnit.LPAREN); match(LexicalUnit.VARNAME); match(LexicalUnit.RPAREN);
    }

    void match(LexicalUnit token) {
        if (currentToken.getType() == token) {
            System.out.println("Token is matched! Good job, keep going!");
            //faire fonctionner le parseTree
            getNextToken();
        }
    }

    public void startParsing() throws Exception {
        /*for (LexicalUnit i : symbol) {
            currentToken = i;
            Program();
        }*/
    }

    public void syntax_error(LexicalUnit token) throws Exception {
        throw new Exception("The token " + token + " raises an issue");
    }
}
