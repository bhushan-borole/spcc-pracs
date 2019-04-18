import java.util.ArrayList;
import java.util.List;

class Quadruples{
    private int location;
    private String op;
    private String arg1;
    private String arg2;
    private String result;

    public Quadruples(int location, String op, String arg1, String arg2, String result) {
        this.location = location;
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }

    public int getLocation() {
        return location;
    }

    public String getOp() {
        return op;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public String getResult() {
        return result;
    }
}

class Triples{
    private int location;
    private String op;
    private String arg1;
    private String arg2;

    public Triples(int location, String op, String arg1, String arg2) {
        this.location = location;
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public int getLocation() {
        return location;
    }

    public String getOp() {
        return op;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }
}

class IndirectTriple{
    private int location;
    private String op;
    private String arg1;
    private String arg2;
    private int pointer;
    private int loc;

    public IndirectTriple(int location, String op,
                          String arg1, String arg2,
                          int pointer, int loc) {
        this.location = location;
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.pointer = pointer;
        this.loc = loc;
    }

    public int getLocation() {
        return location;
    }

    public String getOp() {
        return op;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public int getPointer() {
        return pointer;
    }

    public int getLoc() {
        return loc;
    }
}

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

public class IntermediateCodeGeneration {
    static List<Quadruples> quadruples = new ArrayList<>();
    static List<Triples> triples = new ArrayList<>();
    static List<IndirectTriple> indirectTriples = new ArrayList<>();
    static List<Statement> statements = new ArrayList<>();

    public void parseInput(String input){
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

    static String getLocationOfResult(String result){
        // If the result is already present in the table then it will returns its location
        // else it will return the result.
        for(Quadruples q : quadruples){
            if(q.getResult().equals(result)) {
                return String.valueOf(q.getLocation());
            }
        }
        return result;
    }

    public void generateCode(){
        int location = 100;
        int pointer = 35;
        for(Statement s: statements){
            quadruples.add(new Quadruples(location, s.getOp(),
                            s.getArg1(), s.getArg2(), s.getResult()));
            triples.add(new Triples(location, s.getOp(),
                        getLocationOfResult(s.getArg1()), getLocationOfResult(s.getArg2())));
            indirectTriples.add(new IndirectTriple(location, s.getOp(),
                                getLocationOfResult(s.getArg1()), getLocationOfResult(s.getArg2()),
                                pointer, location));
            location++;
            pointer++;

        }
        // Quadruples
        System.out.println("Quadruples: ");
        System.out.println("Location  |  Op  |  Arg1  |  Arg2  |  Result");
        System.out.println("--------------------------------------------");
        for(Quadruples q : quadruples){
            System.out.printf("%8d  |  %2s  |  %4s  |  %4s  |  %5s",
                    q.getLocation(), q.getOp(), q.getArg1(), q.getArg2(), q.getResult());
            System.out.println();
        }

        //Triples
        System.out.println("\nTriples: ");
        System.out.println("Location  |  Op  |  Arg1  |  Arg2");
        System.out.println("---------------------------------");
        for(Triples t : triples){
            System.out.printf("%8d  |  %2s  |  %4s  |  %4s",
                    t.getLocation(), t.getOp(), t.getArg1(), t.getArg2());
            System.out.println();
        }

        //Indirect Triples
        System.out.println("\nIndirect Triples: ");
        System.out.println("Location  |  Op  |  Arg1  |  Arg2  |  Pointer  |  Loc");
        System.out.println("-----------------------------------------------------");
        for(IndirectTriple i : indirectTriples){
            System.out.printf("%8d  |  %2s  |  %4s  |  %4s  |  %7d  |  %3d",
                    i.getLocation(), i.getOp(), i.getArg1(),
                    i.getArg2(), i.getPointer(), i.getLoc());
            System.out.println();
        }
    }
}

/*
Original Code:
a=b
f=c+d
e=a-f
g=b*c

Intermediate Code Generation:
Quadruples:
Location  |  Op  |  Arg1  |  Arg2  |  Result
--------------------------------------------
     100  |   =  |     b  |  null  |      a
     101  |   +  |     c  |     d  |      f
     102  |   -  |     a  |     f  |      e
     103  |   *  |     b  |     c  |      g

Triples:
Location  |  Op  |  Arg1  |  Arg2
---------------------------------
     100  |   =  |     b  |  null
     101  |   +  |     c  |     d
     102  |   -  |   100  |   101
     103  |   *  |     b  |     c

Indirect Triples:
Location  |  Op  |  Arg1  |  Arg2  |  Pointer  |  Loc
-----------------------------------------------------
     100  |   =  |     b  |  null  |       35  |  100
     101  |   +  |     c  |     d  |       36  |  101
     102  |   -  |   100  |   101  |       37  |  102
     103  |   *  |     b  |     c  |       38  |  103
*/