package main;

import gui.KiesTaalSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUpGui extends Application{

	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
        try {
            KiesTaalSchermController root = new KiesTaalSchermController();
            
            Scene scene = new Scene(root,810,600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Rummikub G07");
            primaryStage.setMinWidth(820);
            primaryStage.setMinHeight(600);
            primaryStage.show();
            primaryStage.setFullScreen(true);
    		primaryStage.setResizable(true);
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }    

}
