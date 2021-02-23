package co.edu.escuelaing.arep.picospring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interfaz para poder hacer inyecciones con mapeos @RequestMapping
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {

    /**
     * Metodo para poder hacer mapeo con el nombre value
     *
     * @return - Valor a mapear
     */
    String value();

}
