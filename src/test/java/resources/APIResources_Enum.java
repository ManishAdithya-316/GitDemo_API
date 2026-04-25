package resources;


//Enum is a special Class in java which is a collection of constants or methods
public enum APIResources_Enum {
	
	AddPlaceAPI("/maps/api/place/add/json"),      //Method names must match with featurefiles name
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;  //this is a global variable
	
	//this is a constructor
	APIResources_Enum(String resource){  //here resource comes from cucumber feature file/stepdefenition method we are loading the resource
		this.resource=resource;   
	}
	
	public String getResource() {  //we need this method to get/return the url value of the requested API
		return resource;
	}
	
	
	
	
	
	
	//or
//	public class Demo{
//		String AddPlaceAPI="/maps/api/place/add/json";
//		String GetPlaceAPI="/maps/api/place/get/json";
//		String DeletePlaceAPI="/maps/api/place/delete/json";
//		
//		public static String getAddPlaceAPI() {
//			return AddPlaceAPI;
//		}
//		
//		public String getGetPlaceAPI() {
//			return GetPlaceAPI;
//		}
//		
//		public String getDeletePlaceAPI() {
//			return DeletePlaceAPI;
//		}
//		
//		//Then in main class create object and access value like
//		Demo d=new Demo();
//		
//		addAPI=d.getAddPlaceAPI;
//		
//		//then use it in addplace api post
//		//eg:
//		Response resp=reqSpec.when().post(addAPI);
//		 .then().spec(respSpec).extract().response();
//	}
	
	
}
