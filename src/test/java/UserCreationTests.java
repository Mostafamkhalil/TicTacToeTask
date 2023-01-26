
import APIs.CreateUserAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class UserCreationTests {
    protected CreateUserAPI createUserAPI;
    protected String userToken;
    protected String userID;

    @DataProvider(name = "UserRegistrationData")
    public Object[][] UserRegistrationData(){
        Object[][] data = new Object[1][2];
        Random rand =new Random();
        Integer randomInt = rand.nextInt(10000);

        data[0][0] = "mostafakhaleel"+randomInt.toString();
        data[0][1] = "mostafa.khaleel"+randomInt.toString()+"@gmail.com";

        return data;
    }

    @Test(dataProvider = "UserRegistrationData")
    public void testValidUserCreation(String nickName, String email) throws InterruptedException {
        createUserAPI = new CreateUserAPI();

        createUserAPI.setNickname(nickName);
        createUserAPI.setEmail(email);

        Response response = createUserAPI.registerNewUser();
        int i=0;
        while(response.statusCode()==429){
            i++;
            Thread.sleep(1000);
            response = createUserAPI.registerNewUser();
            if(i==10){
                break;
            }
        }
        response.then().assertThat().statusCode(201);
        userToken = createUserAPI.getUserToken();
        userID = createUserAPI.getUserId();
        Assert.assertNotNull(userToken);
        Assert.assertNotNull(userID);

    }
}
