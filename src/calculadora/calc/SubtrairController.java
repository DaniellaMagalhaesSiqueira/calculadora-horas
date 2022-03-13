package calculadora.calc;

import java.io.IOException;
import java.time.Duration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SubtrairController {

	  @FXML
	    private TextField horas1;

	    @FXML
	    private TextField horas2;

	    @FXML
	    private TextField min1;

	    @FXML
	    private TextField min2;

	    @FXML
	    private TextField totalH;

	    @FXML
	    private TextField totalM;

	    @FXML
	    void clicarCalcular(ActionEvent event) {
	    	int h1 = retornaInteiro(Util.validar(horas1.getText(), "outro") ? horas1.getText() : "00");
	    	int m1 = retornaInteiro(Util.validar(min1.getText(), "minuto") ? min1.getText() : "00");
	    	int h2 = retornaInteiro(Util.validar(horas2.getText(), "outro") ? horas2.getText() : "00");
	    	int m2 = retornaInteiro(Util.validar(min2.getText(), "minuto") ? min2.getText() : "00");
	    	Duration d = Duration.ofHours(h1);
	    	d = d.plusMinutes(m1);
	    	Duration d2 = Duration.ofHours(h2);
	    	d2 = d2.plusMinutes(m2);
	    	d = d.minus(d2);
	    	totalH.setText(Util.maisZero(d.toHours() + ""));
	    	d = d.minusHours(d.toHours());
	    	totalM.setText(Util.maisZero(d.toMinutes() + ""));
	    }	

	    @FXML
	    void clicarLimpar(ActionEvent event) {

	    	horas1.setText("");
	    	horas2.setText("");
	    	min1.setText("");
	    	min2.setText("");
	    	totalH.setText("");
	    	totalM.setText("");
	    }

	    @FXML
	    void irCalcularHoras(ActionEvent event) {
	    	loadView("calculadoraHoras.fxml");
	    }

	    @FXML
	    void irSomarHoras(ActionEvent event) {
	    	loadView("somarHoras.fxml");
	    }

	    @FXML
	    void irSubtrairHoras(ActionEvent event) {

	    }
	    
	    private int retornaInteiro(String valor) {
	    	
	    	if(valor.isEmpty()) {
	    		return 0;
	    	}
	    	int v = Integer.parseInt(valor);
	    	return v;
	    }
	    
	    private synchronized void loadView(String caminho){
	    	
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
				AnchorPane root = loader.load();
				Scene scene = Principal.getScene();
				AnchorPane principal = (AnchorPane) ((ScrollPane) scene.getRoot()).getContent();
				Node mainMenu = principal.getChildren().get(0);
				principal.getChildren().clear();
				principal.getChildren().add(mainMenu);
				principal.getChildren().addAll(root.getChildren());

			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
}
