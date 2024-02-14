package baseTestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class Base {
	public WebDriver driver;
	public static WebDriver sDriver;
	public LandingPage lp;
	public WebDriver initialiseDriver() throws IOException
	{
		Properties pro=new Properties();
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\common.properties");
		pro.load(fis);
		String browser=System.getProperty("browser")!=null?System.getProperty("browser"):pro.getProperty("browser");
		//String browser=pro.getProperty("browser");
		
		if(browser.contains("chrome"))
		{   ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if(browser.contains("headless"))
			{
				options.addArguments("headless");
			}
			 driver=new ChromeDriver(options);
			// driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("driver is not setup");
		}
		sDriver=driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initialiseDriver();
		 lp=new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.close();
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		String jsonContent=FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
	});
		return data;
	}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(src, dst);
		return System.getProperty("user.dir")+"//reports"+testcaseName+".png";
	}

}
