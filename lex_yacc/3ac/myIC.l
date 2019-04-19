%{
    #include "y.tab.h"

%}
%%
[0-9]+    {strcpy(yylval.str, yytext); return NUMBER;}
[a-zA-Z]+ {strcpy(yylval.str, yytext); return VARIABLE;}
[\r\n]    {
    return 0; // returning 0 or negative integer sends endmarker to yacc
}
. {
    // to return symbols like =,+,-,*,/
    return yytext[0];
}
%%
int yywrap(){
    // function specifies what to do after input ends
    return 1;
}
