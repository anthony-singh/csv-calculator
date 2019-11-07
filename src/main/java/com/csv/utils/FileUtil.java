package com.csv.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileUtil {
	
	private FileUtil() {}
	
	public static List<String> readInputFromFile(String inputFilePath) {
		List<String> linesList = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				linesList.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linesList;
	}
	
	public static void printResult(List<Double> result, List<String> list, String file) {
		Queue<Integer> queue = new LinkedList<>();
		for(String str : list) {
			String [] arr = str.split(",");
			queue.add(arr.length);
		}
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			int index = 0;
			int lineBreakIndex = queue.poll();
			for(Double d : result) {
				if(index <
						lineBreakIndex) {
					writer.write(String.format("%.5f", d));
					//System.out.print(String.format("%.5f", d)); 
				} else if (index == lineBreakIndex) {
					writer.write(System.lineSeparator());
					writer.write(String.format("%.5f", d));
					//System.out.println(""); 
					//System.out.print(String.format("%.5f", d));
					if(!queue.isEmpty())
						lineBreakIndex = queue.poll();
					index = 0;
				}
				if(index + 1 < lineBreakIndex ) {
					writer.write(",");
					//System.out.print(",");
				}
				index++;
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}


}
