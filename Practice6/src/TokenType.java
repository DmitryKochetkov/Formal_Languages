import java.util.regex.Pattern;

public enum TokenType {
    NUMBER("[0-9]+"),
    ID("[A-Za-z]+"),
    ADD("\\+"), //экранирование
    SUB("-"),
    MUL("\\*"),
    DIV("/"),
    LPAR("\\("), //parenthesis
    PPAR("\\)"),
    SPACE("[ \t\r\n]+");
    Pattern pattern;

    TokenType(String regexp) {
        pattern = Pattern.compile(regexp);
    }
}
