import java.util.List;

import static com.twoPass.TwoPassAssembler.*;

public class Main {
    public static void main(String[] args) {
        String input_string = "START\n" +
                "\n" +
                "MACRO\n" +
                "ADD &ARG1,&ARG2\n" +
                "L 1,&ARG1\n" +
                "A 1,&ARG2\n" +
                "MEND\n" +
                "\n" +
                "MACRO\n" +
                "SUB &ARG3,&ARG4\n" +
                "L 1,&ARG3\n" +
                "S 1,&ARG4\n" +
                "MEND\n" +
                "\n" +
                "ADD DATA1,DATA2\n" +
                "SUB DATA1,DATA2\n" +
                "\n" +
                "DATA1 DC F'9'\n" +
                "DATA2 DC F'5'\n" +
                "\n" +
                "END";

        List<List<String []>> input = parseInput(input_string);
        generatePasses(input);
        populateALA(passes);
        performPass1();
        performPass2();
    }
}

/*
START

MACRO
ADD &ARG1, &ARG2
L 1, &ARG1
A 1, &ARG2
MEND

MACRO
SUB &ARG3, &ARG4
L 1, &ARG3
S 1, &ARG4
MEND

ADD DATA1, DATA2
SUB DATA1, DATA2

DATA1 DC F'9'
DATA2 DC F'5'

END
*/
