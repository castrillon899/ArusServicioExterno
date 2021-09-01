Este bloque contiene la estructura necesaria para construir un proyecto en java favoreciendo el enfoque de DDD. 

Los principales patrones y estilos de arquitectura que guían este bloque son

#### Arquitectura hexagonal
Arquitectura que fomenta  que nuestro dominio sea el núcleo de todas las capas, también conocida como puertos y adaptadores en la cual el dominio define los puertos y en las capas superiores se definen los adaptadores para desacoplar el dominio. Se divide princialmente en tres capas, **aplicación**, **dominio** e **infraestructura**
- **Infraestructura**: Capa que tiene las responsabilidades de realizar los adaptadores a los puertos definidos en el domino, exponer web services, consumir web services, realizar conexiones a bases de datos, ejecutar sentencias DML, en general todo lo que sea implementaciones de cualquier framework
- **Aplicación**: capa encargada de enrutar los eventos entrantes de la capa de infraestructura hacía la capa del dominio, generalmente se conoce como una barrera transaccional la cual agrupa toda la invocación de un caso de uso, se pueden encontrar patrones como Fabricas, Manejadores de Comandos, Bus de eventos, etc 
- **Dominio**: representa toda la lógica de negocio de la aplicación la cual es la razón de existir del negocio. Se busca evitar el anti-patron [https://martinfowler.com/bliki/AnemicDomainModel.html](https://martinfowler.com/bliki/AnemicDomainModel.html)  y favorecer el principio [https://martinfowler.com/bliki/TellDontAsk.html](https://martinfowler.com/bliki/TellDontAsk.html) en esta capa se pueden encontrar los siguientes patrones agregados, servicios de dominio, entidades, objetos de valor, repositorios (puerto), etc. 

Para obtener mas documentación sobre este tipo de arquitectura se recomienda [https://codely.tv/blog/screencasts/arquitectura-hexagonal-ddd/](https://codely.tv/blog/screencasts/arquitectura-hexagonal-ddd/)

#### Patrón CQRS:  
Patrón con el cual dividimos nuestro modelo de objetos en dos, un modelo para consulta y un modelo para comando (modificación de datos). Este patrón es recomendado cuando se va desarrollar lógica de negocio compleja porque nos ayuda a separar las responsabilidades y a mantener un modelo de negocio consistente. 

 - **Consulta**: modelo a través del cual se divide la responsabilidad para presentar datos en la interfaz de usuario, los objetos se modelan basado en lo que se va a presentar y no en la lógica de negocio, ejm: ver facturas, consultar clientes
 - **Comando**: son todas las operaciones que cambian el estado del sistema, ejm: (facturar, aplicar descuento), este modelo se construye todo el modelo de objetos basado en la lógica de negocio de la aplicación  

Para mayor documentación del patrón [https://martinfowler.com/bliki/CQRS.html](https://martinfowler.com/bliki/CQRS.html)

#### Especificaciones técnicas: 

 - Spring boot 2.1.2
 - Flayway -> administrar todos los script DDL e iniciliazadores de la bd 
 - Integración con Jenkins: Jenkins File
 - Integración con Sonar: Sonar properties
 - Acceso a la base de datos por medio de JDBC template
 - Se entregan pruebas de muestra automatizadas para cada una de las capas 
 - Pruebas de carga de ejemplo en el directorio microservicio/external-resources
 - Ejemplo de como modelar un Comando y un Query
 - Ejemplo de pruebas de integración con H2
 - Java 8
 - Se debe tener configurado el IDE con Lombok, descargar desde (https://projectlombok.org/download)
 
#### Tener en cuenta para usar bloque para producción: 
Al momento de descargar este bloque y se vaya usar de manera productiva se debe ejecutar la tarea de gradle **dependencyCheckAnalyze** la cual revisa las vulnerabilidades para las versiones de las librerias que usa el bloque. El reporte que genera esta tarea se encuentra en la carpeta build del proyecto raiz. Se deben resolver los reportes críticos

#### Tener en cuenta cada que se incluya una nueva libreria: 
Despues de incluir una libreria en gradle se debe ejecutar la tarea de gradle **dependencyCheckAnalyze** para revisar las vulnerabilidades reportadas. 

#### Estructura del proyecto: 
Se identifican dos carpetas principales, común y microservicio. Microservicio es la carpeta que contiene todo el código fuente para el primer microservicio de su proyecto, se recomienda cambiar el nombre de esta carpeta por la de su lógica de negocio y posteriormente modificar el archivo *settings.gradle*,  si necesita crear mas microservicios lo único que debe realizar es duplicar esta carpeta y realizar la modificación en el archivo *settings.gradle*. El proyecto común contiene código fuente que comparten todos los microservicios y capas, es una librería que importan los que requieran este código compartido, es importante tener en cuenta que no debe ir código de negocio en este lugar. 
Como cada microservicio se va realizar con CQRS, cada uno contiene su propia estructura de arquitectura hexagonal en la cual no se comparten los modelos.

#### Importar el proyecto:
Para importar el proyecto se recomienda usar Gradle en la versión 5.0, se debe importar desde la ruta *microservicio/build.gradle*
Después de importar el proyecto se queda viendo de la siguiente manera 

![enter image description here](https://drive.google.com/uc?id=1x2ZVpM2steX0Er-jDNoffQ_V6pRVdW0k)

#### Bloque HealthCheck
Es un bloque que tiene como fin saber el estado de otros bloques o servicios agregados como por ejemplo de mysql,sqlServer etc.Para esto es necesario crear un paquete com.ceiba.core.actuator en microservicio-consulta-infraestructura    e implementar una interfaz llamada  HealthCheck  con la anotacion @Component sobreescribiendo los siguientes metodos:

- **registrarBloque()**: Que tiene la funcion de registrar el bloque que quiere tenerse encuenta ,para esto es necesario que ala hora de construir la clase que implemente  la interface HealthCheck se inyecte la clase manejadorHealthCheckBloques que tiene en memoria los bloques 
implementados pasandole la cadena del nombre y la clase en si.

- **healthCheck()**: Es un metodo que se le delega al programador para que segun el servicio o el bloque usado implemente una funcionalidad que logre detectar que este ya no esta arriba.No devuleve un valor si no excepcion de tipo ExepcionBloqueSinServicio.

Al momento de crear el bloque principal pedira un tiempo que estara dado en  milisegundos llamado tiempoHealthCheck que estara guardado en archivo application.yaml de resources del microservicio.


nota* Es recomendable  tener muy encuenta el tiempo asignado a HealthCheck como tal en las base de datos el tiempo que tarda en verificar es 30000 milisegundos que en segundos son 30 entoces debe ser mayor a este , para que cuando  healthCheck  realice la revision ,ya todos los bloques hallan devuelto su valor para no tener  inconsistencias de
los valores devuelto .Se esperaria aumentar el tiempo cada vez que un bloque se implemente dependiendo tambien de su tiempo de retardo.



*Cualquier duda o aporte con este bloque contactar a juan.botero@ceiba.com.co o juan.castano@ceiba.com.co*
Hash de git relacionado: 01a59693








package co.tcc.httpconsumer;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;
import oauth.signpost.signature.HmacSha256MessageSigner;
import oauth.signpost.signature.OAuthMessageSigner;
import oauth.signpost.signature.SigningStrategy;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class TCCConsumerServiceJustoYBueno {
  private OAuthConsumer oAuthConsumer;
  
  private ResponseUtility responseUtility;
  
  private String consumerKey;
  
  private String consumerSecret;
  
  private String accessToken;
  
  private String accessTokenSecret;
  
  private String url;
  
  private String guiaId;
  
  private String estadoGuia;
  
  private String courierId;
  
  private String readTimeout;
  
  private String connectionTimeout;
  
  public String getReadTimeout() {
    return this.readTimeout;
  }
  
  public void setReadTimeout(String readTimeout) {
    this.readTimeout = readTimeout;
  }
  
  public String getConnectionTimeout() {
    return this.connectionTimeout;
  }
  
  public void setConnectionTimeout(String connectionTimeout) {
    this.connectionTimeout = connectionTimeout;
  }
  
  public ResponseUtility getResponseUtility() {
    return this.responseUtility;
  }
  
  public void setResponseUtility(ResponseUtility responseUtility) {
    this.responseUtility = responseUtility;
  }
  
  public String getConsumerKey() {
    return this.consumerKey;
  }
  
  public void setConsumerKey(String consumerKey) {
    this.consumerKey = consumerKey;
  }
  
  public String getConsumerSecret() {
    return this.consumerSecret;
  }
  
  public void setConsumerSecret(String consumerSecret) {
    this.consumerSecret = consumerSecret;
  }
  
  public String getAccessToken() {
    return this.accessToken;
  }
  
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
  
  public String getAccessTokenSecret() {
    return this.accessTokenSecret;
  }
  
  public void setAccessTokenSecret(String accessTokenSecret) {
    this.accessTokenSecret = accessTokenSecret;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getGuiaId() {
    return this.guiaId;
  }
  
  public void setGuiaId(String guiaId) {
    this.guiaId = guiaId;
  }
  
  public String getEstadoGuia() {
    return this.estadoGuia;
  }
  
  public void setEstadoGuia(String estadoGuia) {
    this.estadoGuia = estadoGuia;
  }
  
  public String getCourierId() {
    return this.courierId;
  }
  
  public void setCourierId(String courierId) {
    this.courierId = courierId;
  }
  
  public void setupContext(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
    this.oAuthConsumer = (OAuthConsumer)new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    this.oAuthConsumer.setTokenWithSecret(accessToken, accessTokenSecret);
    this.oAuthConsumer.setMessageSigner((OAuthMessageSigner)new HmacSha256MessageSigner());
    this.oAuthConsumer.setSigningStrategy((SigningStrategy)new AuthorizationHeaderSigningStrategy());
  }
  
  public void authorize(HttpRequestBase httpRequest) throws Exception {
    this.oAuthConsumer.sign(httpRequest);
  }
  
  public ResponseUtility executeGetRequest() {
    HttpPut httpPut;
    setupContext(this.consumerKey, this.consumerSecret, this.accessToken.equals("SIN_PARAMETRO") ? null : this.accessToken, 
        this.accessTokenSecret.equals("SIN_PARAMETRO") ? null : this.accessTokenSecret);
    this.responseUtility = new ResponseUtility();
    DefaultHttpClient client = new DefaultHttpClient();
    HttpParams params = client.getParams();
    HttpConnectionParams.setConnectionTimeout(params, Integer.parseInt(this.connectionTimeout));
    HttpConnectionParams.setSoTimeout(params, Integer.parseInt(this.readTimeout));
    client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
    HttpRequestBase httpRequest = null;
    String urlRequest = urlChange();
    this.responseUtility.setRequest(urlRequest);
    URI uri = null;
    try {
      uri = new URI(urlRequest);
    } catch (URISyntaxException e) {
      this.responseUtility.setCode("-1");
      this.responseUtility.setResponse(e.toString());
      return this.responseUtility;
    } 
    String methodtype = "PUT";
    if (methodtype.equals("PUT"))
      httpPut = new HttpPut(uri); 
    httpPut.addHeader("content-type", "application/json");
    httpPut.addHeader("Accept", "application/json");
    try {
      authorize((HttpRequestBase)httpPut);
    } catch (Exception e) {
      this.responseUtility.setCode("-1");
      this.responseUtility.setResponse(e.toString());
      return this.responseUtility;
    } 
    HttpResponse httpResponse = null;
    try {
      HttpHost target = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
      httpResponse = client.execute(target, (HttpRequest)httpPut);
      InputStream inputStraem = httpResponse.getEntity().getContent();
      StringWriter writer = new StringWriter();
      IOUtils.copy(inputStraem, writer, "UTF-8");
      String output = writer.toString();
      this.responseUtility.setCode(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
      this.responseUtility.setResponse(output);
    } catch (Exception e) {
      this.responseUtility.setCode("-2");
      this.responseUtility.setResponse(e.toString());
    } 
    return this.responseUtility;
  }
  
  private String urlChange() {
    return String.format(this.url, new Object[] { this.guiaId, this.estadoGuia, this.courierId });
  }
  
  public static void main(String[] args) {
    TCCConsumerServiceJustoYBueno customerJustoYBueno = new TCCConsumerServiceJustoYBueno();
    customerJustoYBueno.setConsumerKey("ck_62310c4c990d5e92cb9ab66db15ba740e58171ce");
    customerJustoYBueno.setConsumerSecret("cs_120c442c7c3b918a37faf55ba56b56185b182fa6");
    customerJustoYBueno.setAccessToken("SIN_PARAMETRO");
    customerJustoYBueno.setAccessTokenSecret("SIN_PARAMETRO");
    customerJustoYBueno
      .setUrl("http://34.106.234.68/wp-json/neptuno/v1/guia/?guia_id=%s&estado_guia=%s&courier_id=%s");
    customerJustoYBueno.setGuiaId("620001807");
    customerJustoYBueno.setEstadoGuia("alistado");
    customerJustoYBueno.setCourierId("TCC");
    customerJustoYBueno.setReadTimeout("9000");
    customerJustoYBueno.setConnectionTimeout("9000");
    ResponseUtility xxx = customerJustoYBueno.executeGetRequest();
    System.out.println(xxx.toString());
  }
}
