package calculadora.calc;

import java.time.Duration;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Util {

	public static String maisZero(String valor) {
		if (valor.length() == 1) {
			valor = "0" + valor;
		}else if(valor.contains("-") && valor.length() == 2) {
			StringBuilder v = new StringBuilder(valor);
			v = v.insert(1, "0");
			valor = v.toString();
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
	
    
    public static int retornaInteiro(String valor) {
    	
    	if(valor.isEmpty()) {
    		return 0;
    	}
    	int v = Integer.parseInt(valor.trim());
    	return v;
    }
    
	
	public static Duration menos(String h1, String m1, String h2, String m2) {
		int hora1 = retornaInteiro(h1);
		int min1 = retornaInteiro(m1);
		int hora2 = retornaInteiro(h2);
		int min2 = retornaInteiro(m2);
		if(hora1 < 0 && min1 < 0 || hora1 > 0 && min1 > 0 || hora1 < 0 && min1 > 0) {
			Duration t1 = Duration.ofHours(hora1);
			t1 = t1.plusMinutes(min1);
		}
		if(hora1 > 0 && min1 < 0) {			
			Duration t1 = Duration.ofHours(hora1);
			t1 = t1.minusMinutes(min1);
		}
		if(hora1 < 0 && min1 > 0) {
			
		}
		Duration t1 = Duration.ofHours(hora1);
		t1 = t1.plusMinutes(min1);
		t1 = t1.minusHours(hora2);
		t1 = t1.minusMinutes(min2);
		return t1;
	}
	
	
	public static String converteDuration(Duration d) {
		String horas = d.toHours() + "";
		 d = d.minusHours(d.toHours());
		String minutos = d.toMinutes() + "";
		return maisZero(horas) + " : " + maisZero(minutos);
	}
}
