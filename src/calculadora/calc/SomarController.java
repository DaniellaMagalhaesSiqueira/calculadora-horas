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

public class SomarController {
	  @FXML
	    private TextField quaH;

	    @FXML
	    private TextField quaM;

	    @FXML
	    private TextField quiH;

	    @FXML
	    private TextField quiM;

	    @FXML
	    private TextField resH;

	    @FXML
	    private TextField resM;

	    @FXML
	    private TextField segH;

	    @FXML
	    private TextField segM;

	    @FXML
	    private TextField sexH;

	    @FXML
	    private TextField sexM;

	    @FXML
	    private TextField terH;

	    @FXML
	    private TextField terM;

	    @FXML
	    void clicarCalcular(ActionEvent event) {
	    	int h1 = retornaInteiro(Util.validar(segH.getText(), "hora") ? segH.getText() : "00");
	    	int h2 = retornaInteiro(Util.validar(terH.getText(), "hora") ? terH.getText() : "00");
	    	int h3 = retornaInteiro(Util.validar(quaH.getText(), "hora") ? quaH.getText() : "00");
	    	int h4 = retornaInteiro(Util.validar(quiH.getText(), "hora") ? quiH.getText() : "00");
	    	int h5 = retornaInteiro(Util.validar(sexH.getText(), "hora") ? sexH.getText() : "00");	    	
	    	Duration total = Duration.ofHours(h1);
	    	total = total.plusHours(h2);
	    	total = total.plusHours(h3);
	    	total = total.plusHours(h4);
	    	total = total.plusHours(h5);
	    	total = total.plusHours(h5);
	    	int m1 = retornaInteiro(Util.validar(segM.getText(), "minuto") ? segM.getText() : "00");
	    	int m2 = retornaInteiro(Util.validar(terM.getText(), "minuto") ? terM.getText() : "00");
	    	int m3 = retornaInteiro(Util.validar(quaM.getText(), "minuto") ? quaM.getText() : "00");
	    	int m4 = retornaInteiro(Util.validar(quiM.getText(), "minuto") ? quiM.getText() : "00");
	    	int m5 = retornaInteiro(Util.validar(sexM.getText(), "minuto") ? sexM.getText() : "00");
	    	total = total.plusMinutes(m1);
	    	total = total.plusMinutes(m2);
	    	total = total.plusMinutes(m3);
	    	total = total.plusMinutes(m4);
	    	total = total.plusMinutes(m5);
	    	int resHoras = retornaInteiro(resH.getText());
	    	total = total.plusHours(resHoras);
	    	int resMin = retornaInteiro(resM.getText());
	    	total = total.plusMinutes(resMin);
	    	resH.setText(Util.maisZero(total.toHours() + ""));
	    	total = total.minusHours(total.toHours());
	    	resM.setText(Util.maisZero(total.toMinutes() + ""));
	    }

	    @FXML
	    void clicarLimpar(ActionEvent event) {
	    	segH.setText("");
	    	terH.setText("");
	    	quaH.setText("");
	    	quiH.setText("");
	    	sexH.setText("");
	    	segM.setText("");
	    	terM.setText("");
	    	quaM.setText("");
	    	quiM.setText("");
	    	sexM.setText("");

	    	
	    }
	    

	    @FXML
	    void limparRes(ActionEvent event) {
	    	resH.setText("");
	    	resM.setText("");
	    }

	    @FXML
	    void irCalcularHoras(ActionEvent event) {
	    	loadView("calculadoraHoras.fxml");
	    }

	    @FXML
	    void irSomarHoras(ActionEvent event) {

	    }

	    @FXML
	    void irSubtrairHoras(ActionEvent event) {
	    	loadView("subtrairHoras.fxml");
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
