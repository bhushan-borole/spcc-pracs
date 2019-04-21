public class App {
    public static void main(String[] args) {
        // calling for 3AC, does not work for all inputs
        ThreeAddressCode threeAddressCode = new ThreeAddressCode();
        String input = "a*b-a*b+a*b";
        System.out.println("Expression: " + input);
        threeAddressCode.generate(input);


        // calling function for generic 3AC code (works for brackets as well)
        System.out.println("Expression: " + input);
        ThreeAddressCodeGeneric threeAddressCodeGeneric = new ThreeAddressCodeGeneric();
        threeAddressCodeGeneric.generate3AC(input);
    }
}
