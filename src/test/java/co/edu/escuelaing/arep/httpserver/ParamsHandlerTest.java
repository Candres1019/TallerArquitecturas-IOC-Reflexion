package co.edu.escuelaing.arep.httpserver;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ParamsHandlerTest {

    @Test
    public void getParams() {
        ParamsHandler paramsHandler = new ParamsHandler();
        HashMap<String, String> params = paramsHandler.getParams("http://localhost:8080/prueba?name=4");
        int value = 4;
        assertEquals(Integer.parseInt(params.get("name")), value);
    }

    @Test
    public void getParamsNoParams() {
        ParamsHandler paramsHandler = new ParamsHandler();
        HashMap<String, String> params = paramsHandler.getParams("http://localhost:8080/prueba");
        assertTrue(params == null);
    }

}