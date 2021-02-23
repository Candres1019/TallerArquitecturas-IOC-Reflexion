package co.edu.escuelaing.arep.filereader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class DocumentReader {

    private static final DocumentReader _instance = new DocumentReader();
    private static final String indexPath = "src/main/resources/index.html";
    private static final String cssPath = "src/main/resources/css/style.css";
    private static final String appJsPath = "src/main/resources/js/app.js";
    private static final String homeroPath = "src/main/resources/img/homer.png";
    private static final String gokuPath = "src/main/resources/img/goku.png";

    private static final String contentCss = "Content-Type: text/css";
    private static final String contentJs = "Content-Type: text/javascript";
    private static final String contentHtml = "Content-Type: text/html";

    private DocumentReader() {
        // Constructor vacio a proposito
    }

    public static DocumentReader getInstance() {
        return _instance;
    }

    /**
     * Metodo para leer archivos de tipo imagen.
     *
     * @param clientSocket - clientSocket.
     */
    public static void imageReader(Socket clientSocket, String imageName) {
        try {
            String imagePath = "";

            switch (imageName) {
                case "homero":
                    imagePath = homeroPath;
                    break;
                case "goku":
                    imagePath = gokuPath;
                    break;
            }

            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            ImageIO.write(bufferedImage, "PNG", byteArrayOutputStream);
            dataOutputStream.writeBytes("HTTP/1.1 200 \r\n");
            dataOutputStream.writeBytes("Content-Type: image/jpeg \r\n");
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para leer archivos js, css y html
     *
     * @param clientSocket - clientSocket.
     * @param fileName     - tipo de archivo.
     */
    public static void fileReader(Socket clientSocket, String fileName) {
        try {
            String temp = "";
            String contenT = "";

            switch (fileName) {
                case "index":
                    temp = indexPath;
                    contenT = contentHtml;
                    break;
                case "app.js":
                    temp = appJsPath;
                    contenT = contentJs;
                    break;
                case "style.css":
                    temp = cssPath;
                    contenT = contentCss;
                    break;
            }
            File archivo = new File(temp);
            FileReader reader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(reader);
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            dataOutputStream.writeBytes("HTTP/1.1 200 OK");
            dataOutputStream.writeBytes(contenT);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                dataOutputStream.writeBytes(linea + "\r\n");
                dataOutputStream.writeBytes("\r\n");
            }
            bufferedReader.close();
            dataOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error en el socket");
        }
    }

    private String invalidHttpResponse() {
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
                + "<h1> Archivo No Encontrado </h1>\n"
                + "</body>\n"
                + "<html>\n";
    }

}
