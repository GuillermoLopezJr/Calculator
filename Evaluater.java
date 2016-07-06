import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Evaluater {

	class ComputationException extends Exception {
  		public ComputationException(String message) {
   			super(message);
 		}
 	}

	private ArrayList<String> tokens;
	private Map<String, Integer> precedenceTable;
	private Stack<String> opStack; 
	private final String delimiters = "()+-/*^% ";
	private ArrayList<String> operators;

	public Evaluater() {
		opStack = new Stack<String>();
        String ops[] = { "+", "-", "%", "^", "*", "/" };
		operators = new ArrayList<String>(Arrays.asList(ops));

		tokens = new ArrayList<String>();
		precedenceTable = new HashMap<String, Integer>();
		populatePrecedenceTable();
	}

	public void populatePrecedenceTable() {
		precedenceTable.put("+", 1);
		precedenceTable.put("-", 1);
		precedenceTable.put("*", 2);
		precedenceTable.put("/", 2);
		precedenceTable.put("%", 3);
		precedenceTable.put("^", 3);
		precedenceTable.put("(", 0);
		precedenceTable.put(")", 100);
	}

	public double calculate(String expr) throws Exception {
		String infixExpr = expr;
		addTokens(infixExpr); //add tokens to arrayList
		
        String postfixExpr = getPostFixExpr();
		
		tokens.clear();
        //token list is in post fix notation
		addTokens(postfixExpr);

		double result = evaluate();
		return result;
	}

	public void addTokens(String expr) {
		StringTokenizer st = new StringTokenizer(expr, delimiters, true);

		while (st.hasMoreTokens())  {
			String token = st.nextToken();
           	
            if (!token.equals(" ")) {
            	tokens.add(token);
            }
        }
	}

	public String getPostFixExpr() {
		String postfixExpr = "";

		for (int i = 0; i < tokens.size(); i++) {
			String curToken = tokens.get(i);

			if (isOperand(curToken)) {
				postfixExpr += " " + curToken;
			}
			else if (isOperator(curToken) ) {
				while( !opStack.empty() && !hasHigherPrecedence(curToken, opStack.peek()) && !isLeftParen(opStack.peek()) ) {
					postfixExpr += " " + opStack.pop();
                }

				opStack.push(curToken);
			}
			else if (isLeftParen(curToken)) {
				opStack.push(curToken);
			}
			else if (isRightParen(curToken)) {
				while (!opStack.empty() && !isLeftParen(opStack.peek())) {
					postfixExpr += " " + opStack.pop();
                }
				opStack.pop(); //pop the right paren
			}
		}

        //pop remaining tokens
        while (!opStack.empty()) {
            postfixExpr += " " + opStack.pop();
        }

		return postfixExpr;
	}

	public void clearCalc() {
		tokens.clear();
	}

	public double evaluate() throws Exception {
		Stack<String> stack = new Stack<String>();
		double result = -99999;

		for (int i = 0; i < tokens.size(); i++) {
			String curToken = tokens.get(i);
			if (isOperand(curToken)) {
				stack.push(curToken);
            }
			else if (isOperator(curToken)) {
				try {
					double num2 = Double.parseDouble(stack.pop());
					double num1 = Double.parseDouble(stack.pop());	
					char op = curToken.charAt(0);
					result = getResult(num1, num2, op);
					stack.push(""+result);
				}
				catch (Exception ex) {
					throw new ComputationException("Format Exception");
				}	
			}
		}
		
		if (stack.size() == 1) {
			result = Double.parseDouble(stack.pop());
        }
		else { 
			throw new ComputationException("Invalid Expression");
        }
		return result;
	}

	public double getResult(double num1, double num2, char op) throws ComputationException {
		double result = 0;

		if (op == '+') {
			result = num1 + num2;
        }
		else if (op == '-') {
			result = num1 - num2;
        }
		else if (op == '/') {
			if (num2 == 0) {
    			throw new ComputationException("Divide by Zero");
            }
			else {
				result = num1 / num2;
            }
		}
		else if (op == '*') {
			result = num1 * num2;
        }
		else if (op == '%') {
			result = num1 % num2;
        }
		else if (op == '^') {
			result = Math.pow(num1, num2);
        }
		return result;
	}

	public boolean isLeftParen(String str) {
		return (str.equals("("));
	}

	public boolean isRightParen(String str) {
		return (str.equals(")")); 
	}

	public boolean hasHigherPrecedence(String op1, String op2) {
		return (precedenceTable.get(op1) > precedenceTable.get(op2) );	
	}

	public boolean isOperand(String str) {
		try {
			double num = Double.parseDouble(str);
		}
		catch (NumberFormatException ex){
			return false;
		}
		return true;
	}

	public boolean isOperator(String str) {
		for (int i = 0; i < operators.size(); i++) {
			if (str.equals(operators.get(i))) {
				return true;
            }
		}
		return false;
	}

}
