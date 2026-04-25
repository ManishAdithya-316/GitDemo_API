package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeDeletePlaceScenario() throws IOException {
		
		StepDefinition sd=new StepDefinition();
		if(StepDefinition.place_id==null)
		{
		sd.i_have_the_add_place_payload("Apple Store", "English", "Bangalore");
		sd.user_calls_the_with_http_post_request("AddPlaceAPI","POST");
		sd.check_place_id("Apple Store","GetPlaceAPI");
		}
		
	}
	
	
}
