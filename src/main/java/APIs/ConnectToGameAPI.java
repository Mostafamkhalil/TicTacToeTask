package APIs;

import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ConnectToGameAPI extends BaseAPI{

    private final String endPoint = "/api/v1/connect";
    private String userToken;
    private String gameToken;
    private String gameId;

    public ConnectToGameAPI(){
        super();
    }

    public Response connectToNewGame(){
        HashMap body = requestBodyBuilder();

        Response response =
                given().
                        body(body).
                        when().
                        post(BaseURL+endPoint);

        response.then().log().body();

        setGameToken(utils.getJsonValueFromResponse(response,"gameToken"));
        setGameId(utils.getJsonValueFromResponse(response,"gameId"));

        return response;
    }

    private HashMap requestBodyBuilder(){
        HashMap<String,String> body = new HashMap<String,String>();
        body.put("userToken",userToken);
        return body;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getGameToken() {
        return gameToken;
    }

    private void setGameToken(String gameToken) {
        this.gameToken = gameToken;
    }

    public String getGameId() {
        return gameId;
    }

    private void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
