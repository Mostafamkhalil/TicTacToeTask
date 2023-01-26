package APIs;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.HashMap;

public class CreateUserAPI extends BaseAPI{

    private final String endPoint = "/api/v1/user";
    private String nickname;
    private String email;
    private String userId;
    private String userToken;

    public CreateUserAPI(){
        super();
    }

    public Response registerNewUser(){

        HashMap body = requestBodyBuilder();

        Response response =
        given().
                body(body).
        when().
                post(BaseURL+endPoint);

        response.then().log().body();

        setUserId(utils.getJsonValueFromResponse(response,"userId"));
        setUserToken(utils.getJsonValueFromResponse(response,"userToken"));


        return response;
    }

    private HashMap requestBodyBuilder(){
        HashMap<String,String> body = new HashMap<String,String>();
        body.put("nickname",nickname);
        body.put("email",email);
        return body;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    private void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    private void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
