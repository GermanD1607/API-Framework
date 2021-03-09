import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class MessageSender {

    private Properties props = new Properties();

    private String url;
    private String key;
    private String session;

    public MessageSender() {
        try {
            props.load(new FileInputStream("application.properties"));
        } catch(IOException var2) {
            System.out.println("Hay un error leyendo el archivo de properties");
        }
        this.url = props.getProperty("url");
        this.key = props.getProperty("api_key");
        this.session = props.getProperty("session_id");
    }

    public Response getRequest(String endpoint) {
        String requestURL = url + endpoint+"?api_key="+key;
        Response response = given().contentType(ContentType.JSON).when().get(requestURL).andReturn();
        return response;
    }

    public Response authRequest(String endpoint, Token body) {
        String requestURL = url + endpoint+"?api_key="+key;
        Response response = given().contentType(ContentType.JSON).body(body).when().post(requestURL).andReturn();
        session=response.then().extract().path("session_id");
        return response;
    }
    public Response postValidate(String endpoint, ValidateBody body) {
        String requestURL = url + endpoint+"?api_key="+key;
        Response response = given().contentType(ContentType.JSON).body(body).when().post(requestURL).andReturn();
        return response;
    }

    public Response rateRequest(String endpoint, Value body) {
        String requestURL = url + endpoint+"?api_key="+key+"&session_id="+session;
        Response response = given().contentType(ContentType.JSON).body(body).when().post(requestURL).andReturn();
        return response;
    }
    public Response deleteRateRequest(String endpoint) {
        String requestURL = url + endpoint+"?api_key="+key+"&session_id="+session;
        Response response = given().contentType(ContentType.JSON).when().delete(requestURL).andReturn();
        return response;
    }

    public Response createListRequest(String endpoint, ListBody body) {
        String requestURL = url + endpoint+"?api_key="+key+"&session_id="+session;
        Response response = given().contentType(ContentType.JSON).body(body).when().post(requestURL).andReturn();
        return response;
    }
    public Response addRequest(String endpoint, MediaId body) {
        String requestURL = url + endpoint+"?api_key="+key+"&session_id="+session;
        Response response = given().contentType(ContentType.JSON).body(body).when().post(requestURL).andReturn();
        return response;
    }
    public Response clearRequest(String endpoint) {
        String requestURL = url + endpoint+"?api_key="+key+"&session_id="+session+"&confirm="+true;
        Response response = given().contentType(ContentType.JSON).when().post(requestURL).andReturn();
        return response;
    }
    public Response deleteRequest(String endpoint) {
        String requestURL = url + endpoint+"?api_key="+key+"&session_id="+session;
        Response response = given().contentType(ContentType.JSON).when().delete(requestURL).andReturn();
        return response;
    }

}
