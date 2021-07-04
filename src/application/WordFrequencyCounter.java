package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

/** Represents a word frequency counter.
 * @author Leon Silas
 * @author www.github.com/leonsilas
 * @version 1.0
*/

public class WordFrequencyCounter {

	/** Counts unique Strings from a File.
	 * @param fileName A string used to create a File.
	 * @param wordCount A Map used to store File Strings.
	*/
	public static void wordCounter(String fileName, Map<String, Integer> wordCount) {
        //file input
        Scanner input = null;
        try {
            File userFile = new File(fileName);
            input = new Scanner(userFile);
            int counter = 0;
    
            //counter
            while (input.hasNext()) {  
                String currentWord = input.next();
                //ensures start of poem
                if (counter < 1) {
                    if (currentWord.equalsIgnoreCase("<h1") && input.next().equalsIgnoreCase("class=\"center\">")) {
                        counter++;
                        continue;
                    }
                    else
                        continue;
                }

                //replacements
                currentWord = currentWord.replaceAll("<.*?>", "");
                currentWord = currentWord.replaceAll("<.*", "");
                currentWord = currentWord.replaceAll(".+>", "");
                currentWord = currentWord.replaceAll("style=.*", "");
                currentWord = currentWord.replaceAll("—", "");
                currentWord = currentWord.replaceAll("\\P{L}", "").toLowerCase(); 

                //ends reading at the end of poem
                if (currentWord.equalsIgnoreCase("end"))
                    break;

                if (currentWord.equalsIgnoreCase(""))
                    continue;

                //puts words into hashmap
                Integer words = wordCount.get(currentWord);
                if (words != null)
                    words++;
                else
                    words = 1;
                wordCount.put(currentWord, words);
            }

        } 
        //exception handling
        catch (FileNotFoundException e) {
            System.out.println("The file couldn't be read.");
            e.printStackTrace();
        }
        //closes scanner
        finally {
            if(input!=null)
                input.close();
        }
    }//end of wordFrequencyCounter

	/** Sorts Map from wordCounter.
	 * @param wordCount A Map used to store File Strings.
	 * @return sortedWordCount A TreeMap containing the sorted Strings and occurrences.
	*/
    public static TreeMap<Integer, String> sortHashMap (Map<String, Integer> wordCount) {
        //sorts using TreeMap
        TreeMap<Integer, String> sortedWordCount = new TreeMap<Integer, String>();
        for(Entry<String, Integer> entry : wordCount.entrySet())
            sortedWordCount.put(entry.getValue(), entry.getKey());

        return sortedWordCount;
    }//end of sortHashMap
	
	/** Places Strings and number of occurrences into individual arrays.
	 * @param topWords A String array used to store the words from wordCount.
	 * @param topOccurrences An Integer array used to store the occurrences from wordCount.
	 * @param wordCount A Map used to store File Strings.
	 * @param count An int used to limit number of words put into the arrays.
	*/
    public static void wordsToArrays(String[] topWords, Integer[] topOccurrences, Map<String,Integer> wordCount, int count) {
    	TreeMap<Integer,String> sortedWordCount = sortHashMap(wordCount);
        for (Entry<Integer, String> entry : sortedWordCount.entrySet()) {
            if (count == 20) 
               break;
            topWords[count] = entry.getValue();
            topOccurrences[count] = entry.getKey();
            count++;
         }
    }
    
	/** Outputs words and occurrences in readable format.
	 * @param topWords A String array used to store the words from wordCount.
	 * @param topOccurrences An Integer array used to store the occurrences from wordCount.
	*/
    public static void wordOutput(Integer[] topOccurrences, String [] topWords) {
        for (int i = 18; i >= 0; i--) {
        	System.out.println(topOccurrences[i] + " - " + topWords[i]);
        }
    }

}

