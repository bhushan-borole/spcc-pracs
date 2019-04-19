%option noyywrap
%{
	#include<stdio.h>
	int keywords = 0;
	int identifiers = 0;
	int operators = 0;
	int constants = 0;
%}

%%
if|else|while|int|switch|for|char {keywords++;}
[A-Za-z][A-Za-z0-9_]* {identifiers++;}
[=+\-*/^] {operators++;}
[0-9] {constants++;}
\n {printf("\nTotal Number Of\n Keywords: %d\nConstants: %d\nIdentifiers: %d\nOperators: %d\n", keywords, constants, identifiers, operators);}
%%

int main()
{
    printf("Enter the string of your choice: \n");
    yylex();
    return 0;
}
