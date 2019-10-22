import java.util.List;

public class Practice5_6 {
    public static void main(String[] args) {
        String text = "10+20";
        Parser parser = new Parser(text);
        List<Token> tlist = parser.parse();
        for (Token t: tlist) {
            System.out.println(t.type + " " + t.text);
        }
        System.out.println("Evaluation: " + parser.eval(tlist));
    }
}