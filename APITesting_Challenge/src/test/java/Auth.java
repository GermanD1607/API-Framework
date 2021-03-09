import io.restassured.response.Response;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Auth {
    MessageSender messageSender;

    public Auth() {
        messageSender=new MessageSender();
    }

    @org.testng.annotations.Test
    public void AuthTest()
    {
        Response response=messageSender.getRequest("/authentication/token/new");
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("application.properties"));
        } catch(IOException var2) {
            System.out.println("Hay un error leyendo el archivo de properties");
        }
        String tkn = response.then().extract().path("request_token");
        Token bodyToken = new Token(tkn);
        ValidateBody bodyValidate = new ValidateBody(props.getProperty("user"),props.getProperty("password"),tkn);
        Response validate=messageSender.postValidate("/authentication/token/validate_with_login",bodyValidate);
        Response session=messageSender.authRequest("/authentication/session/new",bodyToken);
        System.out.println(response.then().log().body());
        System.out.println(validate.then().log().body());
        System.out.println(session.then().log().body());
        Assert.assertEquals(200,session.statusCode());
    }

}
