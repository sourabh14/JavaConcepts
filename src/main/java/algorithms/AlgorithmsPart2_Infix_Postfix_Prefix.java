package algorithms;

import java.util.Stack;

public class AlgorithmsPart2_Infix_Postfix_Prefix {
    /*
        Infix, Postfix, Prefix
		-Infix expression: The expression of the form a op b. When an operator is in-between every pair of operands.
		-Postfix expression: The expression of the form a b op.

		Why postfix?
			-The compiler scans the expression either from left to right or from right to left.
			The repeated scanning makes it very in-efficient. It is better to convert the expression to postfix(or prefix) form before evaluation.

		Infix to Postfix
			-Scan the infix expression from left to right.
			-If the scanned character is an operand, output it.
			-Else,
			      1 If the precedence of the scanned operator is greater than the precedence of the operator in the stack (or the stack is empty or the stack contains a ‘(‘ ), push it.
			      2 Else, Pop all the operators from the stack which are greater than or equal to in precedence than that of the scanned operator. After doing that Push the scanned operator to the stack. (If you encounter parenthesis while popping then stop there and push the scanned operator in the stack.)
			-If the scanned character is an ‘(‘, push it to the stack.
			-If the scanned character is an ‘)’, pop the stack and output it until a ‘(‘ is encountered, and discard both the parenthesis.
			-Repeat steps 2-6 until infix expression is scanned.
			-Print the output
			-Pop and output from the stack until it is not empty.
     */

    public String infixToPostfix(String infixString) {
        infixString = "(" + infixString + ")";

        char ch;
        StringBuilder postfix = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<infixString.length(); i++) {
            ch = infixString.charAt(i);
//            System.out.println("ch = " + ch);
            if (isAlphabet(ch)) {
                temp.append(ch);
            } else if (ch == ')') {
                while (stack.peek() != '(') {
                    temp.append(stack.pop());
                }
                stack.pop();
                postfix.append(temp);
                temp.setLength(0);
            } else {
                stack.push(ch);
            }
//            System.out.println("stack = " + stack);
//            System.out.println("temp = " + temp);
//            System.out.println("postfix = " + postfix);
//            System.out.println("");
        }

        return postfix.toString();
    }

    private boolean isAlphabet(char ch) {
        if ((ch >= 'a') && (ch <= 'z')) return true;
        return false;
    }

    public void execute() {
        String ex2 = "a+b+c";
        String ex3 = "(a+b)*(c+d)";
        String ex4 = "a*(b+(c*d))";
        String ex5 = "(((a+b)*c)/d)";

        System.out.println("ex : " + ex2);
        System.out.println("ex = " + infixToPostfix(ex2) + "\n");

        System.out.println("ex : " + ex3);
        System.out.println("ex = " + infixToPostfix(ex3) + "\n");

        System.out.println("ex : " + ex4);
        System.out.println("ex = " + infixToPostfix(ex4) + "\n");

        System.out.println("ex : " + ex5);
        System.out.println("ex = " + infixToPostfix(ex5) + "\n");
    }
}
