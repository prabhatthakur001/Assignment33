package pages;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class OpenWeatherMap {

    public double latitude;
    public double longitude;
    public void gettingLatAndLong(){

        RestAssured.baseURI = "http://api.openweathermap.org";
        String endpoint = "/geo/1.0/direct";
        String apiKey = "04959f1ef5d690a15ea9b5fc1178cafd";
        String location = "Bengaluru";
        int limit = 1;
        Response response = given()
                .header("Content-Type", "application/json")
                .queryParam("q", location)
                .queryParam("limit", limit)
                .queryParam("appid", apiKey)
                .accept(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
         latitude = jsonPath.getDouble("[0].lat");
         longitude = jsonPath.getDouble("[0].lon");

    }

    public void gettingWeatherReport(){

        RestAssured.baseURI = "https://api.openweathermap.org/data/3.0/onecall";
        String apiKey = "04959f1ef5d690a15ea9b5fc1178cafd";

        Response response = given()
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("appid", apiKey)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("lat", equalTo(latitude))
                .body("lon", equalTo(longitude))
                .extract()
                .response();
    }

}
