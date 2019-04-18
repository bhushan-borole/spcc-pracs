import java.util.ArrayList;
import java.util.List;

class Statement{
    private String result;
    private String arg1;
    private String op;
    private String arg2;

    @Override
    public String toString() {
        return "Statement{" +
                "result='" + result + '\'' +
                ", arg1='" + arg1 + '\'' +
                ", op='" + op + '\'' +
                ", arg2='" + arg2 + '\'' +
                '}';
    }

    Statement(String result, String arg1, String op, String arg2) {
        this.arg1 = arg1;
        this.op = op;
        this.arg2 = arg2;
        this.result = result;
    }

    String getArg1() {
        return arg1;
    }

    String getOp() {
        return op;
    }

    String getArg2() {
        return arg2;
    }

    String getResult() {
        return result;
    }

}

public class CodeGeneration {
    static List<Statement> statements = new ArrayList<>();

    public static void parseInput(String input){
        String arr[] = input.split("\n");
        for(String s : arr){
            String [] st = s.split("");
            String result = st[0];
            if(st.length == 3){
                String op = st[1];
                String arg1 = st[2];
                String arg2 = "null";
                statements.add(new Statement(result, arg1, op, arg2));
            }
            else{
                String op = st[3];
                String arg1 = st[2];
                String arg2 = st[4];
                statements.add(new Statement(result, arg1, op, arg2));
            }
        }
    }

    public void generateCode(){
        for(Statement st : statements){
            System.out.printf("MOV R%d, %s\n", statements.indexOf(st), st.getArg1());
            switch (st.getOp()){
                case "+":
                    System.out.printf("ADD R%d, %s\n", statements.indexOf(st), st.getArg2());
                    break;

                case "-":
                    System.out.printf("SUB R%d, %s\n", statements.indexOf(st), st.getArg2());
                    break;

                case "/":
                    System.out.printf("DIV R%d, %s\n", statements.indexOf(st), st.getArg2());
                    break;

                case "*":
                    System.out.printf("MUL R%d, %s\n", statements.indexOf(st), st.getArg2());
                    break;
            }
            System.out.printf("MOV %s, R%d\n", st.getResult(), statements.indexOf(st));
        }
    }


}

/*
Original Code:
a=b
f=c+d
e=a-f
g=b*c

Machine Code:
MOV R0, b
MOV a, R0
MOV R1, c
ADD R1, d
MOV f, R1
MOV R2, a
SUB R2, f
MOV e, R2
MOV R3, b
MUL R3, c
MOV g, R3
*/