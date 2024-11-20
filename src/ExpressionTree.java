import java.util.Stack;

class Node{
    String value;
    Node left, right;

    public Node(String value){
        this.value = value;
        left = right = null;
    }
}

public class ExpressionTree{
    Node root;

    public ExpressionTree(){
        root = null;
    }

    // Function to check if the string is an operator
    // 9 4 - 5 3 * + 25 2 - <= 6 3 * 48 2 / < &&
    private boolean isOperator(String c){
         return c.equals("+") ||
                c.equals("-") ||
                c.equals("*") ||
                c.equals("/") ||
                c.equals("<=")||
                c.equals("&&")||
                c.equals("<") ||
                c.equals(">") ||
                c.equals(">=") ||
                c.equals("||");
    }

    // Function to construct the tree from a postfix expression
    public void constructTree(String infix){
        Stack<Node> stack = new Stack<>();

        String postfix = infixToPostFix(infix);

        String[] tokens = postfix.split("\\s+");

        for(String token : tokens){
            if(!isOperator(token)){
                stack.push(new Node(token));
            }else{
                Node operatorNode = new Node(token);

                operatorNode.right = stack.pop();
                operatorNode.left = stack.pop();

                stack.push(operatorNode);
            }
        }
        root = stack.pop();
    }

    // Function to print the tree in-order (for checking)
    public void inOrder(Node root){
        if(root != null){
            inOrder(root.left);
            System.out.print(root.value + " ");
            inOrder(root.right);
        }
    }

    public void preOrder(Node root){
        if(root != null){
            System.out.print(root.value + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    // 9 - 4 + 5 * 3 <= 25 - 2 && 6 * 3 < 48 / 2
    private String infixToPostFix(String infix){
        Stack<String> stack = new Stack<>();
        infix = infix.replaceAll("\\s+","");
        StringBuffer result = new StringBuffer();

       for(int i = 0; i<infix.length(); i++){
          char c = infix.charAt(i);

          if(Character.isDigit(c)){
              StringBuilder number = new StringBuilder();

              while(i < infix.length() && Character.isDigit(infix.charAt(i))){
                  number.append(infix.charAt(i));
                  i++;
              }
              i--;
              result.append(number).append(' ');
          }else{
              String op = String.valueOf(c);

              if (i + 1 < infix.length()) {
                  if (op.equals("<") && infix.charAt(i + 1) == '=') {
                      op = "<=";
                      i++;
                  } else if (op.equals("&") && infix.charAt(i + 1) == '&') {
                      op = "&&";
                      i++;
                  } else if (op.equals("|") && infix.charAt(i + 1) == '|') {
                      op = "||";
                      i++;
                  }
              }
              while(!stack.isEmpty() && (prec(op) <= prec(stack.peek()))){
                  result.append(stack.pop()).append(' ');
              }
              stack.push(op);
          }
       }

       while(!stack.isEmpty()){
           result.append(stack.pop()).append(' ');
       }
       return result.toString().trim();
    }

    private int prec(String op) {
        if (op.equals("*") || op.equals("/") || op.equals("%")) {
            return 5;
        } else if (op.equals("+") || op.equals("-")) {
            return 4;
        } else if (op.equals("<") || op.equals("<=") || op.equals(">") || op.equals(">=")) {
            return 3;
        } else if (op.equals("&&")) {
            return 2;
        } else if (op.equals("||")) {
            return 1;
        } else {
            return -1;
        }
    }
}
