package com.csv.calc;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.csv.utils.ExpressionCalculator;
import com.csv.utils.MapStore;

public class Cell {

	private List<String> expression;
	private Double result;
	private static MapStore map = MapStore.getInstance();

	public Cell(List<String> expression) {
		this.expression = expression;
		this.result = null;
	}

	public Double getResult() {
		return (result != null) ? result : ExpressionCalculator.calculate(prepareExpression());
	}

	private List<String> prepareExpression() {
		return expression
				.stream()
				.map(item -> {
					if (map.containsKey(item)) {
						item = String.valueOf(map.get(item).getResult());
					}
					return item;
				})
				.collect(Collectors.toList());
	}
}
