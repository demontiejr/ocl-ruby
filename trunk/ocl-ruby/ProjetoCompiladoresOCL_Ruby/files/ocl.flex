/*Secao de codigo do usuario*/

package analisadorLexico;

/*********************************************************
* Universidade Federal de Campina Grande - UFCG          *
* Centro de Engenharia Eletrica e Informatica - CEEI     *
* Departamento de Sistemas e Computacao - DSC            *
*                                                        *
* Projeto da disciplina de Compiladores, 2011.1          *
* Analisador Lexico para OCL                             *
*                                                        *
* Grupo: Francisco Demontie dos Santos Junior - 20911084 *
*        Izabela Vanessa de Almeida Melo - 20811018      *
*        Savyo Igor da Nobrega Santos - 20811034         *
*********************************************************/

import java_cup.runtime.*;
import java.util.List;
import java.util.ArrayList;
import analisadorSintatico.Symbols;

%%

/*Opcoes de configuracao*/

%public
%standalone
%class ScannerOCL
%line
%column
%cup
%state STRING

/*Declaracoes de codigo utilitario para o analisador lexico*/

%{
	StringBuilder string;
	public List<String> foundTokens = new ArrayList<String>();
	
	public boolean hasNextToken() {
		return !zzAtEOF;
	}
	
	private Symbol symbol(int type, String token) {
		foundTokens.add("Token encontrado: " + token + "\n");
		return new Symbol(type, yyline, yycolumn);
	}
	
	private Symbol symbol(int type, Object value, String token) {
		foundTokens.add("Token encontrado: " + token + "; Valor: " + value.toString() + "\n");
		return new Symbol(type, yyline, yycolumn, value);
	}
	
	/*Metodo para lancamento de erros lexicos*/
	private void error(int line, String message){
		throw new RuntimeException("Erro lexico na linha " + (line+1) + ": " + message);
	}
	
%}

/*--DEFINICOES DE MACROS PARA EXPRESSOES REGULARES--*/

whiteSpace = [ \t\f\r\n]
comment = "--"([^\r\n])*[\n\r]
oclSuperType = "OclType" | "OclAny"
oclBasicType = "Real" | "Integer" | "String" | "Boolean"
oclCollection = "Collection" | "Set" | "Bag" | "Sequence"
AROBAPRE = "@pre"
ESC = "\\n"	| "\\r" | "\\t" | "\\b" | "\\f"	| "\\\'" | "\\\\"
strDelim = \'
DIGIT = [0-9]
ALPHA = [a-zA-Z_]
INT = {DIGIT}+ 
FLOAT = {INT} "." {DIGIT}+ ([eE][+-]?{DIGIT}+)?
bool = "true" | "false"
id = {ALPHA} ({ALPHA} | {DIGIT})*

/*--FIM DAS DEFINICOES DE MACROS--*/

%%

<YYINITIAL>{
	//eliminando espacos em branco e comentarios
	{whiteSpace}			{}
	{comment}				{}
	
	//palavras reservadas e operadores
	"context"				{return symbol(Symbols.CONTEXT, "CONTEXT");}
	"pre"					{return symbol(Symbols.PRE, "PRE");}
	"post"					{return symbol(Symbols.POST, "POST");}
	"if"					{return symbol(Symbols.IF, "IF");}
	"then"					{return symbol(Symbols.THEN, "THEN");}
	"else"					{return symbol(Symbols.ELSE, "ELSE");}
	"endif"					{return symbol(Symbols.ENDIF, "ENDIF");}
	"("						{return symbol(Symbols.LPAREN, "LPAREN");}
	")"						{return symbol(Symbols.RPAREN, "RPAREN");}
	"{"						{return symbol(Symbols.LCURLY, "LCURLY");}
	"}"						{return symbol(Symbols.RCURLY, "RCURLY");}
	"|"						{return symbol(Symbols.PIPE, "PIPE");}
	".."					{return symbol(Symbols.POINT_POINT, "POINT_POINT");}
	","						{return symbol(Symbols.COMA, "COMA");}
	":"						{return symbol(Symbols.POINTS, "POINTS");}
	"::"					{return symbol(Symbols.FOUR_POINTS, "FOUR_POINTS");}
	"*"						{return symbol(Symbols.STAR, "STAR");}
	"/"						{return symbol(Symbols.DIV, "DIV");}
	"+"						{return symbol(Symbols.PLUS, "PLUS");}
	//operadores pos fixos
	"."						{return symbol(Symbols.POINT, "POINT");}
	"->"					{return symbol(Symbols.ARROW, "ARROW");}
	//operadores relacionais
	"<="					{return symbol(Symbols.LE, "LE");}
	">="					{return symbol(Symbols.GE, "GE");}
	"<>"					{return symbol(Symbols.NE, "NE");}
	"="						{return symbol(Symbols.EQ, "EQ");}
	"<"						{return symbol(Symbols.LT, "LT");}
	">"						{return symbol(Symbols.GT, "GT");}
	//operadores logicos
	"and"					{return symbol(Symbols.AND, "AND");}
	"or"					{return symbol(Symbols.OR, "OR");}
	"xor"					{return symbol(Symbols.XOR, "XOR");}
	"implies"				{return symbol(Symbols.IMPLIES, "IMPLIES");}
	//operadores unarios
	"not"					{return symbol(Symbols.NOT, "NOT");}
	"-"						{return symbol(Symbols.MINUS, "MINUS");}

	{oclSuperType}			{return symbol(Symbols.OCL_SUPER_TYPE, yytext(), "OCL_SUPER_TYPE");}
	{oclBasicType}			{return symbol(Symbols.OCL_BASIC_TYPE, yytext(), "OCL_BASIC_TYPE");}
	{oclCollection}			{return symbol(Symbols.OCL_COLLECTION, yytext(), "OCL_COLLECTION");}
	"self"					{return symbol(Symbols.SELF, "SELF");}
	"result"				{return symbol(Symbols.RESULT, "RESULT");}
	{AROBAPRE}				{return symbol(Symbols.AROBAPRE, "AROBAPRE");}
	//literais
	{INT}					{return symbol(Symbols.INT, new Integer(yytext()), "INT");}
	{FLOAT}					{return symbol(Symbols.FLOAT, new Float(yytext()), "FLOAT");}
	{bool}					{return symbol(Symbols.BOOL, new Boolean(yytext()), "BOOL");}
	
	{id}					{return symbol(Symbols.ID, yytext(), "ID");}
	{strDelim} 				{yybegin(STRING); string = new StringBuilder();}
}

<STRING>{
	{ESC} 					{string.append(yytext());}
	[^\\\']					{string.append(yytext());}
	<<EOF>>					{error(yyline, "String incompleta.");}
	{strDelim}				{yybegin(YYINITIAL); return symbol(Symbols.STRING, string.toString(), "STRING");}
}

.|\n						{error(yyline, "o caracter " + yytext() + " nao eh reconhecido pela linguagem.");}
<<EOF>>						{return symbol(Symbols.EOF, "EOF");}
