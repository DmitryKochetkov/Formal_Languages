import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try (FileReader reader = new FileReader("E:\\Github Repositories\\Formal_Languages\\Simple_Interpreter\\src\\program.txt"))
        {
            int c = 0;
            String program = "";
            while ((c = reader.read()) != -1)
                program += (char)c;
            System.out.println(program + '\n');

            Lexer lexer = new Lexer(program);
            lexer.lex();
            for (Token t: lexer.tokens)
                System.out.println(t.toString());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
