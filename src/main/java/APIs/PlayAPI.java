package APIs;

import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PlayAPI extends BaseAPI{

    private final String endPoint = "/api/v1/play";
    private String userToken;
    private String gameToken;
    private int positionX;
    private int positionY;
    private String playerCrossId;
    private String playerCircleId;
    private String actualPlayerId;


    public PlayAPI(){
        super();
    }

    public Response PlayAHit(){
        HashMap body = requestBodyBuilder();

        Response response =
                given().
                        body(body).
                when().
                        post(BaseURL+endPoint);

        response.then().log().body();

        setPlayerCircleId(utils.getJsonValueFromResponse(response,"playerCircleId"));
        setPlayerCrossId(utils.getJsonValueFromResponse(response,"playerCrossId"));
        setActualPlayerId(utils.getJsonValueFromResponse(response,"actualPlayerId"));

        return response;
    }

    private HashMap requestBodyBuilder(){
        HashMap<String,Object> body = new HashMap<String,Object>();
        body.put("userToken",userToken);
        body.put("gameToken",gameToken);
        body.put("positionX",positionX);
        body.put("positionY",positionY);
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

    public void setGameToken(String gameToken) {
        this.gameToken = gameToken;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getPlayerCrossId() {
        return playerCrossId;
    }

    public void setPlayerCrossId(String playerCrossId) {
        this.playerCrossId = playerCrossId;
    }

    public String getPlayerCircleId() {
        return playerCircleId;
    }

    public void setPlayerCircleId(String playerCircleId) {
        this.playerCircleId = playerCircleId;
    }

    public String getActualPlayerId() {
        return actualPlayerId;
    }

    public void setActualPlayerId(String actualPlayerId) {
        this.actualPlayerId = actualPlayerId;
    }
}
