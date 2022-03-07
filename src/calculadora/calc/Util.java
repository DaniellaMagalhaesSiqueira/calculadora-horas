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
			alert.setTitle("Sele��o inv�lida");
			alert.setHeaderText("Sele��o de caracteres inv�lida");
			alert.setContentText("Por favor, selecione apenas n�meros");
			alert.showAndWait();
			return false;
		}
		if(tipo.equals("hora")) {
			int v = Integer.parseInt(valor.equals("") ? "00" : valor);
			if(v > 23) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Sele��o inv�lida");
				alert.setHeaderText("Sele��o de caracteres inv�lida");
				alert.setContentText("Insira um n�mero v�lido de 0 a 23 para as horas de chegada e sa�da");
				alert.showAndWait();
				return false;
			}
		}
		if(tipo.equals("minuto")) {
			int v = Integer.parseInt(valor.equals("") ? "00" : valor);
			if(v > 59) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Sele��o inv�lida");
				alert.setHeaderText("Sele��o de caracteres inv�lida");
				alert.setContentText("Insira um n�mero v�lido de 0 a 59 para os minutos");
				alert.showAndWait();
				return false;
			}
		}
		return true;
	}
}
