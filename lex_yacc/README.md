# Lex Codes 

- Steps to run:
    1. Install bison and flex  
        - ```sudo apt install flex```  
        - ```sudo apt install bison```    
    2. Run Lex Code:  

        - ```lex file_name.l```  
        - ```cc lex.yy.cc -ll```
        - ```./a.out```  
 
    3. Run Lex and Yacc:  
        - ```lex file_name.l```
        -  ```yacc file_name.y```
        - ```cc lex.yy.c y.tab.h -ll```
        - ```./a.out```
