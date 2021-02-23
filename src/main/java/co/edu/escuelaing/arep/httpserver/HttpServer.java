package co.edu.escuelaing.arep.httpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Servidor Http
 */
public class HttpServer {

    private static Socket clientSocket;
    private final Map<String, Processor> routesToProcesssors = new HashMap();
    private int port;

    public static Socket getClientSocket() {
        return clientSocket;
    }

    /**
     * Iniciador del servidor Http
     *
     * @param httPort - puerto por donde debe correr el servicio
     * @throws IOException - IOException
     */
    public void startServer(int httPort) throws IOException {
        port = httPort;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port + ".");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            clientSocket = null;

            try {
                System.out.println("Listo para recibir en puerto " + getPort() + " ... ");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;
            String outputLine;

            boolean isFirstLine = true;
            String path = "";

            while ((inputLine = in.readLine()) != null) {
                if (isFirstLine) {
                    path = inputLine.split(" ")[1];
                    isFirstLine = false;
                }
                if (!in.ready()) {
                    break;
                }
            }


            String resp = null;
            for (String key : routesToProcesssors.keySet()) {
                if (path.startsWith(key)) {
                    String newPath = path.substring(key.length());
                    resp = routesToProcesssors.get(key).handle(newPath, null, null);
                }
            }

            if (resp == null) {
                outputLine = validOkHtppResponse();
            } else {
                outputLine = resp;
            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Solicitar el puerto asignado al servidor.
     *
     * @return - puerto del servidor.
     */
    public int getPort() {
        return this.port = port;
    }

    /**
     * Modificar el puerto del servidor.
     *
     * @param port - Nuevo puerto para que el serividor corra.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Metodo para registrar el nuevo processor.
     *
     * @param path       -
     * @param proccessor -
     */
    public void registerProccessor(String path, Processor proccessor) {
        routesToProcesssors.put(path, proccessor);
    }

    /**
     * Metodo para retornar un html de respuesta valido.
     *
     * @return -
     */
    public String validOkHtppResponse() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>LAB04</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1> Mi Primer Mensaje </h1>\n"
                + "</body>\n"
                + "<html>\n";
    }

}
