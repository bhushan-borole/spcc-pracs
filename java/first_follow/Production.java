import java.util.*;

class Production{
    final String head;
    final List<String> body;

    public Production(String head, String[] body) {
        this.head = head;
        this.body = Arrays.asList(body);
    }
    @Override
    public String toString() {
        return head + " -> " + String.join(" ",body);
    }
}