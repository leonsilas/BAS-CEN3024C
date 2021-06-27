## Purpose of this code

This code is meant to taken in a given file, in this case an html file, and output only the poem.  This code isn't structured to be versatile and is only applicable to this specific file and poem.  [SCREENSHOT FOR CODE IN ACTION IS IN THE LIB FOLDER]

## Plan for this code

The workspace contains two folders by default, where:

- `main`: execute functions to take in the file, count the words, sort them, and list the top 20 from most to least frequent
    - `wordFrequencyCounter`: VOID - opens and reads the file, counts the words based on input.hasNext(), uses replaceAll() to remove unnecessary characters, equalsIgnoreCase() to ensure no duplication of words in Map, and puts the Integer into the Map for each word counted
    - `sortHashMap`: RETURN TREEMAP<INTEGER, STRING> - puts the hash map values into a TreeMap to sort the frequencies naturally and returns the new TreeMap
    - `loop for output`: uses enhanced for loop to take each entry in the sorted TreeMap and limit it to 20 in a new TreeMap. Print out the TreeMap using decendingMap.

## Problems and improvements to be made

The code currently doesn't work to get rid of hyphens in between words, despite adding a check for it in the replaceAll.  It also fails to sort some of the words in the TreeMap properly, such as the use of bird 10 times not being present in the final output.  Unknown as to why.  Complex use of Map to TreeMap to Treemap could be condensed and refactored more efficiently.