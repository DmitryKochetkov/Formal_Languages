import java.util.ArrayList;
import java.util.List;

public class Parser { //грамматический анализатор
    List<Token> tokens;
    int pos = 0;

    public Parser(String text) {
        Lexer lexer = new Lexer(text);
        this.tokens = lexer.lex();
    }

    void error(String message) {
        if (pos < tokens.size()) {
            Token t = tokens.get(pos);
            throw new RuntimeException(message + " at " + pos);
        }
        else
            throw new RuntimeException(message + " at end");
    }

    Token match(TokenType expected) {
        if (pos < tokens.size()) {
            Token t = tokens.get(pos);
            if (t.type == expected) { pos++; return t;}
        }
        return null;
    }

    void run2() { //run2 и require используют метод рекурсивного спуска, а не конечные автоматы
        require(TokenType.NUMBER);
        while (pos < tokens.size()) { //while is equivalent to Kleene star
            require(TokenType.ADD); //S_FINAL to S_LOOP
            require(TokenType.NUMBER); //S_LOOP to S_FINAL
        }
    }

    List<Token> parse() {
        List<Token> result = new ArrayList<>();
        result.add(require(TokenType.NUMBER));
        while (pos < tokens.size()) { //while is equivalent to Kleene star
            result.add(require(TokenType.ADD)); //S_FINAL to S_LOOP
            result.add(require(TokenType.NUMBER)); //S_LOOP to S_FINAL
        }
        return result;
    }

    int eval(List<Token> l) {
        int result = 0;
        for (Token t: l)
        {
            if (t.type == TokenType.NUMBER) {
                result += Integer.parseInt(t.text);
            }
        }
        return result;
    }

    Token require(TokenType type) {
        Token t = match(type);
        if (t == null)
            error("Token " + type + " expected!");
        return t;
    }
}