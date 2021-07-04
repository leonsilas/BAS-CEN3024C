package application;

import static org.junit.Assert.assertNotNull;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/** Test to ensure correct word counting.
 * @author Leon Silas
 * @author www.github.com/leonsilas
 * @version 1.0
*/

class CountingTest {

	@SuppressWarnings("static-access")
	@Test
	/** Starts the test.
	*/
	void test() {
		//test data
		WordFrequencyCounter test = new WordFrequencyCounter();
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		String fileName = "C:\\Users\\Sern\\Documents\\School Work\\2020-2021\\Summer2021\\Software Development I\\WordOccurances\\lib\\theRavenPoem.html";
		
		test.wordCounter(fileName, wordCount);
		assertNotNull(wordCount);
	}

}
