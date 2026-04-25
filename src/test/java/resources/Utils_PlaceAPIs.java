package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils_PlaceAPIs {   //this class is used for writing common reusable methods
	
	public static RequestSpecification reqSpec;     //Here reqspec obj should not become null and must be reused across multiple tests
	
	public RequestSpecification requestSpecification() throws IOException {
	

	//RestAssured.baseURI="https://rahulshettyacademy.com"; not needed bcoz specBuilder is setting it below using .setBaseUri()
	
	//SpecBuilders are used to replace common code in multiple api eg:addplace api,get place api uses same queryParam
	//RequestSpec builders for req eg:queryParam,contenttype etc
	//ResponseSpecifictionBuilders for common assertions like statuscode = 200 or content type Json,etc
	
	
	if (reqSpec==null) {  //if reqSpec==null for the first test execution, create it else, just return it, this is needed otherwise log file also is replaced/recreated and we are able to see only 1 testcase result in logging.txt(ie, the results of 2nd tc are logged/will replace the result of 1st tc)
		
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));	//Creating PrintStream object which uses FileOutputStream to create a new file called logging.txt
		
		reqSpec=new RequestSpecBuilder().addQueryParam("key","qaclick123")
				
				.setBaseUri(getGlobalValue("baseURI"))  //getGlobalValue("key") method defined below
				
				.addFilter(RequestLoggingFilter.logRequestTo(log))    //log is PrintStream (java.io) obj  //To log request output in file/print stream/console
				.addFilter(ResponseLoggingFilter.logResponseTo(log))						   //To log response output	in file/print stream/console
				
				//here we are implementing logging at global level whenever reqSpec object is used it automatically logs, logs can be checked in the created file logging.txt		
				.setContentType(ContentType.JSON).build();  //contentType is optional
		
		
				return reqSpec;
				}
	return reqSpec;
	
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop=new Properties(); //used to read files with .properties extension and extract values
		FileInputStream fis=new FileInputStream("D:\\eclipse-java-2026-03-R-win32-x86_64\\eclipse\\API_Workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		//fis to read the file
		
		prop.load(fis); //loads the file using fis
		
		//prop.getProperty(key); //here key is any key in global.properties file
		
		return prop.getProperty(key);  //returns String value of key
			
	}
	
	public String getJsonPath(Response resp,String key) {
		String res=resp.asString();
		JsonPath js=new JsonPath(res);
		return js.get(key);
		
	}

}
