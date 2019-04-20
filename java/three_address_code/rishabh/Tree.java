import java.util.concurrent.atomic.AtomicInteger;

class Node{
    String data=null;
    String register;
    Node left=null, right=null;
    Node(){}
}

public class Tree {
    Node root;
    private AtomicInteger register = new AtomicInteger(0);
    Tree(String expression){
        root = new Node();
        buildString(root, expression);
    }
    int buildString(Node root, String expr){
        String left="", right="", operator="";
        if(expr.contains("+") & expr.contains("-")){
            operator = expr.indexOf("+") > expr.indexOf("-") ? "\\-":"\\+";
        } else if(expr.contains("+")){
            operator = "\\+";
        } else if(expr.contains("-")){
            operator = "\\-";
        } else if(expr.contains("/") & expr.contains("*")){
            operator = expr.indexOf("/") > expr.indexOf("*") ? "\\*":"/";
        } else if(expr.contains("/")){
            operator = "/";
        } else if(expr.contains("*")){
            operator = "\\*";
        } else if(expr.contains("^")){
            operator = "\\^";
        }
        else{
            root.data = expr;
            return 0;
        }
        root.data = operator.replaceAll("\\\\", "");
        root.register = "t"+this.register.getAndIncrement();

        String[] parts = expr.split(operator, 2);
        left = parts[0];  right = parts[1];
        root.left = new Node();  root.right = new Node();
        buildString(root.left, left); buildString(root.right, right);
        return 0;
    }
    void print3AC(Node root){
        if(root.left != null) print3AC(root.left);
        if(root.right != null) print3AC(root.right);
        if(root.right != null & root.left != null){
            String left = root.left.left == null ? root.left.data : root.left.register;
            String right = root.right.left == null ? root.right.data : root.right.register;
            System.out.println(String.format("%s = %s %s %s", root.register, left, root.data, right));
        }
    }
}
