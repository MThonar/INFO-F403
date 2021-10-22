%%// Options of the scanner

%class LexicalAnalyzer	//Name
%unicode		//Use unicode
%line         	//Use line counter (yyline variable)
%column       	//Use character counter by line (yycolumn variable)
%function nextToken
%type Symbol  //Says that the return type is Symbol

%eofval{// called after scanning


%eofval}



// Extended Regular Expressions



AlphaUpperCase = [A-Z]
AlphaLowerCase = [a-z]
Alpha          = {AlphaUpperCase}|{AlphaLowerCase}
Numeric        = [0-9]
AlphaNumeric   = {Alpha}|{Numeric}
Sign           = [+-]
Integer        = {Sign}?(([1-9][0-9]*)|0)
Decimal        = \.[0-9]* //the character .[0-9]*
Exponent       = [eE]{Integer}
Real           = {Integer}{Decimal}?{Exponent}?
Identifier     = {Alpha}{AlphaNumeric}*


Varname 	= [a-z]? [a-z]|[1-9]|[A-Z]*
Number 	= {Sign}?(([1-9][0-9]*)|0)
ShortComments 	= "co" .*
LongComments	= "CO" .* "CO"

%%// Identification of tokens 

//Varname 	= ([a-z]|[A-Z]) [a-z]|[0-9]|[A-Z]*

//spaces // 

" "		{}
"\n"		{}



// various "symbols"
":="		{return new Symbol(LexicalUnit.ASSIGN,yyline, yycolumn, yytext());}
"+" 		{return new Symbol(LexicalUnit.PLUS,yyline, yycolumn, yytext());}
"-"		{return new Symbol(LexicalUnit.MINUS,yyline, yycolumn, yytext());}
"*"		{return new Symbol(LexicalUnit.TIMES,yyline, yycolumn, yytext());}
"/"		{return new Symbol(LexicalUnit.DIVIDE,yyline, yycolumn, yytext());}
"("		{return new Symbol(LexicalUnit.LPAREN,yyline, yycolumn, yytext());}
")"		{return new Symbol(LexicalUnit.RPAREN,yyline, yycolumn, yytext());}
";"		{return new Symbol(LexicalUnit.SEMICOLON,yyline, yycolumn, yytext());}


// If/Else/else/do/while/for/print/read keywords
"if"	        {return new Symbol(LexicalUnit.IF,yyline, yycolumn, yytext());}
"then"        	{return new Symbol(LexicalUnit.THEN,yyline, yycolumn, yytext());}
"else"        	{return new Symbol(LexicalUnit.ELSE,yyline, yycolumn, yytext());}
"while"	{return new Symbol(LexicalUnit.WHILE,yyline, yycolumn, yytext());}
"do" 		{return new Symbol(LexicalUnit.DO,yyline, yycolumn, yytext());}
"for"		{return new Symbol(LexicalUnit.FOR,yyline, yycolumn, yytext());}
"print"	{return new Symbol(LexicalUnit.PRINT,yyline, yycolumn, yytext());}
"read"		{return new Symbol(LexicalUnit.READ, yyline, yycolumn, yytext());}
"begin"	{return new Symbol(LexicalUnit.BEG, yyline, yycolumn, yytext());}
"end"		{return new Symbol(LexicalUnit.END, yyline, yycolumn, yytext());}
"endfor"	{return new Symbol(LexicalUnit.ENDFOR, yyline, yycolumn, yytext());}
"by"		{return new Symbol(LexicalUnit.BY, yyline, yycolumn, yytext());}
"from"		{return new Symbol(LexicalUnit.FROM, yyline, yycolumn, yytext());}
"endwhile"	{return new Symbol(LexicalUnit.ENDWHILE, yyline, yycolumn, yytext());}
"endif"	{return new Symbol(LexicalUnit.ENDIF, yyline, yycolumn, yytext());}
"to"		{return new Symbol(LexicalUnit.TO, yyline, yycolumn, yytext());}
"not"	    	{return new Symbol(LexicalUnit.NOT,yyline, yycolumn);}



// Relational operators
"="	        {return new Symbol(LexicalUnit.EQUAL,yyline, yycolumn, yytext());}
">"	      	{return new Symbol(LexicalUnit.GREATER,yyline, yycolumn, yytext());}
"<"	        {return new Symbol(LexicalUnit.SMALLER,yyline, yycolumn, yytext());}






// C99 variable identifier
//{Identifier}  		{return new Symbol(LexicalUnit.C99VAR,yyline, yycolumn, yytext());} no need of those as it's they are C identifiers


// Variables name
{Varname}		{return new Symbol(LexicalUnit.VARNAME, yyline, yycolumn, yytext());}
// numbers
{Number}		{return new Symbol(LexicalUnit.NUMBER, yyline, yycolumn, yytext());}

// comments to be ignored
{ShortComments}	{}
{LongComments}		{}
