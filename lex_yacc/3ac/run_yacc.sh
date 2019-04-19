lex $1
if [ $? -ne 0 ]; then
    echo "lex error"
    exit 1
fi
echo "lex done"

yacc -v -d $2
# d for y.tab.h
# v for verbose/debugging, check y.output
if [ $? -ne 0 ]; then
    echo "yacc error"
    exit 1
fi

echo "yacc done"

gcc y.tab.c lex.yy.c
if [ $? -ne 0 ]; then
    echo "gcc error"
    exit 1
fi
echo "compiled!"

echo  "running"
./a.out
