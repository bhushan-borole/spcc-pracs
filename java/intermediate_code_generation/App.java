public class App{
	public static void main(String[] args) {
		IntermediateCodeGeneration intermediateCodeGeneration = new IntermediateCodeGeneration();
        String input = "a=b\n" +
                "f=c+d\n" +
                "e=a-f\n" +
                "g=b*c";
        System.out.println("Original Code: ");
        System.out.println(input);
        intermediateCodeGeneration.parseInput(input);
        System.out.println("\nIntermediate Code Generation: ");
        intermediateCodeGeneration.generateCode();
    }
}