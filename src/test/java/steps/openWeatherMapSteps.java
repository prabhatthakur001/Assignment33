package steps;

import io.cucumber.java.en.Given;
import pages.OpenWeatherMap;

public class openWeatherMapSteps {

    @Given("I am able to extract cooridnates for the city")
    public void extractCoordinates( ){
        OpenWeatherMap openWeatherMap = new OpenWeatherMap();
        openWeatherMap.gettingLatAndLong();
    }

}
