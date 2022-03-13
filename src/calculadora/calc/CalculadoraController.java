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
		if (Util.validar(horaEntrada.getText(), "hora")) {
			int horas = Integer.parseInt(horaEntrada.getText().isEmpty() ? "00" : horaEntrada.getText());
			if (Util.validar(minutoEntrada.getText(), "minuto")) {
				int minutos = Integer.parseInt(minutoEntrada.getText().isEmpty() ? "00" : minutoEntrada.getText());
				LocalTime horaEntrada = LocalTime.of(horas, minutos);
				if (Util.validar(horaSaida.getText(), "hora")) {
					horas = Integer.parseInt(horaSaida.getText().isEmpty() ? "00" : horaSaida.getText());
					if (Util.validar(minutoSaida.getText(), "minuto")) {
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
						// Horas Extras
						if (!horasRegulares.getText().equals("")) {
							int hr = Integer.parseInt(horasRegulares.getText());
							Duration h = Duration.ofHours(hr);
							total = total.minus(h);
							if (total.isNegative()) {
							}
							resultadoExtras.setText(Util.converteDuration(total));

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
		if (!horasRegulares.getText().isEmpty()) {
			
			String total[] = resultadoExtras.getText().split(":");
			int horas = Util.retornaInteiro(total[0].trim());
			int minutos = Util.retornaInteiro(total[1].trim());
			Duration tot = Duration.ofHours(horas);
			tot = tot.plusMinutes(minutos);
			
			if (somaHE.getText().isEmpty()) {
				somaHE.setText(total[0]);
				somaME.setText(total[1]);
				return;
			} else {
				int hs = Util.retornaInteiro(somaHE.getText().trim());
				int ms = Util.retornaInteiro(somaME.getText().trim());
				Duration soma = Duration.ofHours(hs);
				soma = soma.plusMinutes(ms);
				tot = tot.plus(soma);
				String[] r = Util.converteDuration(tot).split(":");
				
				somaHE.setText(r[0]);
				somaME.setText(r[1]);
				return;
			}
			
		}
	}

	@FXML
	void somaTotal(ActionEvent event) {

		String total[] = resultadoHoras.getText().split(":");
		Long horas = Long.parseLong(total[0].trim());
		Long minutos = Long.parseLong(total[1].trim());
		Duration tot = Duration.ofHours(horas);
		tot = tot.plusMinutes(minutos);

		int somaHora = Integer.parseInt(somaHT.getText().isEmpty() ? "00" : somaHT.getText().trim());
		int somaMin = Integer.parseInt(somaMT.getText().isEmpty() ? "00" : somaMT.getText().trim());
		Duration soma = Duration.ofHours(somaHora);
		soma = soma.plusMinutes(somaMin);

		Duration res = tot.plus(soma);
		String[] r = Util.converteDuration(res).split(":");
		somaHT.setText(r[0]);
		somaMT.setText(r[1]);

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
