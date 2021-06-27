package application;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ArrayTest {

	@SuppressWarnings("static-access")
	@Test
	void test() {
		//test data
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		wordCount.put("this", 1);
		wordCount.put("is", 5);
		wordCount.put("a", 2);
		wordCount.put("word", 11);
		int count = 0;
		String [] topWords ;
		Integer [] topOccurances;
		topWords = new String[20];
		topOccurances = new Integer[20];
		WordFrequencyCounter test = new WordFrequencyCounter();
		
		//tests
		test.wordsToArrays(topWords, topOccurances, wordCount, count);
		assertEquals(topWords[0], "this");
		assertEquals(topWords[3], "word");
		assertEquals(topOccurances[0], 1);
		assertEquals(topOccurances[3], 11);
	}

}
