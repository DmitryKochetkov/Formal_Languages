package interpreter;

import ast_nodes.StatementNode;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            List<Token> tokens = lexer.tokens;

            tokens.removeIf(t -> t.type == TokenType.SPACE);

            for (Token t: tokens)
                System.out.println(t.toString());

            Parser p = new Parser(tokens);
            List<StatementNode> statements = p.parse();


            System.out.println();
            for (StatementNode s: statements)
                System.out.println(s.toString());
            System.out.println();

            Map<String, Double> variables = new HashMap<>();
            variables.put("a", 1.0);
            Interpreter interpreter = new Interpreter(statements, variables);
            System.out.println("Syntax is correct. Starting program...");
            interpreter.startProgram();
            System.out.println("Program finished with exit code 0.");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
