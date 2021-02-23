# Taller de Arquitecturas de Servidores de Aplicaciones, Meta Protocolos de Objetos, Patron IOC y Reflexión

Aplicativo Web diseñado en Java con el objetivo de crear un servidor HTTP, y un framework que reemplace
al framework Spark, este servidor soporta peticiones get por medio de funciones lambda, para esta aplicación el servidor 
devuelve archivos estáticos para ser visualizados por el usuario, estos archivos son almacenados internamente en el directorio 
/src/main/resources

## Información Del Proyecto

* La documentación del las clases y los metodos del proyecto se encuentran en el directorio adjunto /Javadoc/apidocs.
* Haga click [aqui](./TallerClientesServicios.pdf) para ver el reporte del proyecto.

### Pre-Requisitos

Para correr este proyecto necesita los siguientes programas instalados, se adjuntan los
links de como descargarlos:

> * [Como Instalar Java 11](https://www.oracle.com/co/java/technologies/javase-jdk11-downloads.html)
> * [Como Instalar Apache Maven](http://maven.apache.org/download.html#Installation)

Adicionalmente se recomienda tener descargado los siguientes programas:

> * [Como Instalar Git](http://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
> * [Como Instalar HerokuCli](https://devcenter.heroku.com/articles/heroku-cli#download-and-install)

### Despliegue en Heroku
[![Deployed to Heroku](https://www.herokucdn.com/deploy/button.png)](https://afternoon-lake-64620.herokuapp.com/Apps/htmlShow)

### Calidad del código
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/e0c309d228444068b746127db6a10c62)](https://www.codacy.com/gh/Candres1019/TallerClientesServicios/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Candres1019/TallerClientesServicios&amp;utm_campaign=Badge_Grade)

### Integración Continua
[![CircleCI](https://circleci.com/gh/Candres1019/TallerClientesServicios.svg?style=svg)](https://app.circleci.com/pipelines/github/Candres1019/TallerClientesServicios)

### Instalación

1. Clonación o Descarga del Proyecto:

    * Para **Clonar** el proyecto utilice el siguiente comando en la ventana de comandos:
  
   ```
   git clone https://github.com/Candres1019/TallerClientesyServicios---AREP.git
   ```
   
    * Para **Descargar** el proyecto de click [aquí](https://github.com/Candres1019/TallerClientesServicios-AREP/archive/master.zip),
      la descarga comenzara de manera automática.
      
2. En una ventana de comandos ejecute el siguiente comando, dentro de la carpeta principal del proyecto:
    
   ```
    mvn package
    ```

3. Para ejecutar la aplicación de manera local utilizamos en la ventana de comandos el siguiente comando:
   
    > * Distribuciones Linux y MacOs:
    > ```
    > java $JAVA_OPTS -cp target/classes:target/dependency/* co.edu.escuelaing.demoruntime.DemoRunTime
    > ```
    > * Distribuciones Windows:
    > ```
    > java -cp target/classes;target/dependency/* co.edu.escuelaing.demoruntime.DemoRunTime
    > ```
    
4. Para ver el aplicativo web de manera local ingresamos al siguiente enlace, dentro de este enlace encontrará los botones 
   para realizar las acciones específicas (ver imagen, ver js, ver css, ver datos, insertar datos):
   
    > ```
    > http://localhost:3478/Apps/htmlShow
    > ```

5. Por defecto se creó la documentación JavaDoc y fue dejada en el directorio /Javadoc, si desea generar uno nuevo
   utilice el siguiente comando, esta documentación quedará en el directorio /target/site/apidocs :
   
   > ```
   > mvn javadoc:javadoc
   > ```

## Ejecución de pruebas
En una ventana de comandos, utilice el siguiente comando:
   ```
    mvn test
   ```

## Construido Con

* [Java 8](https://www.java.com/es/) - Lenguaje de Programación.
* [JUnit](https://junit.org/junit5/) - Pruebas de Unidad.
* [Maven](https://maven.apache.org/) - Manejo de dependecias.
* [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/) - Entorno de Desarrollo.

## Authors

* **Andres Mateo Calderón Ortega** - [Candres1019](https://github.com/Candres1019)

# Licencia
Este proyecto está licenciado bajo la GNU v3.0 - ver el archivo [LICENSE.md](https://github.com/Candres1019/TallerClientesServicios-AREP/blob/master/LICENSE) para más detalles