%option noyywrap
%{
    #include<stdio.h>
    int special_characters = 0;
%}

%%
[@#$%&] {special_characters++;}
\n { printf("\nSpecial Characters = %d", special_characters);}
%%

int main(){
    printf("Enter the string of your choice: \n");
    yylex();
    return 0;
}
