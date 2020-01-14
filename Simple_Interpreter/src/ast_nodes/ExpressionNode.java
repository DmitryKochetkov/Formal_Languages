package ast_nodes;

import interpreter.Token;

public class ExpressionNode extends Node {

    public static class VarNode extends ExpressionNode {
        public Token id;

        public VarNode(Token id) {
            this.id = id;
        }
    }

    public static class UnOpNode extends ExpressionNode {
        public final Token token;
        public final ExpressionNode expr;

        public UnOpNode(Token token, ExpressionNode expr) {
            this.token = token;
            this.expr = expr;
        }
    }

    public static class BinOpNode extends ExpressionNode {
        public Token op;
        public Node left;
        public Node right;

        public BinOpNode(Token op, Node left, Node right) {
            this.op = op;
            this.left = left;
            this.right = right;
        }
    }

    public static class NumberNode extends ExpressionNode {
        public Token number;

        public NumberNode(Token number) {
            this.number = number;
        }
    }
}


