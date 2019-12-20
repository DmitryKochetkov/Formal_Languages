public abstract class Node {

    abstract class ExpressionNode {
        class BinOpNode extends Node { //Binary Operation Node
            Token op;
            Node left;
            Node right;

            public BinOpNode(Token op, Node left, Node right) {
                this.op = op;
                this.left = left;
                this.right = right;
            }
        }

        class UnOpNode extends Node {
            Token op;
            Node child;

            public UnOpNode(Token op, Node child) {
                this.op = op;
                this.child = child;
            }
        }

        class NumberNode extends Node {
            Token number;

            public NumberNode(Token number) {
                this.number = number;
            }
        }

        class VarNode extends Node {
            Token id;

            public VarNode(Token id) {
                this.id = id;
            }
        }
    }

    class doNode extends Node {


        public doNode(Token op) {
            //if op doesn't contains while return error
        }
    }

    abstract class StatementNode {

    }
}
