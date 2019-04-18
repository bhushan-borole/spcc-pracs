%option noyywrap
%{
	#include<stdio.h>
	int keywords = 0;
%}

%%
if|else|while|int|switch|for|char {keywords++;}
\n {prinf("Total Number of keywords: %d", keywords);}
%%

int main()
{
	printf("Enter the string of your choice: \n");
    yylex();
    return 0;
}
