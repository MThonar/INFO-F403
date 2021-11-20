void program(){
	match(begin)
}
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
}

void instlist(){
	token tok = next_token();
	switch(tok){
	case [Varname]:
		instruction(); h();
		break;
	case if:
		
	}
}
