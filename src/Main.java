//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String postfixExpression = "9 4 - 5 3 * + 25 2 - <= 6 3 * 48 2 / < &&";
        String infixExpression = "9 - 4 + 5 * 3 <= 25 - 2 && 6 * 3 < 48 / 2";
        ExpressionTree tree = new ExpressionTree();
        tree.constructTree(infixExpression);
        tree.inOrder(tree.root);
        System.out.println();
        tree.preOrder(tree.root);
    }
}