package interpreter;

import ast_nodes.ExpressionNode;
import ast_nodes.StatementNode;

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
            error("Unexpected token " + tokens.get(pos).type + ". Expected " + Arrays.toString(types));
        return t;
    }

    List<StatementNode> parse() {
        List<StatementNode> res = new ArrayList<>();
        while (pos < tokens.size())
            res.add(parseStatement());
        return res;
    }

    private ExpressionNode parseElem() {
        Token num = match(TokenType.NUMBER);
        if (num != null)
            return new ExpressionNode.NumberNode(num);

        Token id = match(TokenType.ID);
        if (id != null)
            return new ExpressionNode.VarNode(id);

        error("Expected a number or a variable.");
        return null;
    }

    public ExpressionNode parseCompare() {
        ExpressionNode e1 = parseElem();
        Token op;
        while ((op = match(TokenType.GREATER, TokenType.LESS, TokenType.EQUAL)) != null) {
            ExpressionNode e2 = parseElem();
            e1 = new ExpressionNode.BinOpNode(op, e1, e2);
        }
        return e1;
    }

    StatementNode parseStatement() {
        Token op = require(TokenType.PRINT, TokenType.ID, TokenType.DO);
        switch (op.type) {
            case PRINT:
                Token id = require(TokenType.ID);
                require(TokenType.SEMICOLON);
                return new StatementNode.PrintNode(id);

            case ID:
                Token token = require(TokenType.DEC, TokenType.INC);
                ExpressionNode e = new ExpressionNode.UnOpNode(token, new ExpressionNode.VarNode(op));
                require(TokenType.SEMICOLON);
                return new StatementNode.VariableNode(op, e);

            case DO:
                List<StatementNode> body = new ArrayList<>();
                while (match(TokenType.WHILE) == null) {
                    if (pos < tokens.size()) {
                        StatementNode statement = parseStatement();
                        body.add(statement);
                    }
                    else error("Missing WHILE operator.");
                }
                ExpressionNode expr = parseCompare();
                require(TokenType.SEMICOLON);
                return new StatementNode.doWhileNode(op, expr, body);
        }
        return null;
    }
}
