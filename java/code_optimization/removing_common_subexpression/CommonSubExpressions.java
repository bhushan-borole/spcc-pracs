import java.util.ArrayList;
import java.util.List;

class Statement{
    private String lhs;
    private String rhs;

    public Statement(String lhs, String rhs){
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public String getLhs() {
        return lhs;
    }

    public void setLhs(String lhs) {
        this.lhs = lhs;
    }

    public String getRhs() {
        return rhs;
    }

    public void setRhs(String rhs) {
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return lhs + " = " + rhs;
    }
}

public class CommonSubExpressions {
    static List<Statement> statements = new ArrayList<>();

    public void remove(String input) {
        System.out.println("\nAfter removing common sub expressions: ");
        String [] expressions = input.split("\n");

        for(String s : expressions){
            statements.add(new Statement(s.split("=")[0], s.split("=")[1]));
        }
        for(int i=0; i<statements.size(); i++){
            String rhs = statements.get(i).getRhs();
            String lhs = statements.get(i).getLhs();
            for(int j=i+1; j<statements.size(); j++){
                if(statements.get(j).getRhs().contains(rhs)){
                    statements.get(j).setRhs(statements.get(j).getRhs().replace(rhs, lhs));
                }
            }
        }

        for(Statement s : statements){
            System.out.println(s);
        }
    }
}
