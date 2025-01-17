package lang.ast; // The generated scanner will belong to the package lang.ast

import lang.ast.LangParser.Terminals; // The terminals are implicitly defined in the parser
import lang.ast.LangParser.SyntaxError;

%%

// define the signature for the generated scanner
%public
%final
%class LangScanner
%extends beaver.Scanner

// the interface between the scanner and the parser is the nextToken() method
%type beaver.Symbol 
%function nextToken 

// store line and column information in the tokens
%line
%column

// this code will be inlined in the body of the generated scanner class
%{
  private beaver.Symbol sym(short id) {
    return new beaver.Symbol(id, yyline + 1, yycolumn + 1, yylength(), yytext());
  }
%}

// macros
WhiteSpace = [ ] | \t | \f | \n | \r
ID = [a-zA-Z] [a-zA-Z0-9]*
Numeral = [0-9]+ ("." [0-9]+)?
Comment = "//" [^\n\r]* [\n\r]?


%%

// discard whitespace information
{WhiteSpace}  { }
{Comment}     { }

// token definitions
"int"         { return sym(Terminals.INT); }
"("           { return sym(Terminals.LPARANTH); }
")"           { return sym(Terminals.RPARANTH); }
"{"           { return sym(Terminals.LCURLY); }
"}"           { return sym(Terminals.RCURLY); }
";"           { return sym(Terminals.SEMICOLON); }
","           { return sym(Terminals.COMMA); }
"="           { return sym(Terminals.ASSIGN); }
"%"           { return sym(Terminals.MOD); }
"*"           { return sym(Terminals.MUL); }
"+"           { return sym(Terminals.ADD); }
"/"           { return sym(Terminals.DIV); }
"-"           { return sym(Terminals.SUB); }
"<"           { return sym(Terminals.LT); }
"<="          { return sym(Terminals.LTE); }
">"           { return sym(Terminals.GT); }
">="          { return sym(Terminals.GTE); }
"!="          { return sym(Terminals.NOT); }
"=="          { return sym(Terminals.EQUAL); }
"if"          { return sym(Terminals.IF); }
"else"        { return sym(Terminals.ELSE); }
"while"       { return sym(Terminals.WHILE); }
"return"      { return sym(Terminals.RETURN); }
{Numeral}     { return sym(Terminals.NUMERAL); }
{ID}          { return sym(Terminals.ID); }
<<EOF>>       { return sym(Terminals.EOF); }

/* error fallback */
[^]           { throw new SyntaxError("Illegal character <"+yytext()+">"); }
