package com.csv.utils;

import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;

public class ExpressionCalculator {
	
	private ExpressionCalculator() {}
	
	public static Double calculate(List<String> expression) {
		Stack<Double> stack = new Stack<>();
		expression.stream().forEach(number -> {
			switch (number) {
				case "+":
					evaluate(stack, (n1, n2) -> n2 + n1);
					break;
				case "-":
					evaluate(stack, (n1, n2) -> n2 - n1);
					break;
				case "*":
					evaluate(stack, (n1, n2) -> n2 * n1);
					break;
				case "/":
					evaluate(stack, (n1, n2) -> n2 / n1);
					break;
				default:
					stack.push(Double.parseDouble(number));
			}
		});
		return stack.pop();
	}

	protected static Stack<Double> evaluate(Stack<Double> stack, BiFunction<Double, Double, Double> operation) {
		stack.push(operation.apply(stack.pop(), stack.pop()));
		return stack;
	}

}
