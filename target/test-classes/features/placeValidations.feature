Feature: Validating Google Place APIs

@AddPlace @Regression
Scenario Outline: Verify if a place is added successfully using the Add Place API
	Given I have the Add Place Payload with "<name>" "<language>" "<address>"
	When user calls the "AddPlaceAPI" with http "POST" request
	#When user calls the "DeletePlaceAPI" with http "GET" request    #request should work for this also it works for any api(getPlace,postPlace,deletePlace) and any http method(GET/POST..) using concept of Enums
	Then API call is successfull with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"  
	#these 2 can be reused/acheived in single/ the same stepdefinition method comments start at the begining of the line 
	And verify that place_id created using AddPlaceAPI maps to "<name>" using "GetPlaceAPI"   
	
Examples:
	| name 		 | language | address |
	| AppleHouse | English  | World Cross Center |
#	| BallHouse  | Spanish  | Sea Cross Center   | 	 commenting step since we already know paramerization


@DeletePlace @Regression
Scenario: Verify if Delete Place API is working as expected
	Given I have the Delete Place Payload body
	
	#Below 3 methods are reused from above scenario
	When user calls the "DeletePlaceAPI" with http "POST" request
	Then API call is successfull with status code 200
	And "status" in response body is "OK"
	