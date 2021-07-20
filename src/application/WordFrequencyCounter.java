package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Map;
import java.util.Scanner;
import javafx.scene.control.TextArea;

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

                //puts words into hashmap DEPRECIATED
                Integer words = wordCount.get(currentWord);
                if (words != null)
                    words++;
                else
                    words = 1;
                wordCount.put(currentWord, words);
                
                //puts words into database
                try {
        			//setup for connection
        			String driver = "com.mysql.cj.jdbc.Driver";
        			String url = "jdbc:mysql://localhost:3306/wordoccurrences";
        			String username = "leon";
        			String password = "password";
        			Class.forName(driver);
        			Connection conn = DriverManager.getConnection(url, username, password);
        			conn.setAutoCommit(false);
        			Statement stmt = conn.createStatement();
        			
        			//insertion into database
        			String sql = "INSERT INTO word (word) " +
        								"VALUES ('" + currentWord + "');";
	       	         stmt.executeUpdate(sql);
	
	    	         //closing database
	    	         stmt.close();
	    	         conn.commit();
	    	         conn.close();
        		}
        		catch ( Exception e ) {
        			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        			System.exit(0);
        		}
                
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
            System.out.println("Records created successfully\n");
            System.out.println("=====================================\n");  
        }
    }//end of wordFrequencyCounter
    
	/** Outputs words and occurrences in readable format.
	 * @param ta A TextArea used to output the words from wordCount.
	*/
    public static void wordOutput(TextArea ta) {
       
        //output using the database
        try {
			//setup for connection
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/wordoccurrences";
			String username = "leon";
			String password = "password";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			
			//output from database
		     String sql = "SELECT  count(*) AS occurrences, word FROM word GROUP BY word ORDER BY occurrences DESC LIMIT 20;";
		     ResultSet rs = stmt.executeQuery(sql);
		     ResultSetMetaData rsmd = rs.getMetaData();
		     int colNum = rsmd.getColumnCount();
		     
		     //prints out selection
		     while (rs.next()) {
		         for (int i = 1; i <= colNum; i++) {
		             String columnValue = rs.getString(i);
		             
		             //odds are the word, evens are the occurrences
		             if (i%2 == 0)
		            	 ta.appendText(columnValue + "\n");
		             else if (i == 20)
		            	 ta.appendText(columnValue);
		             else
		            	 ta.appendText(columnValue + " - ");
		         }
		     }
		
		     //closing database
		     stmt.close();
		     conn.commit();
		     conn.close();
		  } catch ( Exception e ) {
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     System.exit(0);
		  }
        finally {
        	System.out.println("Records outputted successfully\n");  
        	System.out.println("=====================================\n");
        }
    }

}

