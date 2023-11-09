package presentation.fx;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import facade.handlers.ITorneioServiceRemote;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.fx.inputcontroller.MenuPrincipalController;

public class Startup extends Application {
    
	private static ITorneioServiceRemote tornGesService;

	@Override 
    public void start(Stage stage) throws IOException {

        ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n.Bundle", new Locale("pt", "PT"));
		
    	FXMLLoader menuPrincipalLoader = new FXMLLoader(getClass().getResource("/fxml/menuPrincipal.fxml"), i18nBundle);
    	Parent root = menuPrincipalLoader.load();
    	
    	MenuPrincipalController mpc = menuPrincipalLoader.getController();

    	mpc.setTornGesService(tornGesService);
    	mpc.setI18NBundle(i18nBundle);
    	
        Scene scene = new Scene(root, 409, 329);
       
        stage.setTitle(i18nBundle.getString("application.title"));
        stage.setScene(scene);
        stage.show();
    }
	
	public static void startGUI(ITorneioServiceRemote tornGesService) {
		Startup.tornGesService = tornGesService;
        launch();
	}
}
