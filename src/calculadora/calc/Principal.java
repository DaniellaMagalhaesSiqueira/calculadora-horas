package calculadora.calc;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Principal extends Application{
	
	private static Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		URL arquivoFXML = getClass().getResource("mainCalculadora.fxml");
		FXMLLoader loader = new FXMLLoader(arquivoFXML);
		
		ScrollPane root = loader.load();
		root.setFitToHeight(true);
		root.setFitToWidth(true);
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Calculadora de Horas e Horas Extras");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("clock.png")));
		primaryStage.show();
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
