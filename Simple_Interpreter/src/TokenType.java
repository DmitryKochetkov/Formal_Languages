import java.util.regex.Pattern;

public enum TokenType {
    NUMBER("[0-9]+"),
    ID("[A-Za-z]+"),
    WHILE("while"),
    DO("do"),
    INC("\\+\\+"),
    DEC("--"),
    GREATER(">"),
    LESS("<"),
    EQUAL("="),
    SPACE("[ \t\r\n]+"),
    PRINT("print");
    Pattern pattern;

    TokenType(String regexp) {
        pattern = Pattern.compile(regexp);
    }
}
