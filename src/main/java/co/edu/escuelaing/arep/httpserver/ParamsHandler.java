package co.edu.escuelaing.arep.httpserver;

import java.util.HashMap;

/**
 * Clase ParamsHandler creada para manejar los parametros de las peticiones web.
 *
 * @author Andres Mateo Calderon Ortega.
 */
public class ParamsHandler {

    private static final ParamsHandler _instance = new ParamsHandler();

    /**
     * Constructor de la clase ParamsHandler.
     */
    public ParamsHandler() {
        //Metodo creado vacio a proposito
    }

    /**
     * Metodo para obtener una instancia de la clase ParamsHandler.
     *
     * @return - Objeto ParamsHandler.
     */
    public static ParamsHandler getInstance() {
        return _instance;
    }

    /**
     * Metodo para obtener parametros dependiendo de un path dado.
     *
     * @param path - path a ser analizado.
     * @return - HasMap con los parametros y sus valores.
     */
    public HashMap<String, String> getParams(String path) {
        System.out.println(path);
        HashMap<String, String> paramsMap = new HashMap<String, String>();
        String[] params = path.substring(path.indexOf('?') + 1).split("&");
        for (String s : params) {
            System.out.println(s);
            String[] tempParam = s.split("=");
            paramsMap.put(tempParam[0], tempParam[1]);
        }
        if (paramsMap.size() == 0) {
            return null;
        }
        return paramsMap;
    }

}
