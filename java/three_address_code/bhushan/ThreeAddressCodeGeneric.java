import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ThreeAddressCodeGeneric {
    static String result = "";
    // arranged in increasing order of precedence
    static List<String> operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "^"));

    static int getPrecedence(char c){
        switch (c)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }
    static String infixToPostfix(String expression){
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<expression.length(); i++){
            char c = expression.charAt(i);

            // if the character is a letter or a digit
            if(Character.isLetterOrDigit(c))
                result += c;

            // if the character is '('
            else if(c == '(')
                stack.push(c);

            // if the character is ')'
            else if(c == ')'){
                // until the stack is empty or we get '('
                while( !stack.isEmpty() && stack.peek() != '(')
                    result += stack.pop();

                stack.pop(); // popping the '('
            }
            // the character is operator
            else{
                while(!stack.isEmpty() && getPrecedence(c) < getPrecedence(stack.peek()))
                    result += stack.pop();
                stack.push(c);
            }
        }

        // pop all the remaining operators from the stack
        while (!stack.isEmpty())
            result += stack.pop();

        return result;
    }

    static List<String> getOperators(List<String> exp){
        // returns the list of operators in order in which
        // they arrive in the list of postfix string.
        // eg: [a, b, *, c, +]
        // for this list it will return [*, +]
        List<String> op = new ArrayList<>();
        for(String s: exp){
            if(operators.contains(s))
                op.add(s);
        }
        return op;
    }

    public void generate3AC(String expression){
        String postfix = infixToPostfix(expression);
        String [] expr = postfix.split("");
        List<String> exp = new ArrayList<>(Arrays.asList(expr));
        List<String> operatorsInPostfix = getOperators(exp);

        int i = 0;
        for(String operator : operatorsInPostfix){
            if(exp.contains(operator)){
                for(int j=0; j<exp.size(); j++){
                    if(exp.get(j).equals(operator)){
                        // for every operator encountered the arg2 is at the i-2 index
                        // and arg1 is at the i-1 index
                        String r = exp.get(j-2);

                        // we print the statement t = arg1 + op + arg2
                        System.out.printf("t%d = %s %s %s\n", i, exp.get(j-1),
                                operator, exp.get(j-2));

                        // we set the arg1 to the result t
                        exp.set(j-1, "t"+i);

                        // remove the operator
                        exp.remove(operator);

                        // remove the arg2
                        exp.remove(r);
                        i++;

                        /*
                        eg: [a, b, *, c, +]
                        for the above list first we will print t0 = a * b
                        then we will set a = t0....hence [t0, b, *, c, +]
                        now we will remove the operator... hence [t0, b, c, +]
                        now we remove the arg1... hence [t0, c, +]
                        now in the next iteration we evaluate the updated list.
                        */
                    }
                }
            }
        }
    }
}
