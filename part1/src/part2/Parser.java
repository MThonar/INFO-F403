package part2;

import part1.LexicalUnit;

public class Parser {

    void Program(){
        match(LexicalUnit.BEG); Code(); match(LexicalUnit.END);
    }

    void Code(){
        token tok = next_token();
        switch(tok){
            case end: return;
        }
        InstList();
    }

    void InstList(){
        Instruction(); InstListTail();
    }

    void InstListTail(){
        token tok = next_token();
        switch(tok){
            case end: return;
        }
        match(LexicalUnit.SEMICOLON); InstList();
    }

    void Instruction(){
        token tok = next_token();
        switch(tok){
            case [Varname]:
                Assign();
                break;
            case "if":
                If();
                break;
            case "while":
                While();
                break;
            case "for":
                For();
                break;
            case print:
                Print();
                break;
            case read:
                Read();
                break;
            default:
                syntax_error(tok); break;
        }
    }

    void Assign(){
        match(LexicalUnit.VARNAME); match(LexicalUnit.ASSIGN); ExprArith();
    }

    void ExprArith(){
        C(); D();
    }

    void C(){
        A();
    }

    void D(){
    }

    void A(){
        E(); F();
    }

    void E(){
        B();
    }

    void F(){
        token tok = next_token();
        switch(tok){
            case end:
            case "+":
            case "-":
            case ";":
            case "=":
            case "<":
            case ">":
            case then:
            case "do":
            case by:
            case to: return;
            case "*":
                match(LexicalUnit.TIMES); B(); F();
                break;
            case "/":
                match(LexicalUnit.DIVIDE); B(); F();
                break;
        }
    }

    void B(){
        token tok = next_token();
        switch(tok){
            case [Varname]:
                match([Varname]);
                break;
            case '(':
                match(LexicalUnit.LPAREN); B(); match(LexicalUnit.RPAREN);
                break;
            case '-':
                match(LexicalUnit.MINUS); B();
                break;
            case [Number]:
                match(LexicalUnit.NUMBER);
                break;
            default:
                syntax_error(tok); break;
        }
    }

    void If(){
        match(LexicalUnit.IF); Cond(); match(LexicalUnit.THEN); Code(); IfTail();
    }

    void IfTail(){
        token tok = next_token();
        switch(tok){
            case endif:
                match(LexicalUnit.ENDIF);
                break;
            case "else":
                match(LexicalUnit.ELSE); Code(); match(LexicalUnit.ENDIF);
                break;
            default:
                syntax_error(tok); break;
        }
    }

    void Cond(){
        token tok = next_token();
        switch(tok){
            case not:
                match(LexicalUnit.NOT); Cond();
                break;
            case [Varname]:
                SimpleCond();
                break;
            case '(':
                SimpleCond();
                break;
            case '-':
                SimpleCond();
                break;
            case [Number]:
                SimpleCond();
                break;
            default:
                syntax_error(tok); break;
        }
    }

    void SimpleCond(){
        ExprArith(); Comp(); ExprArith();
    }

    void Comp(){
        token tok = next_token();
        switch(tok){
            case '=':
                match('=');
                break;
            case '>':
                match('>');
                break;
            case '<':
                match('<');
                break;
            default:
                syntax_error(tok); break;
        }
    }

    void While(){
        match("while"); Cond(); match("do"); Code(); match('endwhile');
    }

    boolean match(LexicalUnit token) {
        Token currentToken;
        token == currentToken;
    }
}