public class App {
    public static void main(String[] args) {
        Tree tree = new Tree("a*b+c/z*d");
        tree.print3AC(tree.root);
    }
}