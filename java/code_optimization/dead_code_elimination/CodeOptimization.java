import java.util.ArrayList;
import java.util.List;

class Statement{
    private String result;
    private String arg1;
    private String op;
    private String arg2;

    @Override
    public String toString() {
        return result + " = " + arg1 + " " + op + " " + arg2;
    }

    Statement(String result, String arg1, String op, String arg2) {
        this.arg1 = arg1;
        this.op = op;
        this.arg2 = arg2;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }
}

public class CodeOptimization {
    static List<Statement> statements = new ArrayList<>();

     static void parseInput(String input){
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


    public void optimize(String input) {
        parseInput(input);
        for(int i=0; i<statements.size(); i++){
            boolean found = false;
            String result = statements.get(i).getResult();
            for(int j=i; j<statements.size(); j++){
                if(result.equals(statements.get(j).getArg1()) || result.equals(statements.get(j).getArg2()))
                    found = true;
            }

            if(!found){
                statements.remove(statements.get(i));
            }
        }

        System.out.println("\nAfter Optimizing: ");
        for(Statement s : statements){
            System.out.println(s.toString());
        }
    }
}
