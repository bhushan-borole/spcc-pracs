import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Symbol{
    private String name;
    private int location;
    private String value;

    public Symbol(String name, int location, String value){
        this.name = name;
        this.location = location;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getLocation() {
        return location;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", value=" + value +
                '}';
    }
}

class MachineOpcode{
    private int location;
    private String opcode;
    private String symbol;

    public MachineOpcode(String opcode, String symbol, int location){
        this.opcode = opcode;
        this.symbol = symbol;
        this.location = location;
    }

    public int getLocation() {
        return location;
    }

    public String getOpcode() {
        return opcode;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "MachineOpcode{" +
                "location=" + location +
                ", opcode='" + opcode + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}

public class TwoPassAssembler {
    static List<String []> INPUT = new ArrayList<>();
    static List<String> pseudoOpcodes = new ArrayList<>(Arrays.asList("USING", "DC", "DS", "START"));
    static List<String> machineOpcodes = new ArrayList<>(Arrays.asList("L", "A", "ST"));
    static List<MachineOpcode> MOT = new ArrayList<>();
    static int startIndex;
    static int counter;
    static List<Symbol> symbolTable = new ArrayList<>();
    static int base;

    static void parseInput(String input){
        String [] split = input.split("\n");
        for(String s : split){
            INPUT.add(s.split("\\s|,"));
        }
    }

    static void getSpecifics(){
        if(INPUT.get(0).length > 2){
            startIndex = Integer.valueOf(INPUT.get(0)[2]);
        }
        else{
            startIndex = Integer.valueOf(INPUT.get(0)[1]);
        }
        counter = startIndex;
        for(int i=1; i<INPUT.size(); i++){
            if(pseudoOpcodes.contains(INPUT.get(i)[0])){
                base = Integer.valueOf(INPUT.get(i)[2]);
            }
            else if(machineOpcodes.contains(INPUT.get(i)[0])){
                MOT.add(new MachineOpcode(INPUT.get(i)[0], INPUT.get(i)[2], counter));
                counter += 4;
            }
            else{
                if(INPUT.get(i)[0].equals("END"))
                    continue;
                else {
                    String value = INPUT.get(i)[2];
                    if(value.startsWith("F"))
                        symbolTable.add(new Symbol(INPUT.get(i)[0], counter,
                                value.substring(2, 3)));
                    else
                        symbolTable.add(new Symbol(INPUT.get(i)[0], counter, "-"));
                }
                counter += 4;
            }
        }

//        System.out.println("Symbol Table: ");
//        for(Symbol s : symbolTable){
//            System.out.println(s);
//        }
//
//        System.out.println("\nMOT: ");
//        for(MachineOpcode m : MOT){
//            System.out.println(m);
//        }

    }

    static void performPass1(){
        System.out.println("--------- Pass 1 ----------");
        String Base = "(0, " + base + ")";
        for(MachineOpcode m : MOT){
            System.out.println(m.getLocation() + " " + m.getOpcode() + " 1, _" + Base);
        }
        for(Symbol s : symbolTable){
            System.out.println(s.getLocation() + " " + s.getValue());
        }
    }

    static int getLocationOfSymbol(String symbol){
        for(Symbol s : symbolTable){
            if(symbol.equals(s.getName())){
                return s.getLocation();
            }
        }
        return 0;
    }

    static void performPass2(){
        System.out.println("\n--------- Pass 2 ----------");
        String Base = "(0, " + base + ")";
        for(MachineOpcode m : MOT){
            System.out.println(m.getLocation() + " " + m.getOpcode() + " 1, " +
                    getLocationOfSymbol(m.getSymbol()) + Base);
        }
        for(Symbol s : symbolTable){
            System.out.println(s.getLocation() + " " + s.getValue());
        }
    }

    public static void main(String[] args) {
        String input = "JOHN START 0\n" +
                        "USING *,15\n" +
                        "L 1,FIVE\n" +
                        "A 1,FOUR\n" +
                        "ST 1,TEMP\n" +
                        "FOUR DC F'4'\n" +
                        "FIVE DC F'5'\n" +
                        "TEMP DS 1F\n" +
                        "END";
        System.out.println("Assembly Code: ");
        System.out.println(input + "\n");
        parseInput(input);
        getSpecifics();
        performPass1();
        performPass2();
    }
}

/*
Assembly Code:
JOHN START 0
USING *,15
L 1,FIVE
A 1,FOUR
ST 1,TEMP
FOUR DC F'4'
FIVE DC F'5'
TEMP DS 1F
END

--------- Pass 1 ----------
0 L 1, _(0, 15)
4 A 1, _(0, 15)
8 ST 1, _(0, 15)
12 4
16 5
20 -

--------- Pass 2 ----------
0 L 1, 16(0, 15)
4 A 1, 12(0, 15)
8 ST 1, 20(0, 15)
12 4
16 5
20 -
*/

