package application;
	
import javafx.application.Application;
import javafx.fxml.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


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
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
