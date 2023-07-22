package variousConcetps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class LoginTest {

	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void reportGenerator() {
		
		extent = new ExtentReports();
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
		extent.attachReporter(htmlReporter);
		
		test = extent.createTest("DataProvier", "Description");
	}

	@BeforeMethod
	public void init() {

		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.techfios.com/billing/?ng=login/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@DataProvider(name = "loginDataMulitUser")
	public String[][] loginData(){
		
		String[][] data = new String[][] {
			{"demo@techfios.com", "abc123"},
			{"demo@techfios1.com", "abc123"},
			{"demo@techfios1.com", "2131321"}
		};
		
		return data;
		
	}
	
	@Test(dataProvider = "loginDataMulitUser")
	public void loginTest(String userName, String password) {
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(userName);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
		driver.findElement(By.xpath("/html/body/div/div/div/form/div[3]/button")).click();

	}
	
	@AfterClass
	public void reporterClose() {
		extent.flush();
	}
	
	
	
	
	

}
