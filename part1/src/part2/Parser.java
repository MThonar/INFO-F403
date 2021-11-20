package part2;

public class Parser {
    void Program(){
        match(begin); code(); match(end)
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
        match(";"); InstList();
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
        match([Varname]); match(':='); ExprArith();
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
                match("*"); B(); F();
                break;
            case "/":
                match("/"); B(); F();
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
                match("("); B(); match(")");
                break;
            case '-':
                match('-'); B();
                break;
            case [Number]:
                match([Number]);
                break;
            default:
                syntax_error(tok); break;
        }
    }

    void If(){
        match('if'); Cond(); match(then); Code(); IfTail();
    }

    void IfTail(){
        token tok = next_token();
        switch(tok){
            case endif:
                match(endif);
                break;
            case "else":
                match("else"); Code(); match(endif);
                break;
            default:
                syntax_error(tok); break;
        }
    }

    void Cond(){
        token tok = next_token();
        switch(tok){
            case not:
                match(not); Cond();
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
}