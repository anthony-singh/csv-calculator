package com.csv.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.csv.calc.Cell;

public class CsvUtil {

	private CsvUtil() {
	}

	private static MapStore map = MapStore.getInstance();

	public static void populateMapStore(List<String> lines) {
		String key;
		int rowIndex = 0;
		for (String line : lines) {
			key = getCharacter(rowIndex + 1);
			String[] columnsArray = line.split(",");
			for (int idx = 0; idx < columnsArray.length; idx++) {
				map.put(key + idx, createCell(columnsArray[idx], key + idx));
			}
			rowIndex++;
		}
		System.out.println("");
	}

	private static Cell createCell(String expression, String cellName) {
		List<String> expressionAsList = new ArrayList<String>();
		if (expression.contains(cellName)) {
			throw new RuntimeException("Cyclic dependency at cell " + cellName + " for expression " + expression);
		}
		if (expression.startsWith("=")) {
			expressionAsList = convertExpressionToPostFix(expression.substring(1));
		} else {
			expressionAsList.add(expression);
		}
		return new Cell(expressionAsList);
	}

	private static List<String> convertExpressionToPostFix(String expression) {
		List<String> expressionList = convertExpressionToList(expression);
		Stack<String> stack = new Stack<>();
		List<String> postFixExpr = new ArrayList<String>();
		for (String str : expressionList) {
			if (isOperator(str.charAt(0))) {
				if (str.equals("(")) {
					stack.push(str);
				} else if (str.equals(")")) {
					while (!stack.isEmpty() && !stack.peek().equals("("))
						postFixExpr.add(stack.pop());

					if (!stack.isEmpty() && !stack.peek().equals("(")) {
						throw new RuntimeException("Invalid Expression");
					} else
						stack.pop();
				} else {
					while (!stack.isEmpty() && getPrecedence(str.charAt(0)) <= getPrecedence(stack.peek().charAt(0))) {
						if (stack.peek().equals("(")) {
							throw new RuntimeException("Invalid Expression");
						}
						postFixExpr.add(stack.pop());
					}
					stack.push(str);
				}
			} else {
				postFixExpr.add(str);
			}
		}

		while (!stack.isEmpty()) {
			postFixExpr.add(stack.pop());
		}

		return postFixExpr;
	}

	private static int getPrecedence(char c) {
		switch (c) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		default:
			return -1;
		}
	}

	private static List<String> convertExpressionToList(String expression) {
		List<String> expressionList = new ArrayList<String>();
		int lastStartIndex = 0;
		for (int index = 0; index < expression.length(); index++) {
			if (isOperator(expression.charAt(index))) {
				if (lastStartIndex != index) {
					expressionList.add(expression.substring(lastStartIndex, index));
				}
				expressionList.add(expression.substring(index, index + 1));
				lastStartIndex = index + 1;
			}
		}

		if (lastStartIndex < expression.length()) {
			expressionList.add(expression.substring(lastStartIndex));
		}

		return expressionList;
	}

	private static String getCharacter(int i) {
		return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
	}

	private static boolean isOperator(char c) {
		return c == ')' || c == '(' || c == '+' || c == '-' || c == '*' || c == '/';
	}
}
