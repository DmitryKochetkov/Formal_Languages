package ast_nodes;

import interpreter.Token;

import java.util.List;

abstract public class StatementNode extends Node {

    public static class PrintNode extends StatementNode {
        public final Token token;

        public PrintNode(Token token) {
            this.token = token;
        }
    }

    public static class VariableNode extends StatementNode {
        public final Token token;
        public final ExpressionNode expression;

        public VariableNode(Token token, ExpressionNode expression) {
            this.token = token;
            this.expression = expression;
        }
    }

    public static class IncDecNode extends StatementNode {
        public final Token token;
        public final ExpressionNode expression;

        public IncDecNode(Token token, ExpressionNode expression) {
            this.token = token;
            this.expression = expression;
        }
    }

    public static class doWhileNode extends StatementNode {
        public final Token token;
        public final ExpressionNode cobdition;
        public final List<StatementNode> body;

        public doWhileNode(Token token, ExpressionNode cobdition, List<StatementNode> body) {
            this.token = token;
            this.cobdition = cobdition;
            this.body = body;
        }
    }
}






