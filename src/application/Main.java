package application;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root;
		Scene scene;
		
		root = FXMLLoader.load(getClass().getResource("IDE.fxml"));
		scene = new Scene(root);
		
		primaryStage.setTitle("BIDE");
		primaryStage.setMinWidth(600);
		primaryStage.setMinHeight(400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Button executeButton = (Button) root.lookup("#executeBtn");
		TextArea inputTextArea = (TextArea) root.lookup("#inputTextArea");
		TextArea outputTextArea = (TextArea) root.lookup("#outputTextArea");
		TextArea bfProgramTextArea = (TextArea) root.lookup("#bfProgramTextArea");
	    
		executeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override public void handle(MouseEvent e) {
	            	BfVirtualMachine bfVirtualMachine; 
					bfVirtualMachine = new BfVirtualMachine(
							inputTextArea,outputTextArea);
					bfVirtualMachine.setProgram(bfProgramTextArea.getText());
					Thread bfExecutionThread = new Thread(bfVirtualMachine);
					bfExecutionThread.start();
					
					e.consume();
	            }
	     });
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
