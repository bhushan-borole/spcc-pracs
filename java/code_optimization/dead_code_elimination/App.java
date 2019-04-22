public class App {
    public static void main(String[] args) {
        String input = "c=a+b\n" +
                "x=a\n" +
                "d=a+c";
        System.out.println("Before Optimization: ");
        System.out.println(input);
        CodeOptimization codeOptimization = new CodeOptimization();
        codeOptimization.optimize(input);
    }
}
/*
c=a+b
x=a
d=a+c
*/
