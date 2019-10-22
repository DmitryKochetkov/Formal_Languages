import java.util.List;

public class Practice3 {
    public static void main(String[] args) {
        String text = "10+20";
        Lexer l = new Lexer(text);
        List<Token> tlist = l.lex();
        for (Token t: tlist) {
            System.out.println(t.type + " " + t.text);
        }
    }
}
