package application;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

/** Test to ensure correct sorting.
 * @author Leon Silas
 * @author www.github.com/leonsilas
 * @version 1.0
*/

class SortTest {

	@Test
	/** Starts the test.
	*/
	void test() {
		//test data
		WordFrequencyCounter test = new WordFrequencyCounter();
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		wordCount.put("this", 1);
		wordCount.put("is", 5);
		wordCount.put("a", 2);
		wordCount.put("word", 11);
		
		TreeMap<Integer,String> correctOutput = new TreeMap<Integer,String>();
		correctOutput.put(1, "this");
		correctOutput.put(5, "is");
		correctOutput.put(2, "a");
		correctOutput.put(11, "word");
		
		@SuppressWarnings("static-access")
		TreeMap<Integer,String> output = test.sortHashMap(wordCount);

		assertEquals(output.keySet(), correctOutput.keySet());
	}

}
