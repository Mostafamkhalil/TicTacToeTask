package APIs;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utils {

    public String getJsonValueFromResponse(Response response, String JsonKey){
        JsonPath jsnPath = response.getBody().jsonPath();
        return jsnPath.get(JsonKey);
    }
}
