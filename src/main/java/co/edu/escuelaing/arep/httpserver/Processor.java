package co.edu.escuelaing.arep.httpserver;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Interfaz Processor
 */
public interface Processor {

    /**
     * Método para manejar las peticiones.
     *
     * @param path - link path.
     * @param req  - req
     * @param res - res
     * @return - Cadena html dependiendo del tipo.
     */
    String handle(String path, HttpRequest req, HttpResponse res);

}
