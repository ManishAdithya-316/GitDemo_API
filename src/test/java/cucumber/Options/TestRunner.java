package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//This class is used to run the code

@RunWith(Cucumber.class)    //we are using junit 4 since juint 5 is not satble and junit 6 is depricated
				//features=fetures package path glue="stepdefinitions package name or full path"
@CucumberOptions(features="src/test/java/features",glue= {"stepDefinitions"},
				plugin="json:target/jsonReports/cucumber-report.json"     //means create a json report under target/jsonReports folder //6)Important refresh the Project to see the Jsonreports folder

		)//tags= "@DeletePlace"   //tags runs all scenarios with @AddPlace tag in any and all feature files
																							//can use conditions for multiple tags 	
//tags = "@AddPlace or @DeletePlace" (Run scenarios with either tag (@AddPlace OR @DeletePlace))
//Run scenarios that have both tags (@AddPlace AND @DeletePlace):  tags = "@AddPlace and @DeletePlace"
//Run scenarios with one tag but exclude another: tags = "@AddPlace and not @DeletePlace"

public class TestRunner {

}


//if you want to use mvn command s and run in cmd prompt:-(navigate to project directory First)
//mvn test    - Runs everything
//mvn test-Dcucumber.filter.tags="@AddPlace"  //runs only addplace or in TestRunnerfile we can add that tags="@AddPlace"

//maven phases
//mvn compiles -> Only compiles code does not execute test cases
//mvn test ->Executes the testcases
//mvn verify ->Used to generate the reports.Only executes after all test cases have been executed


//Steps for reporting
//1)add plugin="json:target/jsonRpeorts/cucumber-report(any name)" in test runner file
//2)go to https://github.com/damianszczepanik/maven-cucumber-reporting copy code and paste in pom.xml above dependencies
//3)Update the version in (Check version here)
//4)Remove below Code:-
//  <!-- optional, defaults to outputDirectory if not specified -->
//<classificationDirectory>${project.build.directory}/classifications</classificationDirectory>
//<classificationFiles>
//        <!-- supports wildcard or name pattern -->
//        <param>sample.properties</param>
//        <param>other.properties</param>
//</classificationFiles> 
//5)run using mvn test verify
//6)Important refresh the Project to see the reports folder
//7)Open overview-features.html in browser and explore report (Features,Tags,Steps,Failures)

//Jenkins CICD Integration steps:-
//1)Download Jenkins.war
//2)Go to downloaded folder and run command java -jar jenkins.war or java -jar jenkins.war --httpPort=9090 (default is 8080
//3)Go to localhost:9090 in browser or localhost:8080
//4)Sign in with Manish_Admin/Shiva@316
//5)Click new item/job
//6)Give Project name & go to Advanced -> check use Custom Workspace and give workspace/eclipse project path
//7)Give src code management as none or git if using github
//8)Click on add maven build step(invoke top level maven targets) and give appropriate cmd eg:test verify or test verify -Dcucumber.filter.tags="@AddPlace"
//9)Save and then click Build now button

//Jenkins Parameterization steps:-
//10)Click on Configure 
//11)Check the This project is Parameterized checkbox
//12)Choose choice parameter and give "tag" as name and choice values as "AddPlace","DeletePlace","Regression"
//13)Modify maven build step command to test verify -Dcucumber.filter.tags="@"$tag""	
//14)Save and click "Build with Parameters" button
//15)Choose any tag eg @RegressionTag and then click "Build"

//To see output:-
//16)Click on running build number under Builds section and select Console Output
//17)After test completion go back to RestAPIFramework folder and click on workspace to access eclipse workspace
//18)Go to target folder -> cucumber-html-reports/ and select any report eg:overviewfeatures.html to view report generated

//Note:- To close jenkins type ctrl+c in cmd prompt or close terminal

