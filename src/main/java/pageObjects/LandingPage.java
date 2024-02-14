package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webdriverUtility.WebDriverUtilityMethods;

public class LandingPage extends WebDriverUtilityMethods{
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmailEdt;
	
	@FindBy(id="userPassword")
	WebElement passwordEdt;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
   
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductsCatalogPage loginToApp(String username, String password)
	{
		userEmailEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
		ProductsCatalogPage pcp=new ProductsCatalogPage(driver);
		return pcp;
	}
	
	public String captureWrongPwMsg()
	{
		waitForElementToAppear(errorMsg);
		return errorMsg.getText();
	}
}
