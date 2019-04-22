public class App {
    public static void main(String[] args) {
        String input = "a=b+c\n" +
                "e=(b+c)*d-f/(b+c)";

        System.out.println("\nBefore removing common sub expressions: ");
        System.out.println(input);
        CommonSubExpressions commonSubExpressions = new CommonSubExpressions();
        commonSubExpressions.remove(input);
    }
}
/*
c=a+b
x=a
d=a+c
*/
