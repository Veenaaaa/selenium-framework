package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webdriverUtility.WebDriverUtilityMethods;

public class PaymentPage extends WebDriverUtilityMethods{
	
	WebDriver driver;
	
	public PaymentPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css="button[class*='ta-item']:nth-child(3)")
	WebElement clickOnCountry;
	
	@FindBy(css="a[class*='action__submit']")
	WebElement placeOrderBtn;
	
	By wait=By.cssSelector("section[class*='ta-results']");
	
	public void chooseCountry(String countryName)
	{
		actionsSendKeys(country,countryName);
		waitForElementToAppear(wait);
		clickOnCountry.click();
		
	}
	
	public ConfirmationPage clickOnPlaceOrder()
	{
		placeOrderBtn.click();
		ConfirmationPage cpp=new ConfirmationPage(driver);
		return cpp;
	}

}
