package co.edu.escuelaing.arep.controllers;

import co.edu.escuelaing.arep.filereader.DocumentReader;
import co.edu.escuelaing.arep.httpserver.HttpServer;
import co.edu.escuelaing.arep.picospring.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase SpringAppController, hecha para manejar las peticiones de la aplicacion web principal.
 *
 * @author Andres Mateo Calderon Ortega.
 */
public class SpringAppController {

    private static final DocumentReader documentReader = DocumentReader.getInstance();
    private static final ArrayList<String> listaDeUsuarios = new ArrayList<>();

    /**
     *
     */
    @RequestMapping(value = "/")
    public static void noPath(){
        getIndex();
    }

    /**
     * Metodo para solicitar el index de la aplicacion
     */
    @RequestMapping(value = "/index")
    public static void getIndex() {
        DocumentReader.fileReader(HttpServer.getClientSocket(), "index");
    }

    /**
     * Metodo para solicitar el css utilizado en la pagina index.html
     */
    @RequestMapping(value = "/css/style.css")
    public static void getCss() {
        DocumentReader.fileReader(HttpServer.getClientSocket(), "style.css");
    }

    /**
     * Metodo para solicitar el JavaScript utilizado en la pagina index.html
     */
    @RequestMapping(value = "/js/app.js")
    public static void getScriptJs() {
        DocumentReader.fileReader(HttpServer.getClientSocket(), "app.js");
    }

    /**
     * Metodo para solicitar imagenes.
     *
     * @param arguments - HashMap que contendra el nombre de la imagen para cargar.
     */
    @RequestMapping(value = "/cargarImagen")
    public static void getHomero(HashMap<String, String> arguments) {
        DocumentReader.imageReader(HttpServer.getClientSocket(), arguments.get("img"));
    }

    /**
     * Metodo hecho para ser usado desde JavaScript para ver los usuarios registrados de manera temporal en la app.
     *
     * @return - lista de usuarios en la app
     */
    @RequestMapping(value = "/usuarios/ver")
    public static String getCostumers() {
        StringBuilder usuarios = new StringBuilder();
        for (String s : listaDeUsuarios) {
            usuarios.append(s).append("\n");
        }
        return validOkHttpHeader() + usuarios;
    }

    /**
     * Metodo para ingresar un usuario a la lista de usuarios de la app.
     *
     * @param arguments - HashMap con el usuario a ser ingresado.
     * @return - nombre del usuario ingresado.
     */
    @RequestMapping(value = "/usuarios/add")
    public static String addUsuario(HashMap<String, String> arguments) {
        StringBuilder nombre = new StringBuilder();
        String[] dataSeparada = arguments.get("name").split("%20");
        for (String s : dataSeparada) {
            nombre.append(s).append(" ");
        }
        listaDeUsuarios.add(nombre.toString());
        return validOkHttpHeader() + nombre;
    }

    /**
     * Encabezado http valido
     *
     * @return - Encabezado http valido.
     */
    public static String validOkHttpHeader() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n";
    }

}
