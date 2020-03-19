package server;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class FormValidation {
	public static boolean textFieldNotEmpty(TextField texty) {
		boolean r = false;
		if (texty.getText() != null && !texty.getText().isEmpty()) {
			r = true;
		}

		return r;
	}

	public static boolean textFieldNotEmpty(TextField texty, Label labely, String validator) {
		boolean r = true;
		String result = null;
		if (!textFieldNotEmpty(texty)) {
			r = false;
			result = validator;
		}
		labely.setText(result);
		labely.setTextFill(Color.web("red"));
		return r;
	}
}
