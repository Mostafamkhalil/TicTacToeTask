import APIs.PlayAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayTests extends GameConnectionTests{
    private PlayAPI playAPI;
    private String actualPlayerId;

    @Test(dependsOnMethods = "testConnectToNewGame")
    public void testPlayASingleHit() throws InterruptedException {
        playAPI = new PlayAPI();
        playAPI.setUserToken(userToken);
        playAPI.setGameToken(gameToken);
        playAPI.setPositionX(1);
        playAPI.setPositionY(2);
        Response response = playAPI.PlayAHit();
        int i=0;
        while(response.statusCode()==429){
            i++;
            Thread.sleep(1000);
            response = playAPI.PlayAHit();
            if(i==10){
                break;
            }
        }
        response.then().assertThat().statusCode(201);
        actualPlayerId = playAPI.getActualPlayerId();
        Assert.assertEquals(actualPlayerId,userID);
    }
}
