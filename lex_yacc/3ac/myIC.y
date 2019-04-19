%{
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
int yylex();
int yyerror();
int tempCounter=0;

char* createTemp(){
    char *ptr = (char*)calloc(3, sizeof(char));
    sprintf(ptr, "t%d", tempCounter++);
    return ptr;
}

%}
%union
{
    char str[30];
}
// terminals
%token <str> VARIABLE NUMBER
// non terminals
%type <str> startSymbol Exp
// Increasing order of precedence
%left '+' '-' '*' '/'
// specifiy start symbol
%start startSymbol

%%
startSymbol : VARIABLE '=' Exp {strcpy($$, $3); printf("%s=%s\n", $1, $3);}
    ;

// $$ refers to value on LHS
// $1,$2 is started from RHS
Exp : '(' Exp ')' {strcpy($$, $2);}
    | Exp '+' Exp {strcpy($$, createTemp()); printf("%s=%s+%s\n", $$, $1, $3);}
    | Exp '-' Exp {strcpy($$, createTemp()); printf("%s=%s-%s\n", $$, $1, $3);}
    | Exp '*' Exp {strcpy($$, createTemp()); printf("%s=%s*%s\n", $$, $1, $3);}
    | Exp '/' Exp {strcpy($$, createTemp()); printf("%s=%s/%s\n", $$, $1, $3);}
    | VARIABLE //{strcpy($$, $1);} // RHS is automatically copied to LHS by default
    | NUMBER   //{strcpy($$, $1);}
    ;
%%
int main(){
    printf("Enter expression:\n");
    yyparse();
    return 0;
}
int yyerror(){
    printf("ERROR\n");
    return 1;
}
