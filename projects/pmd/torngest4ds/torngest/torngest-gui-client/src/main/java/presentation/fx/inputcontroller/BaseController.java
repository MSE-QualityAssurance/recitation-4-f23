package presentation.fx.inputcontroller;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BaseController {

	ResourceBundle i18nBundle;

	public void showError(String errorText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(i18nBundle.getString("error.dialog.title"));
		alert.setHeaderText(null);
		alert.setContentText(errorText);
		alert.showAndWait(); 
	}

	public void showInfo(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(i18nBundle.getString("info.dialog.title"));
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public void setI18NBundle(ResourceBundle i18nBundle) {
		this.i18nBundle = i18nBundle;
	}


}
