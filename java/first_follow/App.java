import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter start symbol:");
        String startSymbol = sc.nextLine();

        System.out.print("Enter non terminals:");
        List<String> NonTerminals = Arrays.asList( sc.nextLine().split(" ") );

        System.out.print("Enter terminals:");
        List<String> Terminals = Arrays.asList( sc.nextLine().split(" ") );

        System.out.print("Enter no of productions:");
        int pn = Integer.parseInt( sc.nextLine() );

        System.out.println("Enter productions:");
        List<Production> list_productions = new ArrayList<>();

        for(int pi = 0; pi < pn; pi++){
            String[] pp = sc.nextLine().trim().split("=");
            String head = pp[0].trim();
            String [] body = pp[1].trim().split(" ");
            list_productions.add(new Production(head, body));
        }

        Grammar g = new Grammar(
               Terminals, NonTerminals, list_productions, startSymbol);
        System.out.println(String.join("\n", g.getFirstSets()));
        System.out.println(String.join("\n", g.getFollowSets()));
    }
}