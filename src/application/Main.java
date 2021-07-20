package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
			Text text = new Text("Please wait after pressing to see your results. It may take awhile.");
			Text titleText = new Text("    We've found the file for \nThe Raven by Edgar Allan Poe");
			titleText.setFont(Font.font("Verdana", 20));
			//output text area
			TextArea ta = new TextArea();
			ta.setPrefHeight(360);
			ScrollPane sp = new ScrollPane(ta);
			sp.setHmax(0.8);
			sp.setPrefHeight(360);
			
			
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
					//call to class to do the work
					WordFrequencyCounter.wordCounter(fileName, wordCount);     
					WordFrequencyCounter.wordOutput(ta);
					
					//change Text
					text.setText("Done! Check out the top 20 words in this text below!");
				}
			
			});
			
			//stack for the window and adding parts
			VBox layout = new VBox(15);
			layout.setPadding(new Insets(10, 10, 0, 10));
			layout.setAlignment(Pos.CENTER);
			layout.getChildren().add(titleText);
			layout.getChildren().add(runButt);
			layout.getChildren().add(text);
			layout.getChildren().add(sp);
			
			//setting scene
			Scene scene = new Scene(layout,400,550);
			primaryStage.setTitle("Word Occurrences");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Connects to the database.
	 * @return conn Connection to the database.
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
	*/
	public static void main(String[] args) throws Exception {
		//GUI
		createTable();
		launch(args);
	}//end of main
	
}
