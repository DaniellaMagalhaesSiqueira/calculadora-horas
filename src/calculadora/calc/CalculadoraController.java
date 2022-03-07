package calculadora.calc;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CalculadoraController {

	@FXML
	private Button calcularBotao;

	@FXML
	private TextField horasRegulares;

	@FXML
	private TextField horaEntrada;

	@FXML
	private TextField horaSaida;

	@FXML
	private RadioButton manterH;

	@FXML
	private TextField minutoEntrada;

	@FXML
	private TextField minutoSaida;

	@FXML
	private TextField resultadoHoras;

	@FXML
	private TextField resultadoExtras;

	@FXML
	private TextField somaHE;

	@FXML
	private TextField somaHT;

	@FXML
	private TextField somaME;

	@FXML
	private TextField somaMT;

	@FXML
	void calcular(ActionEvent event) {
		if(Util.validar(horaEntrada.getText(), "hora")) {			
			int horas = Integer.parseInt(horaEntrada.getText().isEmpty() ? "00" : horaEntrada.getText());
			if(Util.validar(minutoEntrada.getText(), "minuto")) {		
				int minutos = Integer.parseInt(minutoEntrada.getText().isEmpty() ? "00" : minutoEntrada.getText());
				LocalTime horaEntrada = LocalTime.of(horas, minutos);
				if(Util.validar(horaSaida.getText(), "hora")) {
					horas = Integer.parseInt(horaSaida.getText().isEmpty() ? "00" : horaSaida.getText());
					if(Util.validar(minutoSaida.getText(), "minuto")) {						
						minutos = Integer.parseInt(minutoSaida.getText().isEmpty() ? "00" : minutoSaida.getText());
						LocalTime horaSaida = LocalTime.of(horas, minutos);
						Duration total = Duration.between(horaEntrada, horaSaida);
						if (total.toHours() >= 7L) {
							total = total.minus(Duration.ofHours(1));
						}
						Long horasl = total.toHours();
						Duration m = total.minusHours(total.toHours());
						Long minutosl = m.toMinutes();
						String resultado = Util.maisZero(horasl + "") + " : " + Util.maisZero(minutosl + "");
						resultadoHoras.setText(resultado);
						if (!horasRegulares.getText().equals("")) {
							int h = Integer.parseInt(horasRegulares.getText());
							horasl -= h;
							resultadoExtras.setText(Util.maisZero(horasl + "") + " : " + Util.maisZero(minutosl + ""));
						}
					}
				}
			}
		}

	}

	@FXML
	void irSomarHoras(ActionEvent event) {
		loadView("somarHoras.fxml");
	}

	@FXML
	void irSubtrairHoras(ActionEvent event) {
		loadView("subtrairHoras.fxml");
	}

	@FXML
	void clicarLimpar(ActionEvent event) {
		horaEntrada.setText("");
		horaSaida.setText("");
		minutoEntrada.setText("");
		minutoSaida.setText("");
		resultadoHoras.setText("");
		resultadoExtras.setText("");
		horasRegulares.setText(manterH.isSelected() ? horasRegulares.getText() : "");
	}

	@FXML
	void somaExtra(ActionEvent event) {

		// Total de Horas Extras Calculadas
		if (!horasRegulares.getText().isEmpty()) {

			String total[] = resultadoExtras.getText().split(":");
			Long horas = Long.parseLong(total[0].trim());
			Long minutos = Long.parseLong(total[1].trim());
			Duration tot = Duration.ofHours(horas);
			tot = tot.plus(Duration.ofMinutes(minutos));

			// somando total do somatório anterior se estiver com valores
			int somaHora = Integer.parseInt(somaHE.getText().isEmpty() ? "00" : somaHE.getText());
			int somaMin = Integer.parseInt(somaME.getText().isEmpty() ? "00" : somaME.getText());

			tot = tot.plus(Duration.ofHours(somaHora));
			tot = tot.plus(Duration.ofMinutes(somaMin));

			// Preenchendo o campo Somatório
			somaHE.setText(Util.maisZero(tot.toHours() + ""));
			Long hs = tot.toHours();
			tot = tot.minusHours(hs);
			somaME.setText(Util.maisZero(tot.toMinutes() + ""));
		}
	}

	@FXML
	void somaTotal(ActionEvent event) {

		// Total de Horas Calculadas
		String total[] = resultadoHoras.getText().split(":");
		Long horas = Long.parseLong(total[0].trim());
		Long minutos = Long.parseLong(total[1].trim());
		Duration tot = Duration.ofHours(horas);
		tot = tot.plus(Duration.ofMinutes(minutos));

		// somando total do somatório anterior se estiver com valores
		int somaHora = Integer.parseInt(somaHT.getText().isEmpty() ? "0" : somaHT.getText());
		int somaMin = Integer.parseInt(somaMT.getText().isEmpty() ? "0" : somaMT.getText());

		tot = tot.plus(Duration.ofHours(somaHora));
		tot = tot.plus(Duration.ofMinutes(somaMin));

		// Preenchendo o campo Somatório
		somaHT.setText(Util.maisZero(tot.toHours() + ""));
		Long hs = tot.toHours();
		tot = tot.minusHours(hs);
		somaMT.setText(Util.maisZero(tot.toMinutes() + ""));

	}

	@FXML
	void limparSomaExtra(ActionEvent event) {
		somaHE.setText("");
		somaME.setText("");

	}

	@FXML
	void limparSomaTotal(ActionEvent event) {
		somaHT.setText("");
		somaMT.setText("");
	}

	private synchronized void loadView(String caminho) {

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
