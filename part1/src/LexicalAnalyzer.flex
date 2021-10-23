%%// Options of the scanner

%class LexicalAnalyzer	//Name
%unicode		//Use unicode
%line         		//Use line counter (yyline variable)
%column       		//Use character counter by line (yycolumn variable)
%function nextToken
%type Symbol  		//Says that the return type is Symbol

// Return value of the program
%eofval{
		return new Symbol(LexicalUnit.END_OF_STREAM, yyline, yycolumn, yytext());
%eofval}

// Extended Regular Expressions
VarName	= [A-Z]|[a-z] ([0-9]|[A-Z]|[a-z])*
Number		= [1-9][0-9]*|0
ShortComments	= "co" .*
LongComments	= "CO" .* "CO"

%%// Identification of tokens 
" "		{}
"\n"	{}
"\t"	{}

// Various "symbols"
":="	{return new Symbol(LexicalUnit.ASSIGN, yyline, yycolumn, yytext());}
"+" 	{return new Symbol(LexicalUnit.PLUS, yyline, yycolumn, yytext());}
"-"		{return new Symbol(LexicalUnit.MINUS, yyline, yycolumn, yytext());}
"*"		{return new Symbol(LexicalUnit.TIMES, yyline, yycolumn, yytext());}
"/"		{return new Symbol(LexicalUnit.DIVIDE, yyline, yycolumn, yytext());}
"("		{return new Symbol(LexicalUnit.LPAREN, yyline, yycolumn, yytext());}
")"		{return new Symbol(LexicalUnit.RPAREN, yyline, yycolumn, yytext());}
";"		{return new Symbol(LexicalUnit.SEMICOLON, yyline, yycolumn, yytext());}

// If/Else/else/do/while/for/print/read keywords
"if"		{return new Symbol(LexicalUnit.IF, yyline, yycolumn, yytext());}
"then"		{return new Symbol(LexicalUnit.THEN, yyline, yycolumn, yytext());}
"else"		{return new Symbol(LexicalUnit.ELSE, yyline, yycolumn, yytext());}
"while"	    {return new Symbol(LexicalUnit.WHILE, yyline, yycolumn, yytext());}
"do"		{return new Symbol(LexicalUnit.DO, yyline, yycolumn, yytext());}
"for"		{return new Symbol(LexicalUnit.FOR, yyline, yycolumn, yytext());}
"print"	    {return new Symbol(LexicalUnit.PRINT, yyline, yycolumn, yytext());}
"read"		{return new Symbol(LexicalUnit.READ, yyline, yycolumn, yytext());}
"begin"	    {return new Symbol(LexicalUnit.BEG, yyline, yycolumn, yytext());}
"end"		{return new Symbol(LexicalUnit.END, yyline, yycolumn, yytext());}
"endfor"	{return new Symbol(LexicalUnit.ENDFOR, yyline, yycolumn, yytext());}
"by"		{return new Symbol(LexicalUnit.BY, yyline, yycolumn, yytext());}
"from"		{return new Symbol(LexicalUnit.FROM, yyline, yycolumn, yytext());}
"endwhile"	{return new Symbol(LexicalUnit.ENDWHILE, yyline, yycolumn, yytext());}
"endif"	    {return new Symbol(LexicalUnit.ENDIF, yyline, yycolumn, yytext());}
"to"		{return new Symbol(LexicalUnit.TO, yyline, yycolumn, yytext());}
"not"		{return new Symbol(LexicalUnit.NOT, yyline, yycolumn, yytext());}

// Relational operators
"="		{return new Symbol(LexicalUnit.EQUAL, yyline, yycolumn, yytext());}
">"		{return new Symbol(LexicalUnit.GREATER, yyline, yycolumn, yytext());}
"<"		{return new Symbol(LexicalUnit.SMALLER, yyline, yycolumn, yytext());}

// Variables name
{VarName}	{return new Symbol(LexicalUnit.VARNAME, yyline, yycolumn, yytext());}

// Numbers
{Number}	{return new Symbol(LexicalUnit.NUMBER, yyline, yycolumn, yytext());}

// Comments to be ignored
{ShortComments}	  {}
{LongComments}    {}