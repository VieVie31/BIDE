package application;


import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
		
		Button openButton = (Button) root.lookup("#openBtn");
		Button saveButton = (Button) root.lookup("#saveBtn");
		Button executeButton = (Button) root.lookup("#executeBtn");
		//Button stopButton = (Button) root.lookup("#stopBtn");
		TextArea inputTextArea = (TextArea) root.lookup("#inputTextArea");
		TextArea outputTextArea = (TextArea) root.lookup("#outputTextArea");
		TextArea bfProgramTextArea = (TextArea) root.lookup("#bfProgramTextArea");
	    TableView<?> memoryTableView = (TableView<?>) root.lookup("#memoryTableView");
		
	    openButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
            	File file = new FileChooser().showOpenDialog(primaryStage);
				
            	try {
            		byte[] content = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            		bfProgramTextArea.setText(new String(content));
            		inputTextArea.setText("");
            		outputTextArea.setText("");
            		//TODO: reset memory table
            	} catch (Exception ex) {}
            	
				e.consume();
            }
        });
	    
	    saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
            	File file = new FileChooser().showSaveDialog(primaryStage);
				
            	try {
            		FileWriter fw = new FileWriter(file);
            		fw.write(bfProgramTextArea.getText());
            		fw.close();
            	} catch (Exception ex) {}
            	
				e.consume();
            }
        });
	    
		executeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            @Override public void handle(MouseEvent e) {
	            	outputTextArea.setText("");//reset output
	            	
	            	BfVirtualMachine bfVirtualMachine; 
					bfVirtualMachine = new BfVirtualMachine(
							inputTextArea,outputTextArea, memoryTableView);
					bfVirtualMachine.setProgram(bfProgramTextArea.getText());
					
					Thread bfExecutionThread = new Thread(bfVirtualMachine);
					bfExecutionThread.start();
					
					e.consume();
	            }
	    });
		
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
