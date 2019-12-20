import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (FileReader reader = new FileReader("E:\\Github Repositories\\Formal_Languages\\Simple_Interpreter\\src\\test.txt"))
        {
            int c = 0;
            String program = "";
            while ((c = reader.read()) != -1)
                program += (char)c;
            System.out.println(program + '\n');

            Lexer lexer = new Lexer(program);
            List<Token> tokens = lexer.tokens;

            tokens.removeIf(t -> t.type == TokenType.SPACE);

            for (Token t: tokens)
                System.out.println(t.toString());

            Parser p = new Parser(tokens);
            List<Node.StatementNode> statements = p.parse();

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
