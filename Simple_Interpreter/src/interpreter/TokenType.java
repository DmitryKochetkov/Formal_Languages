package interpreter;

import java.util.regex.Pattern;

public enum TokenType {
    NUMBER("[0-9]+"),
    ID("[A-Za-z]+"),
    INC("\\+\\+"),
    DEC("--"),
    GREATER(">"),
    LESS("<"),
    EQUAL("="),
    SPACE("[ \t\r\n]+"),
    PRINT("print"),
    SEMICOLON(";"),
    DO("do"),
    WHILE("while");
    Pattern pattern;

    TokenType(String regexp) {
        pattern = Pattern.compile(regexp);
    }
}
