package calculadora.calc;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Util {

	public static String maisZero(String valor) {
		if (valor.length() == 1) {
			valor = "0" + valor;
		}
		return valor;
	}

	public static boolean validar(String valor, String tipo) {

		if (!valor.matches("[+-]?\\d*(\\.\\d+)?")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Seleção inválida");
			alert.setHeaderText("Seleção de caracteres inválida");
			alert.setContentText("Por favor, selecione apenas números");
			alert.showAndWait();
			return false;
		}
		if(tipo.equals("hora")) {
			int v = Integer.parseInt(valor.equals("") ? "00" : valor);
			if(v > 23) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Seleção inválida");
				alert.setHeaderText("Seleção de caracteres inválida");
				alert.setContentText("Insira um número válido de 0 a 23 para as horas de chegada e saída");
				alert.showAndWait();
				return false;
			}
		}
		if(tipo.equals("minuto")) {
			int v = Integer.parseInt(valor.equals("") ? "00" : valor);
			if(v > 59) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Seleção inválida");
				alert.setHeaderText("Seleção de caracteres inválida");
				alert.setContentText("Insira um número válido de 0 a 59 para os minutos");
				alert.showAndWait();
				return false;
			}
		}
		return true;
	}
}
