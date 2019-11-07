package com.csv.utils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.csv.calc.Cell;

public class MapStore {
	
	private static Map<String, Cell> map = new LinkedHashMap<>(26 * 5000000);
	private static MapStore instance = null;
	
	private MapStore() {}
	
	public static MapStore getInstance() {
		if(instance == null) {
			instance = new MapStore();
		}
		return instance;
	}
	
	public void put(String s, Cell c) {
		map.put(s, c);
	}
	
	public Cell get(String s) {
		return map.get(s);
	}
	
	public boolean containsKey(String key) {
		return map.containsKey(key);
	}
	
	public Collection<Cell> getMapValues() {
		return  map.values();
	}
}
