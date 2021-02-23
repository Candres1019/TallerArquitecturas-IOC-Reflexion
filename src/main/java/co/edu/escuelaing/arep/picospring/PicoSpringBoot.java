package co.edu.escuelaing.arep.picospring;

import co.edu.escuelaing.arep.httpserver.HttpServer;
import co.edu.escuelaing.arep.httpserver.ParamsHandler;
import co.edu.escuelaing.arep.httpserver.Processor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase enccargada de inciar un servidor PicoSpringBoot
 *
 * @author Andres Mateo Calderon Ortega.
 */
public class PicoSpringBoot implements Processor {

    private static final PicoSpringBoot _instance = new PicoSpringBoot();
    private final Map<String, Method> requestProcessors = new HashMap();
    private final ParamsHandler paramsHandler = ParamsHandler.getInstance();
    private HttpServer hserver;

    /**
     * Constructor de la clase PicoSpringBoot
     */
    private PicoSpringBoot() {
        // Constructor vacio a proposito
    }

    /**
     * Metodo main de la clase PicoSpringBoot
     *
     * @param args - args
     * @throws IOException            - IOException
     * @throws ClassNotFoundException - ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PicoSpringBoot.getInstance().loadComponents(args);
        PicoSpringBoot.getInstance().startServer();
    }

    /**
     * Metodo para obtener la isntancia de la clase PicoSpringBoot
     *
     * @return - Objeto PicoSpringBoot
     */
    public static PicoSpringBoot getInstance() {
        return _instance;
    }

    /**
     * Metodo para iniciar el servidor.
     *
     * @throws IOException - IOException
     */
    public void startServer() throws IOException {
        hserver = new HttpServer();
        hserver.registerProccessor("", this);
        hserver.startServer(getPort());
    }

    /**
     * Metodo para cargar la lista de componentes.
     *
     * @param componentsList - lista de componentes.
     * @throws ClassNotFoundException - ClassNotFoundException
     */
    public void loadComponents(String[] componentsList) throws ClassNotFoundException {
        for (String componentName : componentsList) {
            loadComponent(componentName);
        }
    }

    /**
     * Metodo para cargar un componente.
     *
     * @param componentName - nombre del componente.
     * @throws ClassNotFoundException - ClassNotFoundException
     */
    private void loadComponent(String componentName) throws ClassNotFoundException {
        Class componentClass = Class.forName(componentName);
        Method[] componentMethods = componentClass.getDeclaredMethods();
        for (Method m : componentMethods) {
            if (m.isAnnotationPresent(RequestMapping.class)) {
                System.out.println("Registering method: " + m.toString() + "attached to path: " + m.getAnnotation(RequestMapping.class).value());
                requestProcessors.put(m.getAnnotation(RequestMapping.class).value(), m);
            }
        }
    }

    /**
     * Método para manejar las peticiones.
     *
     * @param path - link path.
     * @param req  -
     * @param resp -
     * @return - Cadena html dependiendo del tipo.
     */
    @Override
    public String handle(String path, HttpRequest req, HttpResponse res) {
        if (HttpServer.getClientSocket().isClosed()) {
            while (HttpServer.getClientSocket().isClosed()) {
                try {
                    wait(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        String resp = "completed";
        String pathNoParams = path;
        if (path.contains("?")) {
            pathNoParams = (String) path.subSequence(0, path.indexOf('?'));
        }
        if (requestProcessors.containsKey(pathNoParams)) {
            try {
                System.out.println("Executing method attached to: " + pathNoParams);
                HashMap<String, String> arguments = null;
                try {
                    if (path.contains("?")) {
                        resp = requestProcessors.get(pathNoParams).invoke(null, paramsHandler.getParams(path)).toString();
                    } else {
                        resp = requestProcessors.get(pathNoParams).invoke(null, null).toString();
                    }
                } catch (NullPointerException ex) {
                    if (path.contains("?")) {
                        requestProcessors.get(pathNoParams).invoke(null, paramsHandler.getParams(path));
                    } else {
                        requestProcessors.get(pathNoParams).invoke(null, null);
                    }
                }
            } catch (Exception e) {
                resp = null;
                e.printStackTrace();
            }
        }
        return resp;
    }

    /**
     * Retorna el puerto por el que deberia correr el servidor, creado para evitar errores en un ambiente de
     * despliegue no local
     *
     * @return - puerto.
     */
    private int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }

}
