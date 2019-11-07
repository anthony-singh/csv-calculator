package com.csv.calc;

import java.util.List;
import java.util.stream.Collectors;

import com.csv.utils.CsvUtil;
import com.csv.utils.FileUtil;
import com.csv.utils.MapStore;

public class CsvCalculator {

	public static void main(String[] args) {
		checkArguments(args);
		String inputFile = null;
		String outputFile = null;
		
		if(args[0].equals("-i")) {
			inputFile = args[1];
			outputFile = args[3];
		} else if (args[0].equals("-o")) {
			outputFile = args[1];
			inputFile = args[3];
		}
		
		List<String> list = FileUtil.readInputFromFile(inputFile);
		try {
			List<Double> resultList = processCsvInput(list);
			FileUtil.printResult(resultList, list, outputFile);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	
	/** Parallel Stream could be used for faster data processing but here we have a dependencies.
	 * Next cell may be dependent on the previous cell values and during parallel operations its quite
	 * possible that the dependent formula might get processed before the dependency which will fail to evaluate.
	 * */
	static List<Double> processCsvInput(List<String> lines) {
		CsvUtil.populateMapStore(lines);
		return MapStore.getInstance().getMapValues().stream().map(item -> item.getResult())
				.collect(Collectors.toList());
	}
	
	private static void checkArguments(String [] args) {
		if(args.length < 4 || args.length > 4) {
			System.err.println("Invalid number of arguments, required 4 found " + args.length);
			System.out.println("Usage : java -jar spreadsheet.jar -i input.csv -o output.csv");
			System.exit(-1);
		}
	}

}
