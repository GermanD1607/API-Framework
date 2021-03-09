import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ValidateBody {
        private String username;
        private String password;
        private String request_token;
        public ValidateBody(String user,String pass,String tkn){
            this.username=user;
            this.password=pass;
            this.request_token=tkn;
        }
        public String getUserName() {
            return username;
        }
        public String getPassword() {
            return password;
        }
        public String getToken() {
            return request_token;
        }
    }
