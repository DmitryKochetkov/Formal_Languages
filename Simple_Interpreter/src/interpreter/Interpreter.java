package interpreter;

import ast_nodes.StatementNode;

import java.util.List;
import java.util.Map;

public class Interpreter {
    private final List<StatementNode> statements;
    private final Map<String, Double> variables;

    public Interpreter(List<StatementNode> statements, Map<String, Double> variables) {
        this.statements = statements;
        this.variables = variables;
    }

    public void startProgram() {
        for (StatementNode statement: statements) {
            evalStatement(statement);
        }
    }

    private void evalStatement(StatementNode s) {
        if (s instanceof StatementNode.PrintNode) {
            System.out.println(variables.get(((StatementNode.PrintNode)s).token.text));
        }

    }
}
