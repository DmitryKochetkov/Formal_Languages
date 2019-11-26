import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;

public class Lexer {
    String src;
    int pos = 0; //текущая позиция в строке
    List<Token> tokens = new ArrayList<>();

    static HashMap<String, TokenType> keywords = new HashMap<>();

    static {
        keywords.put("do", TokenType.DO);
        keywords.put("while", TokenType.WHILE);
        keywords.put("print", TokenType.PRINT);
    }

    public Lexer(String src) {
        this.src = src;
    }

    boolean nextToken() {
        if (pos >= src.length())
            return false;
        for (TokenType tt : TokenType.values()) {
            Matcher m = tt.pattern.matcher(src);
            m.region(pos, src.length()); //ищем только от pos до конца, заточено под lookingAt
            if (m.lookingAt()) {
                if (tt == TokenType.ID && keywords.containsKey(m.group())) {
                    Token t = new Token(keywords.get(m.group()), m.group(), pos);
                    tokens.add(t);
                } else {
                    Token t = new Token(tt, m.group(), pos);
                    tokens.add(t);
                }
                pos = m.end();
                return true;
            }

        }
        throw new RuntimeException("Unknown symbol"); //должно включать также сам символ и его позиция
    }

    List<Token> lex() {
        while (nextToken()) {

        }
        return tokens;
    }
}