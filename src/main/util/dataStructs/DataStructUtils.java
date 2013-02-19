package main.util.dataStructs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class DataStructUtils {

	public static HashMap<String, String> merge(HashMap<String, String> h1, HashMap<String, String> h2) {
		HashMap<String, String> h3 = new HashMap<String, String>();
		
		// h1
		Iterator<Entry<String, String>> it = h1.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, String> pairs = it.next();
			h3.put(pairs.getKey(), pairs.getValue());
		}
		
		// h2
		it = h2.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, String> pairs = it.next();
			h3.put(pairs.getKey(), pairs.getValue());
		}
		
		// return merged cells
		return h3;
	}
	
	/**
	 * a filter based on a array of values.
	 * 
	 * @param h1
	 * @param values
	 * @return
	 */
	public static HashMap<String, String> filter(HashMap<String, String> h1, String[] values) {
		HashMap<String, String> h3 = new HashMap<String, String>();
		
		// cycle thru has values
		Iterator<Entry<String, String>> it = h1.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, String> pairs = it.next();

			// checks against the types.
			for(int i=0 ; i<values.length ; i++) {
				// add in file types
				if (pairs.getValue().equals(values[i])) {
					h3.put(pairs.getKey(), pairs.getValue());
				}
			}
		}
		
		// return filtered
		return h3;
	}
}
