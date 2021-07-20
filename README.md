## Purpose of this code

This code is meant to taken in a given file, in this case an html file, and output only the poem.  This code isn't structured to be versatile and is only applicable to this specific file and poem.  [SCREENSHOT FOR CODE IN ACTION IS IN THE LIB FOLDER]

## Plan for this code

The workspace contains two folders by default, where:

- `main`: supports GUI while taking in file and making database table. on button press execute functions from wordFrequencyCounter
    - `wordFrequencyCounter`: VOID - opens and reads the file, counts the words based on input.hasNext(), uses replaceAll() to remove unnecessary characters, equalsIgnoreCase() to ensure same words, and then stores them in a sql database to be brought out later sorted from descending with the top 20 words.

## Final thoughts

After working throughout this semester and learning more about Java specifics, I think learning about using a database and JavaFX really brought the project together.  Trying to store things in a TreeMap and then sorting them and doing all kinds of other things was complicated and a headache.  Simply storing them in the database and then using SQL to pull them out the way I wanted was so much easier, and it condensed my code while making it much easier to read and interpret on both the back and front end.  Definitely one of my favorite projects to work on so far.