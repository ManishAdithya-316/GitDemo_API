package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;  //for junit assertEquals()

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources_Enum;
import resources.TestDataBuild;
import resources.Utils_PlaceAPIs;

//note run the code using testrunner file(run as junit test)
public class StepDefinition extends Utils_PlaceAPIs { //import Utils_PlaceAPIs from resources package which i created Utils class contains reusable methods
	
	
	RequestSpecification reqSpec;
	ResponseSpecification respSpec;
	Response resp;
	RequestSpecification req;
	
	TestDataBuild tdata=new TestDataBuild();   //TestDataBuild class contains test data methods
	
	static String place_id;   //we use static so that place_id value is the same while running multiple scenarios
	
	
	@Given("I have the Add Place Payload with {string} {string} {string}")    //Most code copied from SpecBuilderTest.jaava
	public void i_have_the_add_place_payload(String name, String language, String address) throws IOException {    
		
		
		
		
		
		
		//		Relacing with specification builder code
//		Response res=	given().queryParam("qaclick123").log().all()
//		.body(ap)                         //passing AddPlace class object                      
//		.when().post("/maps/api/place/add/json")
//		.then().assertThat().statusCode(200)
//		.extract().response();  //extract().response().asString();
		
		reqSpec=given().spec(requestSpecification())  //call method directly since StepDef class extends utils just requestSpecification() not Utils_PlaceAPIs.requestSpecification()
		.log().all()
		.body(tdata.addPlacePayload(name,language,address)); //passing name,language and address to AddPlace class dynamically from feature file
		//returns AddPlace pojo class object which is used as input payload in jsonbody             //here we are diving request as RequestSpecification and then using that in calculating below response
	}

	@When("user calls the {string} with http {string} request")
	public void user_calls_the_with_http_post_request(String api_resource,String http_method) {
	    // Write code here that turns the phrase above into concrete actions
		
		respSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();  //contentType is optional
		
		//APIResources_Enum resrcEnum=APIResources_Enum.AddPlaceAPI; //gives value of add/get/delete place api resource url
		//Constructor will be called with the value of the resource that you pass in the feature file
		//here we are calling the constructor which sets the resource,then to fetch the value then we use the getResource() to get it
		APIResources_Enum resource=APIResources_Enum.valueOf(api_resource); //passing api name eg:AddPlaceAPI/GetPlaceAPI/DeletePlaceAPI from feature file to Enum class
		
		System.out.println(resource.getResource());
		
		if(http_method.equalsIgnoreCase("POST"))
		{
		resp=reqSpec.when()    //reqSpec is requst specification which is stored in global variable
				//.post("/maps/api/place/add/json")  hardcoded can create class and use values directly eg:Demo d=new Demo(); addAPI=d.getAddPlaceAPI;
				//or use enum
				.post(resource.getResource());  //.post("/maps/api/place/add/json")
			
				// .then().spec(respSpec).extract().response(); response we will use later/execute in another feature file step
		}
		else if(http_method.equalsIgnoreCase("GET")) 
			resp=reqSpec.when().get(resource.getResource());
		
	}

	@Then("API call is successfull with status code {int}")
	public void api_call_is_successfull_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions		
	    assertEquals(resp.getStatusCode(),200); //import static org.junit.Assert.*;  //for junit assertEquals()
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expected_json_value) {  //this gets values from placevalidations.feature file
	    // Write code here that turns the phrase above into concrete actions
	    //compares if js.get("status") ="OK" and js.get("scope")="APP" 
		
		//String out=resp.asString();
		//JsonPath js=new JsonPath(out);
		
		assertEquals(getJsonPath(resp,key).toString(),expected_json_value);
		
	}

	@Then("verify that place_id created using AddPlaceAPI maps to {string} using {string}")  //string is lower case in cucumber expression
	public void check_place_id(String expectedName,String api_name) throws IOException {
		
		//requestSpec
		place_id=getJsonPath(resp,"place_id");  //jsonpath js=new Jsonpath(response) js.get("key");
		reqSpec=given()
			 .spec(requestSpecification())   //uses 1st requestspecification
			 .queryParam("place_id", place_id);   //adds place id query param
		
		//When user calls "GetPlaceAPI" with "GET" http request ... reusing this method
		user_calls_the_with_http_post_request(api_name,"GET");  //this method updates the resp value (response)
		
		String actualName=getJsonPath(resp,"name");
		
		Assert.assertEquals(expectedName, actualName);
					
	}
	
	
	//You can implement this step using the snippet(s) below: (generated) if we run testrunner it shows unimplemented methods
	@Given("I have the Delete Place Payload body")
	public void i_have_the_delete_place_payload_body() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
		//create a request specification for every method which will be stored globally
		reqSpec=given().spec(requestSpecification())
		.body(tdata.deletePlacePayload(place_id));
		
	}


}
