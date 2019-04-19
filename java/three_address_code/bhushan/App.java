public class App {
    public static void main(String[] args) {
        ThreeAddressCode threeAddressCode = new ThreeAddressCode();
        String expression = "a/b*c/d";
        System.out.println("Expression: " + expression);
        threeAddressCode.generate(expression);
    }
}
