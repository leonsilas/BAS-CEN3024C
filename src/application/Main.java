package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

//TODO Add Deployment - MODULE 11
//TODO Update Any Documentation for Module 11 before Final Submission (Module 10 is already documented, just pending 11 before using JavaDoc)

/** Represents the GUI for the program.
 * @author Leon Silas
 * @author www.github.com/leonsilas
 * @version 1.0
*/

public class Main extends Application {
	/** Starts the GUI.
	 * @param primaryStage A stage used as the main display output.
	*/
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			//new text
			Text text = new Text("Waiting for you to press the button...");
			
			//new button
			Button runButt = new Button();
			runButt.setText("Get the word frequency!");
			runButt.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
			//when the button is pressed
			runButt.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					
					 //creation of Map and use of counter
					Map<String, Integer> wordCount = new HashMap<String, Integer>();
					String fileName = "C:\\Users\\Sern\\Documents\\School Work\\2020-2021\\Summer2021\\Software Development I\\WordOccurrences\\lib\\theRavenPoem.html";
					int count = 0;
					String [] topWords ;
					Integer [] topOccurrences;
					topWords = new String[20];
					topOccurrences = new Integer[20];
					//call to class to do the work
					WordFrequencyCounter.wordCounter(fileName, wordCount);
					WordFrequencyCounter.wordsToArrays( topWords, topOccurrences, wordCount, count);        
					WordFrequencyCounter.wordOutput(topOccurrences, topWords);
					
					//change Text
					text.setText("Check your console to see the sorted words!");
				}
			
			});
			
			//stack for the window and adding parts
			VBox layout = new VBox();
			layout.setSpacing(10);
			layout.setAlignment(Pos.CENTER);
			layout.getChildren().add(runButt);
			layout.getChildren().add(text);
			
			//setting scene
			Scene scene = new Scene(layout,400,450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Text Stats");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Connects to the database.
	 * @throws Exception for failed connection.
	*/
	public static Connection getConnection() throws Exception {
		System.out.println("=====================================\n");
		try {
			//setup for connection
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/wordoccurrences";
			String username = "leon";
			String password = "password";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Opened database successfully\n");  
			System.out.println("=====================================\n");
			return conn;
		}
		catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return null;
	}
	
	/** Creates a table in connected database.
	 * @throws Exception for failed connection.
	*/
	public static void createTable() throws Exception {
		try {
			//connection to database
			Connection conn = getConnection();
			
			//creation of table
			Statement stmt = conn.createStatement();
			String dropsql = "DROP TABLE word";		//used for testing purposes to be able to drop the entire table and start over
			stmt.executeUpdate(dropsql);				   //avoids errors with overlapping table and data usage by accident
			String sql = "CREATE TABLE word " +
			                " (ID        		int							NOT NULL		AUTO_INCREMENT, " + 
			                " word			 varchar(255) 			NOT NULL, " +
			                " PRIMARY KEY(ID))"; 
			stmt.executeUpdate(sql);
			 
			 //closing database
			stmt.close();
			conn.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
			}
		finally {
			  System.out.println("Table created successfully\n"); 
			  System.out.println("=====================================\n");
		}
	}
	
	/** Main function to launch the GUI
	 * @param args A string array representing function passed parameters.
	 * @throws Exception. 
	*/
	public static void main(String[] args) throws Exception {
		//GUI
		createTable();
		launch(args);
	}//end of main
	
}
