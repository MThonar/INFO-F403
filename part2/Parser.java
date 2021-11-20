void Program(){
	match(begin); code(); match(end)
}
/* fonction avec epsilon
void code(){
	token tok = next_token();
	switch(tok){
	case end:
	case endif:
	case else:
	case endwhile:
	case endfor: return;
	}
	instlist();
}*/

void Instlist(){
	Instruction(); InstListTail();
	/*token tok = next_token();
	switch(tok){
	case [Varname]:
		Instruction(); InstListTail();
		break;
	case ('if'):
		Instruction(); InstListTail();
		break;
	case ('while'):
		Instruction(); InstListTail();
		break;
	case ('for'):
		Instruction(); InstListTail();
		break;
	case ('print'):
		Instruction(); InstListTail();
		break;
	case ('read'):
		Instruction(); InstListTail();
		break;
	default:
		syntax_error(tok); break;
	}*/
}
/* continet epsilon
void InstListTail(){
	token tok = next_token()
}*/

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
	/*token tok = next_token();
	switch(tok){
	
	
	
	default:
		syntax_error(tok); break;
	}*/
}
void ExprArith(){
	C(); D();
	/*token tok = next_token();
	switch(tok){
	case [Varname]:
		C(); D();
		break;
	case '(':
		C(); D();
		break;
	case '-':
		C(); D();
		break;
	case [Number]:
		C(); D();
		break;
	default:
		syntax_error(tok); break;
	}*/
}

void C(){
	A();
}
/* contient eps
void D(){
}*/

void A(){
	E(); F();
}

void E(){
	B();
}
/*eps
void F(){
}*/

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


