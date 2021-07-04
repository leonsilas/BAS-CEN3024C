package application;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/** Test to ensure correct array input.
 * @author Leon Silas
 * @author www.github.com/leonsilas
 * @version 1.0
*/


class ArrayTest {

	@SuppressWarnings("static-access")
	@Test
	/** Starts the test.
	*/
	void test() {
		//test data
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		wordCount.put("this", 1);
		wordCount.put("is", 5);
		wordCount.put("a", 2);
		wordCount.put("word", 11);
		int count = 0;
		String [] topWords ;
		Integer [] topOccurrences;
		topWords = new String[20];
		topOccurrences = new Integer[20];
		WordFrequencyCounter test = new WordFrequencyCounter();
		
		//tests
		test.wordsToArrays(topWords, topOccurrences, wordCount, count);
		assertEquals(topWords[0], "this");
		assertEquals(topWords[3], "word");
		assertEquals(topOccurrences[0], 1);
		assertEquals(topOccurrences[3], 11);
	}

}
