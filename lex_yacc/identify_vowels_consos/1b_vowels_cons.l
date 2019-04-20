%{
#include<stdio.h>
char vowels[40];
char consos[40];
int n_vowels = 0;
int n_consos = 0;
// yytext will contain the matched characters
%}
%%
[\r\n] {
    vowels[n_vowels] = '\0';
    consos[n_consos] = '\0';

    printf("Vowels: %s\n", vowels);
    printf("Consos: %s\n", consos);
    
    // Reset the arrays
    n_vowels = n_consos = 0;
}

\\s+ {/*ignore whitespace*/}

[aeiouAEIOU] {
    vowels[n_vowels++] = yytext[0];
}
[^aeiouAEIOU] {
    consos[n_consos++] = yytext[0];
}

%%

int main(){
    printf("1b. Show vowels and consonants\n");
    yylex();
    return 0;
}
