package client;

import javax.ejb.EJB;

import facade.handlers.ITorneioServiceRemote;

/**
 * A simple application client that uses both services.
 *
 * @author fmartins
 * @author alopes
 * @version 1.3 (02/05/2017)
 */
public class Main {
		
	// run with the following command inside the torngest project directory:
	// PATH-TO-WILDFLY/bin/appclient.sh torngest-ear/target/torngest-ear-1.0.ear#torngest-gui-client.jar
	// Example in case the wildfly server is in your homedir in dir wildfly-10.1.0.Final
	// dentro da pasta do projeto: appclient.bat torngest-ear/target/torngest-ear-1.0.ear#torngest-gui-client.jar 
	
    @EJB
    private static ITorneioServiceRemote tornGesService;

	/**
	 * An utility class should not have public constructors
	 */
	private Main() {
	}

    /**
     * A simple interaction with the application services
     *
     * @param args Command line parameters
     */
    public static void main(String[] args) {
    	presentation.fx.Startup.startGUI(tornGesService);
    }
}