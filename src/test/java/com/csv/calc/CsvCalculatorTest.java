package com.csv.calc;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CsvCalculatorTest {
	
	private static List<String> input = new ArrayList<>();
	private static List<Double> expectedOutput = new ArrayList<>();
	
	@BeforeAll
	private static void init() {
		input.add("2,4,1,=A0+A1*A2");
		input.add("=A3*(A0+1),=B2,0,=A0+1");
		expectedOutput.addAll(List.of(2.00000,4.00000,1.00000,6.00000,18.00000,0.00000,0.00000,3.00000));
	}

	@Test
	public void testCsvCalculator() {
		List<Double> result = CsvCalculator.processCsvInput(input);
		Assertions.assertEquals(expectedOutput, result);
	}
	
	/*
	 * @Test public void testCsvCalculator_cyclic_dependency() { input.clear();
	 * input.add("2,4,=A2+A3,=A0+A1*A2");
	 * 
	 * RuntimeException thrown = assertThrows(RuntimeException.class, ()-> {
	 * CsvCalculator.processCsvInput(input); }); assertTrue(thrown.getMessage().
	 * equalsIgnoreCase("Cyclic dependency at cell A2 for expression =A2+A3")); }
	 */
}
