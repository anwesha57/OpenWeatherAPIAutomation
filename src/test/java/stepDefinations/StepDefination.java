package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import static org.junit.Assert.*;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification request;
	Response response;
	
	@Given("{double} and {double} of the city for which wether report has to fetch")
	public void latitude_and_longitude_of_the_city_for_which_wether_report_has_to_fetch(double latitude, double longitude)throws IOException {		
		request = given().spec(requestSpecification()
				.queryParam("lat", latitude)
				.queryParam("lon", longitude)
				.queryParam("appid",getGlobalValue("apikey")));	    
		}
	
	@Given("{double} {double} and {string} of the city for which weather report api has to be tested")
	public void latitude_longitude_and_invalidappID_of_the_city_for_which_wether_report_has_to_fetch(double latitude, double longitude, String invalid_appid)throws IOException {		
		request = given().spec(requestSpecification())
				.queryParam("lat", latitude)
				.queryParam("lon", longitude)
				.queryParam("appid",invalid_appid);	    
		}
	
	
	@When("user calls currentweather api with get http request")
	public void user_calls_with_get_http_request() {
		response = request.when().get(getGlobalValue("resourceEndPoint"));
	}
	
	@Then("the API call returns status code {int}")
	public void api_call_returns_status_code(int status_code) {
		assertEquals(response.getStatusCode(),status_code);
	}
	
	@Then("{string} in response body is {string}")
	public void validate_response_body(String key, String value) {	
		   assertEquals(getJsonPath(response,key),value);
	}
		
	@Then("{int} {int} and {string} in response body are fetched accordingly")
	public void validate_response_body_parameters(Integer id, Integer timezone, String cityname) {
		assertEquals(String.valueOf(id),getJsonPath(response,"id"));
		assertEquals(String.valueOf(timezone),getJsonPath(response,"timezone"));
		assertEquals(cityname,getJsonPath(response,"name"));
	}
	
}
