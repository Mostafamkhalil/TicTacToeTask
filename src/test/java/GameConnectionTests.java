import APIs.ConnectToGameAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GameConnectionTests extends UserCreationTests{
    protected ConnectToGameAPI connectToGameAPI;
    protected String gameToken;

    @Test(dependsOnMethods = "testValidUserCreation")
    public void testConnectToNewGame() throws InterruptedException {
        connectToGameAPI = new ConnectToGameAPI();
        connectToGameAPI.setUserToken(userToken);
        Response response = connectToGameAPI.connectToNewGame();
        int i=0;
        while(response.statusCode()==429){
            i++;
            Thread.sleep(1000);
            response = connectToGameAPI.connectToNewGame();
            if(i==10){
                break;
            }
        }
        response.then().assertThat().statusCode(201);
        gameToken = connectToGameAPI.getGameToken();
        Assert.assertNotNull(gameToken);
        System.out.println(gameToken);
    }
}
