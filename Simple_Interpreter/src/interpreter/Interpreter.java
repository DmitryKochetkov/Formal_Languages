package interpreter;

import ast_nodes.ExpressionNode;
import ast_nodes.StatementNode;

import java.util.List;
import java.util.Map;

import static interpreter.TokenType.DEC;
import static interpreter.TokenType.INC;

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

    private double evalExpression(ExpressionNode e) {
        if(e instanceof ExpressionNode.NumberNode) return Double.valueOf(((ExpressionNode.NumberNode) e).number.text);
        if(e instanceof ExpressionNode.VarNode) return variables.get(((ExpressionNode.VarNode) e).id.text);
        if(e instanceof ExpressionNode.UnOpNode) return -1 * evalExpression(((ExpressionNode.UnOpNode) e).expr);

        return 0;
    }


    private boolean evalCompare(ExpressionNode comp) {
        double valueL = evalExpression((ExpressionNode) ((ExpressionNode.BinOpNode)comp).left);
        double valueR = evalExpression((ExpressionNode) ((ExpressionNode.BinOpNode)comp).right);

        switch (((ExpressionNode.BinOpNode)comp).op.type) {
            case EQUAL:
                return valueL == valueR;
            case GREATER:
                return valueL > valueR;
            case LESS:
                return valueL < valueR;
            default:
                return false;
        }
    }

    private void evalStatement(StatementNode s) {
        if (s instanceof StatementNode.PrintNode) {
            System.out.println(variables.get(((StatementNode.PrintNode)s).token.text));
        }

        if (s instanceof StatementNode.doWhileNode) {
            while(evalCompare(((StatementNode.doWhileNode) s).condition)) {
                for ( StatementNode st: ((StatementNode.doWhileNode) s).body) {
                    evalStatement(st);
                }
            }
        }

        if (s instanceof StatementNode.VariableNode) {
            switch (((ExpressionNode.UnOpNode)(((StatementNode.VariableNode) s).expression)).token.type) {
                case INC:
                    variables.put(
                            ((StatementNode.VariableNode) s).token.text,
                            (variables.get(((StatementNode.VariableNode) s).token.text) + 1.0)
                    );
                    break;
                case DEC:
                    variables.put(
                            ((StatementNode.VariableNode) s).token.text,
                            (variables.get(((StatementNode.VariableNode) s).token.text) - 1.0)
                    );
                    break;
                default:
                    variables.put(
                            ((StatementNode.VariableNode) s).token.text,
                            evalExpression(((StatementNode.VariableNode) s).expression)
                    );
            }
        }

    }
}
