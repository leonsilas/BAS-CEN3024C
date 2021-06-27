package application;

import static org.junit.Assert.assertNotNull;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CountingTest {

	@SuppressWarnings("static-access")
	@Test
	void test() {
		//test data
		WordFrequencyCounter test = new WordFrequencyCounter();
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		String fileName = "C:\\Users\\Sern\\Documents\\School Work\\2020-2021\\Summer2021\\Software Development I\\WordOccurances\\lib\\theRavenPoem.html";
		
		test.wordCounter(fileName, wordCount);
		assertNotNull(wordCount);
	}

}
