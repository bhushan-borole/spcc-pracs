public class App{
	public static void main(String[] args) {
		CodeGeneration codeGeneration = new CodeGeneration();
        String input = "a=b\n" +
                "f=c+d\n" +
                "e=a-f\n" +
                "g=b*c";
        System.out.println("Original Code: ");
        System.out.println(input);
        codeGeneration.parseInput(input);
        System.out.println("\nMachine Code: ");
        codeGeneration.generateCode();
    }
}