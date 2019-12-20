import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    List<Token> tokens;
    int pos = 0;

    public Parser(List<Token> l) {
        this.tokens = l;
    }

    void error(String message) {
        if (pos < tokens.size()) {
            Token t = tokens.get(pos);
            throw new RuntimeException("Error at " + pos + ": " + message);
        }
        else
            throw new RuntimeException("Error " + " at end: " + message);
    }

    Token match(TokenType... expected) {
        if (pos < tokens.size()) {
            Token t = tokens.get(pos);
            if (Arrays.asList(expected).contains(t.type)) { pos++; return t;}
        }
        return null;
    }

    Token require(TokenType ... types) {
        Token t = match(types);
        if (t == null)
            error("Unexpected token. Expected " + Arrays.toString(types));
        return t;
    }

    List<Node.StatementNode> parse() {
        List<Node.StatementNode> res = new ArrayList<>();
        //scan for statements
        return res;
    }
}
