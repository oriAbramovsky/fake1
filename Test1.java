package tests;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

//import main.main.Main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class Test1 {
	
	FirefoxDriver myDriver;
	Selenium selenium;
	String baseUrl;
	
	// fake added
	
	@Before //this happens before runing the test
	public void setUp() throws Exception {
		myDriver = Main.getFireDriver();
		myDriver.navigate().refresh();
		baseUrl = "http://mydvm0895.devlab.ad:20002//ori";
	}
	
	
	@Test // this is the test
	public void test() throws Exception {
		
		// login
		myDriver.get(baseUrl+"/debug.jsp");
		myDriver.findElementById("user_field").sendKeys("a");
		myDriver.findElementByCssSelector("div.content > div.fields > div.pass > input[name=\"j_password\"]")
			.sendKeys("a");
		myDriver.findElementByCssSelector("div.content > div.fields > div.buttons > input[name=\"Login\"]").click();
		
		// wait for the page to load
		myDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// verify that the shown sum of components and it real sum is the same
		myDriver.findElementById("requirements").click();
		int requirementsSum = Integer.parseInt(myDriver.findElementById("requirements").getText());
		int requirementsComponent = (myDriver.findElementsByCssSelector("div[id^='requirementcomponent-1']" +
				"[class='x-container req-component x-box-item x-container-default x-box-layout-ct']").size());
		
		assertTrue(requirementsSum==requirementsComponent);
		
		myDriver.findElementById("defects").click();
		int defectsSum = Integer.parseInt(myDriver.findElementById("defects").getText());
		
		
		myDriver.findElementById("changes").click();
		int changesSum = Integer.parseInt(myDriver.findElementById("changes").getText());
		
		myDriver.findElementById("developers").click();
		int developers = Integer.parseInt(myDriver.findElementById("developers").getText());
		
	}
	
	@After
	public void tearDown() throws Exception {
		
		System.out.println("test1 is over");
	}

}
