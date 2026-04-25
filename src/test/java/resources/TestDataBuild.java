package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {  //Class used for handling test data
	
	public AddPlace addPlacePayload(String name,String language, String address) {  //passing name,language and address to AddPlace class dynamically from feature file
		
		AddPlace ap=new AddPlace();
		
		ap.setAccuracy(50);
		
		//ap.setName("Frontline house");
		ap.setName(name);
		
		ap.setPhone_number("\"(+91) 983 893 3937");
		
		//ap.setAddress("29, side layout, cohen 09");
		ap.setAddress(address);
		
		ap.setWebsite("http://google.com");
		
		//ap.setLanguage("English-IN");
		ap.setLanguage(language);
		
		List<String> types=new ArrayList();
		types.add("shoe park");
		types.add("shop");
		
		ap.setTypes(types); //types array
		
		
		Location loc=new Location(); //import Location class from pojo package
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		ap.setLocation(loc); //location class
		
		return ap;
	}
	
	public String deletePlacePayload(String place_id) {  //{ "place_id" : "abc" }
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}\r\n"
				+ "";
	}

}
