%{
#include<stdio.h>
int words = 0;
int space = 0;
int characters = 0;
int lines = 0;
%}

%%
[ ]+ {space++; words++;}
[^\t\n] {characters++;}
[\n] {lines++; words++;printf("Space = %d; Word = %d, Characters = %d; Lines = %d", space, words, characters, lines);}
%%

int yywrap(){
	return 1;
}

int main(){
	printf("Enter String: ");
	yylex();
	return 0;
}
