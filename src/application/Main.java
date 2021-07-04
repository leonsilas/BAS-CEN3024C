package application;
	
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

//TODO Add JavaDoc - MODULE 9
//TODO Add Database - MODULE 10
//TODO Add Deployment - MODULE 11

/** Represents the GUI for the program.
 * @author Leon Silas
 * @author www.github.com/leonsilas
 * @version 1.0
*/

public class Main extends Application {
	@Override
	/** Starts the GUI.
	 * @param primaryStage A stage used as the main display output.
	*/
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
	
	/** Main function to launch the GUI
	 * @param args A string array representing function passed parameters.
	*/
	public static void main(String[] args) {
		//GUI
		launch(args);
	}//end of main
	
}
